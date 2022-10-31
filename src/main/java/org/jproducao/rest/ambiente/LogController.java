package org.jproducao.rest.ambiente;

import ch.qos.logback.classic.servlet.LogbackServletContainerInitializer;
import org.jproducao.model.entity.ambiente.Evento;
import org.jproducao.model.entity.ambiente.Log;
import org.jproducao.model.repository.ambiente.EventoRepository;
import org.jproducao.model.repository.ambiente.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class LogController {
    private final LogRepository repository;

    @Autowired
    public LogController(LogRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Log salvar(@RequestBody @Valid Log objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Log acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Log não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(log -> {
                    repository.delete(log);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Log não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Log objetoAtualizado){
        repository
                .findById(id)
                .map(log -> {
                    objetoAtualizado.setId(log.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Log não encontrado"));
    }
}
