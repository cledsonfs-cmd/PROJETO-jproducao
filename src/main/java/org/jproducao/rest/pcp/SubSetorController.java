package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.SubSetor;
import org.jproducao.model.entity.pcp.TipoSetor;
import org.jproducao.model.repository.pcp.SubSetorRepository;
import org.jproducao.model.repository.pcp.TipoSetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class SubSetorController {
    private final SubSetorRepository repository;

    @Autowired
    public SubSetorController(SubSetorRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubSetor salvar(@RequestBody @Valid SubSetor objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public SubSetor acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubSetor não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(subSetor -> {
                    repository.delete(subSetor);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubSetor não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid SubSetor objetoAtualizado){
        repository
                .findById(id)
                .map(subSetor -> {
                    objetoAtualizado.setId(subSetor.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubSetor não encontrado"));
    }
}
