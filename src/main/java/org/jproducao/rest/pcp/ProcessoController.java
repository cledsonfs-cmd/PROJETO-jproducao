package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Pedido;
import org.jproducao.model.entity.pcp.Processo;
import org.jproducao.model.repository.pcp.PedidoRepository;
import org.jproducao.model.repository.pcp.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class ProcessoController {
    private final ProcessoRepository repository;

    @Autowired
    public ProcessoController(ProcessoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Processo salvar(@RequestBody @Valid Processo objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Processo acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Processo não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(processo -> {
                    repository.delete(processo);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Processo não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Processo objetoAtualizado){
        repository
                .findById(id)
                .map(processo -> {
                    objetoAtualizado.setId(processo.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Processo não encontrado"));
    }
}
