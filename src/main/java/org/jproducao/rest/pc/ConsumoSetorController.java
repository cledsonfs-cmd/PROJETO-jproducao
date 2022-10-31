package org.jproducao.rest.pc;

import org.jproducao.model.entity.pc.Carteira;
import org.jproducao.model.entity.pc.ConsumoSetor;
import org.jproducao.model.repository.pc.CarteiraRepository;
import org.jproducao.model.repository.pc.ConsumoSetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class ConsumoSetorController {
    private final ConsumoSetorRepository repository;

    @Autowired
    public ConsumoSetorController(ConsumoSetorRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsumoSetor salvar(@RequestBody @Valid ConsumoSetor objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public ConsumoSetor acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ConsumoSetor não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(consumoSetor -> {
                    repository.delete(consumoSetor);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ConsumoSetor não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid ConsumoSetor objetoAtualizado){
        repository
                .findById(id)
                .map(consumoSetor -> {
                    objetoAtualizado.setId(consumoSetor.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ConsumoSetor não encontrado"));
    }
}
