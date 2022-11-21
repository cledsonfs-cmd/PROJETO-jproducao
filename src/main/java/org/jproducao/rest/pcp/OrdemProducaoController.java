package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Empresa;
import org.jproducao.model.entity.pcp.Operacao;
import org.jproducao.model.entity.pcp.OrdemProducao;
import org.jproducao.model.repository.pcp.OperacaoRepository;
import org.jproducao.model.repository.pcp.OrdemProducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ordem-producao")
@CrossOrigin("*")
public class OrdemProducaoController {
    private final OrdemProducaoRepository repository;

    @Autowired
    public OrdemProducaoController(OrdemProducaoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemProducao salvar(@RequestBody @Valid OrdemProducao objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public OrdemProducao acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "OrdemProducao não encontrado"));
    }
    @GetMapping
    public List<OrdemProducao> getAll(){
        return repository.findAll();
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(ordemProducao -> {
                    repository.delete(ordemProducao);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "OrdemProducao não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid OrdemProducao objetoAtualizado){
        repository
                .findById(id)
                .map(ordemProducao -> {
                    objetoAtualizado.setId(ordemProducao.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "OrdemProducao não encontrado"));
    }
}
