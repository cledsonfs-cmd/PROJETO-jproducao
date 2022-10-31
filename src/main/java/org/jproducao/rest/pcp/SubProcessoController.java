package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.SubProcesso;
import org.jproducao.model.entity.pcp.SubSetor;
import org.jproducao.model.repository.pcp.SubProcessoRepository;
import org.jproducao.model.repository.pcp.SubSetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class SubProcessoController {
    private final SubProcessoRepository repository;

    @Autowired
    public SubProcessoController(SubProcessoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubProcesso salvar(@RequestBody @Valid SubProcesso objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public SubProcesso acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubProcesso não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(subProcesso -> {
                    repository.delete(subProcesso);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubProcesso não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid SubProcesso objetoAtualizado){
        repository
                .findById(id)
                .map(subProcesso -> {
                    objetoAtualizado.setId(subProcesso.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubProcesso não encontrado"));
    }
}