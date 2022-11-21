package org.jproducao.rest.pc;

import org.jproducao.model.entity.pc.FolhaElemento;
import org.jproducao.model.entity.pc.FolhaObservacao;
import org.jproducao.model.entity.pcp.Empresa;
import org.jproducao.model.repository.pc.FolhaElementoRepository;
import org.jproducao.model.repository.pc.FolhaObservacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/folha-observacao")
@CrossOrigin("*")
public class FolhaObservacaoController {
    private final FolhaObservacaoRepository repository;

    @Autowired
    public FolhaObservacaoController(FolhaObservacaoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FolhaObservacao salvar(@RequestBody @Valid FolhaObservacao objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public FolhaObservacao acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "FolhaObservacao não encontrado"));
    }
    @GetMapping
    public List<FolhaObservacao> getAll(){
        return repository.findAll();
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(folhaObservacao -> {
                    repository.delete(folhaObservacao);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "FolhaObservacao não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid FolhaObservacao objetoAtualizado){
        repository
                .findById(id)
                .map(folhaObservacao -> {
                    objetoAtualizado.setId(folhaObservacao.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "FolhaObservacao não encontrado"));
    }
}
