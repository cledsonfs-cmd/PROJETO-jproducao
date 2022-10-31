package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Representante;
import org.jproducao.model.entity.pcp.Reprogramacao;
import org.jproducao.model.repository.pcp.RepresentanteRepository;
import org.jproducao.model.repository.pcp.ReprogramacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class ReprogramacaoController {
    private final ReprogramacaoRepository repository;

    @Autowired
    public ReprogramacaoController(ReprogramacaoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reprogramacao salvar(@RequestBody @Valid Reprogramacao objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Reprogramacao acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reprogramacao não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(reprogramacao -> {
                    repository.delete(reprogramacao);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reprogramacao não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Reprogramacao objetoAtualizado){
        repository
                .findById(id)
                .map(reprogramacao -> {
                    objetoAtualizado.setId(reprogramacao.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reprogramacao não encontrado"));
    }
}
