package org.jproducao.rest.ambiente;

import org.jproducao.model.entity.ambiente.Acesso;
import org.jproducao.model.repository.ambiente.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/acesso")
public class AcessoController {
    private final AcessoRepository repository;

    @Autowired
    public AcessoController(AcessoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Acesso salvar(@RequestBody @Valid Acesso objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Acesso acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Acesso não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(acesso -> {
                    repository.delete(acesso);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Acesso não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Acesso objetoAtualizado){
        repository
                .findById(id)
                .map(acesso -> {
                    objetoAtualizado.setId(acesso.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Acesso não encontrado"));
    }
}
