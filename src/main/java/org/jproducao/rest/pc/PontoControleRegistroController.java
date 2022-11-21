package org.jproducao.rest.pc;

import org.jproducao.model.entity.pc.PontoControle;
import org.jproducao.model.entity.pc.PontoControleRegistro;
import org.jproducao.model.repository.pc.PontoControleRegistroRepository;
import org.jproducao.model.repository.pc.PontoControleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/ponto-controle-registro")
@CrossOrigin("*")
public class PontoControleRegistroController {
    private final PontoControleRegistroRepository repository;

    @Autowired
    public PontoControleRegistroController(PontoControleRegistroRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PontoControleRegistro salvar(@RequestBody @Valid PontoControleRegistro objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public PontoControleRegistro acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PontoControleRegistro não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(pontoControleRegistro -> {
                    repository.delete(pontoControleRegistro);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PontoControleRegistro não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid PontoControleRegistro objetoAtualizado){
        repository
                .findById(id)
                .map(pontoControleRegistro -> {
                    objetoAtualizado.setId(pontoControleRegistro.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PontoControleRegistro não encontrado"));
    }
}
