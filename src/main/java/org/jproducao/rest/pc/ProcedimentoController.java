package org.jproducao.rest.pc;

import org.jproducao.model.entity.pc.Procedimento;
import org.jproducao.model.entity.pc.Tarefa;
import org.jproducao.model.repository.pc.ProcedimentoRepository;
import org.jproducao.model.repository.pc.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class ProcedimentoController {
    private final ProcedimentoRepository repository;

    @Autowired
    public ProcedimentoController(ProcedimentoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Procedimento salvar(@RequestBody @Valid Procedimento objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Procedimento acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Procedimento não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(procedimento -> {
                    repository.delete(procedimento);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Procedimento não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Procedimento objetoAtualizado){
        repository
                .findById(id)
                .map(procedimento -> {
                    objetoAtualizado.setId(procedimento.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Procedimento não encontrado"));
    }
}
