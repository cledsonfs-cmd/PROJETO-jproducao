package org.jproducao.rest.pc;

import org.jproducao.model.entity.pc.ElementoTempo;
import org.jproducao.model.entity.pc.FolhaElemento;
import org.jproducao.model.repository.pc.ElementoTempoRepository;
import org.jproducao.model.repository.pc.FolhaElementoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class FolhaElementoController {
    private final FolhaElementoRepository repository;

    @Autowired
    public FolhaElementoController(FolhaElementoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FolhaElemento salvar(@RequestBody @Valid FolhaElemento objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public FolhaElemento acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "FolhaElemento não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(folhaElemento -> {
                    repository.delete(folhaElemento);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "FolhaElemento não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid FolhaElemento objetoAtualizado){
        repository
                .findById(id)
                .map(folhaElemento -> {
                    objetoAtualizado.setId(folhaElemento.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "FolhaElemento não encontrado"));
    }
}
