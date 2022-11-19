package org.jproducao.rest.pc;

import org.jproducao.model.entity.pc.PontoControle;
import org.jproducao.model.entity.pc.Pop;
import org.jproducao.model.repository.pc.PontoControleRepository;
import org.jproducao.model.repository.pc.PopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/pop")
@CrossOrigin("*")
public class PopController {
    private final PopRepository repository;

    @Autowired
    public PopController(PopRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pop salvar(@RequestBody @Valid Pop objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Pop acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pop não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(pop -> {
                    repository.delete(pop);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pop não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Pop objetoAtualizado){
        repository
                .findById(id)
                .map(pop -> {
                    objetoAtualizado.setId(pop.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pop não encontrado"));
    }
}
