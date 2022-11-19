package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.ComponenteProcesso;
import org.jproducao.model.entity.pcp.Embalagem;
import org.jproducao.model.repository.pcp.ComponenteProcessoRepository;
import org.jproducao.model.repository.pcp.EmbalagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/embalagem")
@CrossOrigin("*")
public class EmbalagemController {
    private final EmbalagemRepository repository;

    @Autowired
    public EmbalagemController(EmbalagemRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Embalagem salvar(@RequestBody @Valid Embalagem objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Embalagem acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Embalagem não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(embalagem -> {
                    repository.delete(embalagem);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Embalagem não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Embalagem objetoAtualizado){
        repository
                .findById(id)
                .map(embalagem -> {
                    objetoAtualizado.setId(embalagem.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Embalagem não encontrado"));
    }
}
