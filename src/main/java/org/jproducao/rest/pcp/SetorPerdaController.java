package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Setor;
import org.jproducao.model.entity.pcp.SetorPerda;
import org.jproducao.model.repository.pcp.SetorPerdaRepository;
import org.jproducao.model.repository.pcp.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/setor-perda")
@CrossOrigin("*")
public class SetorPerdaController {
    private final SetorPerdaRepository repository;

    @Autowired
    public SetorPerdaController(SetorPerdaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SetorPerda salvar(@RequestBody @Valid SetorPerda objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public SetorPerda acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SetorPerda não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(setorPerda -> {
                    repository.delete(setorPerda);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SetorPerda não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid SetorPerda objetoAtualizado){
        repository
                .findById(id)
                .map(setorPerda -> {
                    objetoAtualizado.setId(setorPerda.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SetorPerda não encontrado"));
    }
}
