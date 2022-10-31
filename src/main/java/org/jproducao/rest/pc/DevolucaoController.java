package org.jproducao.rest.pc;

import org.jproducao.model.entity.pc.ConsumoSetor;
import org.jproducao.model.entity.pc.Devolucao;
import org.jproducao.model.repository.pc.ConsumoSetorRepository;
import org.jproducao.model.repository.pc.DevolucaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class DevolucaoController {
    private final DevolucaoRepository repository;

    @Autowired
    public DevolucaoController(DevolucaoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Devolucao salvar(@RequestBody @Valid Devolucao objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Devolucao acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Devolucao não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(devolucao -> {
                    repository.delete(devolucao);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Devolucao não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Devolucao objetoAtualizado){
        repository
                .findById(id)
                .map(devolucao -> {
                    objetoAtualizado.setId(devolucao.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Devolucao não encontrado"));
    }
}
