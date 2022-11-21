package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Empresa;
import org.jproducao.model.entity.pcp.Estoque;
import org.jproducao.model.entity.pcp.Faturamento;
import org.jproducao.model.repository.pcp.EstoqueRepository;
import org.jproducao.model.repository.pcp.FaturamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/faturamento")
@CrossOrigin("*")
public class FaturamentoController {
    private final FaturamentoRepository repository;

    @Autowired
    public FaturamentoController(FaturamentoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Faturamento salvar(@RequestBody @Valid Faturamento objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Faturamento acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Faturamento não encontrado"));
    }

    @GetMapping
    public List<Faturamento> getAll(){
        return repository.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(faturamento -> {
                    repository.delete(faturamento);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Faturamento não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Faturamento objetoAtualizado){
        repository
                .findById(id)
                .map(faturamento -> {
                    objetoAtualizado.setId(faturamento.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Faturamento não encontrado"));
    }
}
