package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Almoxarifado;
import org.jproducao.model.entity.pcp.SubProcesso;
import org.jproducao.model.repository.pcp.AlmoxarifadoRepository;
import org.jproducao.model.repository.pcp.SubProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class AlmoxarifadoController {
    private final AlmoxarifadoRepository repository;

    @Autowired
    public AlmoxarifadoController(AlmoxarifadoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Almoxarifado salvar(@RequestBody @Valid Almoxarifado objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Almoxarifado acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Almoxarifado não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(almoxarifado -> {
                    repository.delete(almoxarifado);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Almoxarifado não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Almoxarifado objetoAtualizado){
        repository
                .findById(id)
                .map(almoxarifado -> {
                    objetoAtualizado.setId(almoxarifado.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Almoxarifado não encontrado"));
    }
}
