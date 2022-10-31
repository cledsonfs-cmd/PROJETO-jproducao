package org.jproducao.rest.ambiente;

import org.jproducao.model.entity.ambiente.Perfil;
import org.jproducao.model.entity.ambiente.Tela;
import org.jproducao.model.repository.ambiente.PerfilRepository;
import org.jproducao.model.repository.ambiente.TelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class TelaController {
    private final TelaRepository repository;

    @Autowired
    public TelaController(TelaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tela salvar(@RequestBody @Valid Tela objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Tela acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tela não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(tela -> {
                    repository.delete(tela);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tela não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Tela objetoAtualizado){
        repository
                .findById(id)
                .map(tela -> {
                    objetoAtualizado.setId(tela.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tela não encontrado"));
    }
}
