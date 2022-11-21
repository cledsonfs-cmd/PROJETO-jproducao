package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Empresa;
import org.jproducao.model.entity.pcp.LinhaProducao;
import org.jproducao.model.entity.pcp.Lote;
import org.jproducao.model.repository.pcp.LinhaProducaoRepository;
import org.jproducao.model.repository.pcp.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lote")
@CrossOrigin("*")
public class LoteController {
    private final LoteRepository repository;

    @Autowired
    public LoteController(LoteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Lote salvar(@RequestBody @Valid Lote objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Lote acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lote não encontrado"));
    }

    @GetMapping
    public List<Lote> getAll(){
        return repository.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(lote -> {
                    repository.delete(lote);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lote não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Lote objetoAtualizado){
        repository
                .findById(id)
                .map(lote -> {
                    objetoAtualizado.setId(lote.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lote não encontrado"));
    }
}
