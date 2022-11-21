package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Empresa;
import org.jproducao.model.entity.pcp.Produto;
import org.jproducao.model.entity.pcp.Representante;
import org.jproducao.model.repository.pcp.ProdutoRepository;
import org.jproducao.model.repository.pcp.RepresentanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/representante")
@CrossOrigin("*")
public class RepresentanteController {
    private final RepresentanteRepository repository;

    @Autowired
    public RepresentanteController(RepresentanteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Representante salvar(@RequestBody @Valid Representante objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Representante acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Representante não encontrado"));
    }
    @GetMapping
    public List<Representante> getAll(){
        return repository.findAll();
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(representante -> {
                    repository.delete(representante);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Representante não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Representante objetoAtualizado){
        repository
                .findById(id)
                .map(representante -> {
                    objetoAtualizado.setId(representante.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Representante não encontrado"));
    }
}
