package org.jproducao.rest.ambiente;

import org.jproducao.model.entity.ambiente.Perfil;
import org.jproducao.model.repository.ambiente.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/perfil")
@CrossOrigin("*")
public class PerfilController {
    private final PerfilRepository repository;

    @Autowired
    public PerfilController(PerfilRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Perfil salvar(@RequestBody @Valid Perfil objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Perfil acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(perfil -> {
                    repository.delete(perfil);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Perfil objetoAtualizado){
        repository
                .findById(id)
                .map(perfil -> {
                    objetoAtualizado.setId(perfil.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil não encontrado"));
    }
}
