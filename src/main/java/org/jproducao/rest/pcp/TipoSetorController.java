package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.TipoSetor;
import org.jproducao.model.entity.pcp.Unidade;
import org.jproducao.model.repository.pcp.TipoSetorRepository;
import org.jproducao.model.repository.pcp.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class TipoSetorController {
    private final TipoSetorRepository repository;

    @Autowired
    public TipoSetorController(TipoSetorRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoSetor salvar(@RequestBody @Valid TipoSetor objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public TipoSetor acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TipoSetor não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(tipoSetor -> {
                    repository.delete(tipoSetor);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TipoSetor não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid TipoSetor objetoAtualizado){
        repository
                .findById(id)
                .map(tipoSetor -> {
                    objetoAtualizado.setId(tipoSetor.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TipoSetor não encontrado"));
    }
}
