package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.MateriaPrima;
import org.jproducao.model.entity.pcp.MotivoPerda;
import org.jproducao.model.repository.pcp.MateriaPrimaRepository;
import org.jproducao.model.repository.pcp.MotivoPerdaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class MotivoPerdaController {
    private final MotivoPerdaRepository repository;

    @Autowired
    public MotivoPerdaController(MotivoPerdaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MotivoPerda salvar(@RequestBody @Valid MotivoPerda objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public MotivoPerda acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MotivoPerda não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(motivoPerda -> {
                    repository.delete(motivoPerda);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MotivoPerda não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid MotivoPerda objetoAtualizado){
        repository
                .findById(id)
                .map(motivoPerda -> {
                    objetoAtualizado.setId(motivoPerda.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MotivoPerda não encontrado"));
    }
}
