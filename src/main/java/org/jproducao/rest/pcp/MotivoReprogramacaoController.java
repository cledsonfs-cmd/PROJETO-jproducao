package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.MotivoPerda;
import org.jproducao.model.entity.pcp.MotivoReprogramacao;
import org.jproducao.model.repository.pcp.MotivoPerdaRepository;
import org.jproducao.model.repository.pcp.MotivoReprogramacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/motivo-reprogramacao")
@CrossOrigin("*")
public class MotivoReprogramacaoController {
    private final MotivoReprogramacaoRepository repository;

    @Autowired
    public MotivoReprogramacaoController(MotivoReprogramacaoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MotivoReprogramacao salvar(@RequestBody @Valid MotivoReprogramacao objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public MotivoReprogramacao acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MotivoReprogramacao não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(motivoReprogramacao -> {
                    repository.delete(motivoReprogramacao);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MotivoReprogramacao não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid MotivoReprogramacao objetoAtualizado){
        repository
                .findById(id)
                .map(motivoReprogramacao -> {
                    objetoAtualizado.setId(motivoReprogramacao.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MotivoReprogramacao não encontrado"));
    }
}
