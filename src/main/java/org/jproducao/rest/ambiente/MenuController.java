package org.jproducao.rest.ambiente;

import org.jproducao.model.entity.ambiente.Log;
import org.jproducao.model.entity.ambiente.Menu;
import org.jproducao.model.repository.ambiente.LogRepository;
import org.jproducao.model.repository.ambiente.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class MenuController {
    private final MenuRepository repository;

    @Autowired
    public MenuController(MenuRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Menu salvar(@RequestBody @Valid Menu objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Menu acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(menu -> {
                    repository.delete(menu);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Menu objetoAtualizado){
        repository
                .findById(id)
                .map(menu -> {
                    objetoAtualizado.setId(menu.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu não encontrado"));
    }
}
