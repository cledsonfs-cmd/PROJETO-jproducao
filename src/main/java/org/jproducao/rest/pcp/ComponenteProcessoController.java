package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.ComponenteMateriaPrima;
import org.jproducao.model.entity.pcp.ComponenteProcesso;
import org.jproducao.model.repository.pcp.ComponenteMateriaPrimaRepository;
import org.jproducao.model.repository.pcp.ComponenteProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class ComponenteProcessoController {
    private final ComponenteProcessoRepository repository;

    @Autowired
    public ComponenteProcessoController(ComponenteProcessoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComponenteProcesso salvar(@RequestBody @Valid ComponenteProcesso objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public ComponenteProcesso acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ComponenteProcesso não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(componenteProcesso -> {
                    repository.delete(componenteProcesso);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ComponenteProcesso não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid ComponenteProcesso objetoAtualizado){
        repository
                .findById(id)
                .map(componenteProcesso -> {
                    objetoAtualizado.setId(componenteProcesso.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ComponenteProcesso não encontrado"));
    }
}
