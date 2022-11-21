package org.jproducao.rest.pc;

import org.jproducao.model.entity.pc.FolhaObservacao;
import org.jproducao.model.entity.pc.PontoControle;
import org.jproducao.model.entity.pcp.Empresa;
import org.jproducao.model.repository.pc.FolhaObservacaoRepository;
import org.jproducao.model.repository.pc.PontoControleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ponto-controle")
@CrossOrigin("*")
public class PontoControleController {
    private final PontoControleRepository repository;

    @Autowired
    public PontoControleController(PontoControleRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PontoControle salvar(@RequestBody @Valid PontoControle objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public PontoControle acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PontoControle não encontrado"));
    }
    @GetMapping
    public List<PontoControle> getAll(){
        return repository.findAll();
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(pontoControle -> {
                    repository.delete(pontoControle);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PontoControle não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid PontoControle objetoAtualizado){
        repository
                .findById(id)
                .map(pontoControle -> {
                    objetoAtualizado.setId(pontoControle.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PontoControle não encontrado"));
    }
}
