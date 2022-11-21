package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Componente;
import org.jproducao.model.entity.pcp.ComponenteMateriaPrima;
import org.jproducao.model.entity.pcp.Empresa;
import org.jproducao.model.repository.pcp.ComponenteMateriaPrimaRepository;
import org.jproducao.model.repository.pcp.ComponenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/componente-materia-prima")
@CrossOrigin("*")
public class ComponenteMateriaPrimaController {
    private final ComponenteMateriaPrimaRepository repository;

    @Autowired
    public ComponenteMateriaPrimaController(ComponenteMateriaPrimaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComponenteMateriaPrima salvar(@RequestBody @Valid ComponenteMateriaPrima objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public ComponenteMateriaPrima acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ComponenteMateriaPrima não encontrado"));
    }

    @GetMapping
    public List<ComponenteMateriaPrima> getAll(){
        return repository.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(componenteMateriaPrima -> {
                    repository.delete(componenteMateriaPrima);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ComponenteMateriaPrima não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid ComponenteMateriaPrima objetoAtualizado){
        repository
                .findById(id)
                .map(componenteMateriaPrima -> {
                    objetoAtualizado.setId(componenteMateriaPrima.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ComponenteMateriaPrima não encontrado"));
    }
}
