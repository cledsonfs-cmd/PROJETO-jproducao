package org.jproducao.rest.pc;

import org.jproducao.model.entity.pc.Producao;
import org.jproducao.model.entity.pc.Tarefa;
import org.jproducao.model.repository.pc.ProducaoRepository;
import org.jproducao.model.repository.pc.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tarefa")
@CrossOrigin("*")
public class TarefaController {
    private final TarefaRepository repository;

    @Autowired
    public TarefaController(TarefaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa salvar(@RequestBody @Valid Tarefa objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Tarefa acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(tarefa -> {
                    repository.delete(tarefa);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Tarefa objetoAtualizado){
        repository
                .findById(id)
                .map(tarefa -> {
                    objetoAtualizado.setId(tarefa.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrado"));
    }
}
