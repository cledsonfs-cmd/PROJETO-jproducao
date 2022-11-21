package org.jproducao.rest.pc;

import org.jproducao.model.entity.pc.Pop;
import org.jproducao.model.entity.pc.PrazoEntrega;
import org.jproducao.model.entity.pcp.Empresa;
import org.jproducao.model.repository.pc.PopRepository;
import org.jproducao.model.repository.pc.PrazoEntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/prazo-entrega")
@CrossOrigin("*")
public class PrazoEntregaController {
    private final PrazoEntregaRepository repository;

    @Autowired
    public PrazoEntregaController(PrazoEntregaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrazoEntrega salvar(@RequestBody @Valid PrazoEntrega objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public PrazoEntrega acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PrazoEntrega não encontrado"));
    }
    @GetMapping
    public List<PrazoEntrega> getAll(){
        return repository.findAll();
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(prazoEntrega -> {
                    repository.delete(prazoEntrega);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PrazoEntrega não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid PrazoEntrega objetoAtualizado){
        repository
                .findById(id)
                .map(prazoEntrega -> {
                    objetoAtualizado.setId(prazoEntrega.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PrazoEntrega não encontrado"));
    }
}
