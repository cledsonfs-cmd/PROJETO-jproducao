package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Faturamento;
import org.jproducao.model.entity.pcp.Funcionario;
import org.jproducao.model.repository.pcp.FaturamentoRepository;
import org.jproducao.model.repository.pcp.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/funcionario")
@CrossOrigin("*")
public class FuncionarioController {
    private final FuncionarioRepository repository;

    @Autowired
    public FuncionarioController(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario salvar(@RequestBody @Valid Funcionario objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Funcionario acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionario não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(funcionario -> {
                    repository.delete(funcionario);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionario não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Funcionario objetoAtualizado){
        repository
                .findById(id)
                .map(funcionario -> {
                    objetoAtualizado.setId(funcionario.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionario não encontrado"));
    }
}
