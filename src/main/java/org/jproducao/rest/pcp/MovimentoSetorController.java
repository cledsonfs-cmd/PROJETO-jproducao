package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.MotivoReprogramacao;
import org.jproducao.model.entity.pcp.MovimentoSetor;
import org.jproducao.model.repository.pcp.MotivoReprogramacaoRepository;
import org.jproducao.model.repository.pcp.MovimentoSetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/movimento-setor")
@CrossOrigin("*")
public class MovimentoSetorController {
    private final MovimentoSetorRepository repository;

    @Autowired
    public MovimentoSetorController(MovimentoSetorRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovimentoSetor salvar(@RequestBody @Valid MovimentoSetor objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public MovimentoSetor acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MovimentoSetor não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(movimentoSetor -> {
                    repository.delete(movimentoSetor);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MovimentoSetor não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid MovimentoSetor objetoAtualizado){
        repository
                .findById(id)
                .map(movimentoSetor -> {
                    objetoAtualizado.setId(movimentoSetor.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MovimentoSetor não encontrado"));
    }
}
