package application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Aluno;
import application.repository.AlunoRepository;

@RestController
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepo;

    @GetMapping("/alunos")
    public List<Aluno> getAluno() {
        return (List<Aluno>) alunoRepo.findAll();
    }

    @PostMapping("/alunos")
    public Aluno postAluno(@RequestBody Aluno aluno) {
        return alunoRepo.save(aluno);
    }

    @GetMapping("/alunos/{id}")
    public Aluno getAluno(@PathVariable Long id) {
        return alunoRepo.findById(id).get();
    }

    @PutMapping("/alunos/{id}")
    public Aluno putAluno(@RequestBody Aluno aluno, @PathVariable Long id) {
        Aluno resposta = alunoRepo.findById(id).get();

        resposta.setNome(aluno.getNome());
        resposta.setIdade(aluno.getIdade());
        resposta.setCurso(aluno.getCurso());

        return alunoRepo.save(resposta);
    }

    @DeleteMapping("/alunos/{id}")
    public void deleteAluno(@PathVariable Long id) {
        if (alunoRepo.existsById(id)) {
            alunoRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
