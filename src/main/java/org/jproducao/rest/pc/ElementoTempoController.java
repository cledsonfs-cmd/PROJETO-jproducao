package org.jproducao.rest.pc;

import org.jproducao.model.entity.pc.Devolucao;
import org.jproducao.model.entity.pc.ElementoTempo;
import org.jproducao.model.entity.pcp.Empresa;
import org.jproducao.model.repository.pc.DevolucaoRepository;
import org.jproducao.model.repository.pc.ElementoTempoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/elemento-tempo")
@CrossOrigin("*")
public class ElementoTempoController {
    private final ElementoTempoRepository repository;

    @Autowired
    public ElementoTempoController(ElementoTempoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ElementoTempo salvar(@RequestBody @Valid ElementoTempo objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public ElementoTempo acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ElementoTempo não encontrado"));
    }
    @GetMapping
    public List<ElementoTempo> getAll(){
        return repository.findAll();
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(elementoTempo -> {
                    repository.delete(elementoTempo);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ElementoTempo não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid ElementoTempo objetoAtualizado){
        repository
                .findById(id)
                .map(elementoTempo -> {
                    objetoAtualizado.setId(elementoTempo.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ElementoTempo não encontrado"));
    }
}
