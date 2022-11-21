package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Empresa;
import org.jproducao.model.entity.pcp.Reprogramacao;
import org.jproducao.model.entity.pcp.Setor;
import org.jproducao.model.repository.pcp.ReprogramacaoRepository;
import org.jproducao.model.repository.pcp.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/setor")
@CrossOrigin("*")
public class SetorController {
    private final SetorRepository repository;

    @Autowired
    public SetorController(SetorRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Setor salvar(@RequestBody @Valid Setor objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Setor acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor não encontrado"));
    }
    @GetMapping
    public List<Setor> getAll(){
        return repository.findAll();
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(setor -> {
                    repository.delete(setor);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Setor objetoAtualizado){
        repository
                .findById(id)
                .map(setor -> {
                    objetoAtualizado.setId(setor.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor não encontrado"));
    }
}
