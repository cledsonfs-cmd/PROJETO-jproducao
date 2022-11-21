package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Empresa;
import org.jproducao.model.entity.pcp.Lote;
import org.jproducao.model.entity.pcp.Maquina;
import org.jproducao.model.repository.pcp.LoteRepository;
import org.jproducao.model.repository.pcp.MaquinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/maquina")
@CrossOrigin("*")
public class MaquinaController {
    private final MaquinaRepository repository;

    @Autowired
    public MaquinaController(MaquinaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Maquina salvar(@RequestBody @Valid Maquina objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Maquina acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Maquina não encontrado"));
    }

    @GetMapping
    public List<Maquina> getAll(){
        return repository.findAll();
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(maquina -> {
                    repository.delete(maquina);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Maquina não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Maquina objetoAtualizado){
        repository
                .findById(id)
                .map(maquina -> {
                    objetoAtualizado.setId(maquina.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Maquina não encontrado"));
    }
}
