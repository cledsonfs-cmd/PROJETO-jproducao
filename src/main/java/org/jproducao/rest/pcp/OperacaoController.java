package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.MovimentoSetor;
import org.jproducao.model.entity.pcp.Operacao;
import org.jproducao.model.repository.pcp.MovimentoSetorRepository;
import org.jproducao.model.repository.pcp.OperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class OperacaoController {
    private final OperacaoRepository repository;

    @Autowired
    public OperacaoController(OperacaoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Operacao salvar(@RequestBody @Valid Operacao objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Operacao acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Operacao não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(operacao -> {
                    repository.delete(operacao);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Operacao não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Operacao objetoAtualizado){
        repository
                .findById(id)
                .map(operacao -> {
                    objetoAtualizado.setId(operacao.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Operacao não encontrado"));
    }
}
