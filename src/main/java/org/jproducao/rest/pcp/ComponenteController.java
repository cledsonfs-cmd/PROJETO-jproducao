package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Cliente;
import org.jproducao.model.entity.pcp.Componente;
import org.jproducao.model.entity.pcp.Empresa;
import org.jproducao.model.repository.pcp.ClienteRepository;
import org.jproducao.model.repository.pcp.ComponenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/componente")
@CrossOrigin("*")
public class ComponenteController {
    private final ComponenteRepository repository;

    @Autowired
    public ComponenteController(ComponenteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Componente salvar(@RequestBody @Valid Componente objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Componente acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Componente não encontrado"));
    }

    @GetMapping
    public List<Componente> getAll(){
        return repository.findAll();
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(componente -> {
                    repository.delete(componente);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Componente não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Componente objetoAtualizado){
        repository
                .findById(id)
                .map(componente -> {
                    objetoAtualizado.setId(componente.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Componente não encontrado"));
    }
}
