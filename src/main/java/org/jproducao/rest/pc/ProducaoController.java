package org.jproducao.rest.pc;

import org.jproducao.model.entity.pc.PrazoEntrega;
import org.jproducao.model.entity.pc.Producao;
import org.jproducao.model.entity.pcp.Empresa;
import org.jproducao.model.repository.pc.PrazoEntregaRepository;
import org.jproducao.model.repository.pc.ProducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/producao")
@CrossOrigin("*")
public class ProducaoController {
    private final ProducaoRepository repository;

    @Autowired
    public ProducaoController(ProducaoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producao salvar(@RequestBody @Valid Producao objeto){
        return repository.save(objeto);
    }
    @GetMapping
    public List<Producao> getAll(){
        return repository.findAll();
    }
    @GetMapping("{id}")
    public Producao acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producao não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(producao -> {
                    repository.delete(producao);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producao não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Producao objetoAtualizado){
        repository
                .findById(id)
                .map(producao -> {
                    objetoAtualizado.setId(producao.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producao não encontrado"));
    }
}
