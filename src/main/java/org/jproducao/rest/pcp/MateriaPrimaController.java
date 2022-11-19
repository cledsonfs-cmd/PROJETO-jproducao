package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Maquina;
import org.jproducao.model.entity.pcp.MateriaPrima;
import org.jproducao.model.repository.pcp.MaquinaRepository;
import org.jproducao.model.repository.pcp.MateriaPrimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/materia-prima")
@CrossOrigin("*")
public class MateriaPrimaController {
    private final MateriaPrimaRepository repository;

    @Autowired
    public MateriaPrimaController(MateriaPrimaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MateriaPrima salvar(@RequestBody @Valid MateriaPrima objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public MateriaPrima acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MateriaPrima não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(materiaPrima -> {
                    repository.delete(materiaPrima);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MateriaPrima não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid MateriaPrima objetoAtualizado){
        repository
                .findById(id)
                .map(materiaPrima -> {
                    objetoAtualizado.setId(materiaPrima.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MateriaPrima não encontrado"));
    }
}
