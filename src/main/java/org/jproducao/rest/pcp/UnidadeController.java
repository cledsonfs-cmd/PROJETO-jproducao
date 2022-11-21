package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pc.Carteira;
import org.jproducao.model.entity.pcp.Empresa;
import org.jproducao.model.entity.pcp.Unidade;
import org.jproducao.model.repository.pc.CarteiraRepository;
import org.jproducao.model.repository.pcp.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/unidade")
@CrossOrigin("*")
public class UnidadeController {
    private final UnidadeRepository repository;

    @Autowired
    public UnidadeController(UnidadeRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Unidade salvar(@RequestBody @Valid Unidade objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Unidade acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidade não encontrado"));
    }
    @GetMapping
    public List<Unidade> getAll(){
        return repository.findAll();
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(unidade -> {
                    repository.delete(unidade);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidade não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Unidade objetoAtualizado){
        repository
                .findById(id)
                .map(unidade -> {
                    objetoAtualizado.setId(unidade.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidade não encontrado"));
    }
}
