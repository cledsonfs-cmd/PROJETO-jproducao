package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Equipamento;
import org.jproducao.model.entity.pcp.Estoque;
import org.jproducao.model.repository.pcp.EquipamentoRepository;
import org.jproducao.model.repository.pcp.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/estoque")
@CrossOrigin("*")
public class EstoqueController {
    private final EstoqueRepository repository;

    @Autowired
    public EstoqueController(EstoqueRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estoque salvar(@RequestBody @Valid Estoque objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Estoque acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(estoque -> {
                    repository.delete(estoque);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Estoque objetoAtualizado){
        repository
                .findById(id)
                .map(estoque -> {
                    objetoAtualizado.setId(estoque.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado"));
    }
}
