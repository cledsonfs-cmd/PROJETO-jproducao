package org.jproducao.rest.pcp;

import antlr.StringUtils;
import org.jproducao.model.entity.pcp.Embalagem;
import org.jproducao.model.entity.pcp.Empresa;
import org.jproducao.model.repository.pcp.EmbalagemRepository;
import org.jproducao.model.repository.pcp.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
@RestController
@RequestMapping("/api/empresa")
@CrossOrigin("*")
public class EmpresaController {
    private final EmpresaRepository repository;

    @Autowired
    public EmpresaController(EmpresaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa salvar(@RequestBody @Valid Empresa objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Empresa acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(empresa -> {
                    repository.delete(empresa);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Empresa objetoAtualizado){
        repository
                .findById(id)
                .map(empresa -> {
                    objetoAtualizado.setId(empresa.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrado"));
    }
}
