package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Funcionario;
import org.jproducao.model.entity.pcp.FuncionarioEquipamento;
import org.jproducao.model.repository.pcp.FuncionarioEquipamentoRepository;
import org.jproducao.model.repository.pcp.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class FuncionarioEquipamentoController {
    private final FuncionarioEquipamentoRepository repository;

    @Autowired
    public FuncionarioEquipamentoController(FuncionarioEquipamentoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioEquipamento salvar(@RequestBody @Valid FuncionarioEquipamento objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public FuncionarioEquipamento acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "FuncionarioEquipamento não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(funcionarioEquipamento -> {
                    repository.delete(funcionarioEquipamento);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "FuncionarioEquipamento não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid FuncionarioEquipamento objetoAtualizado){
        repository
                .findById(id)
                .map(funcionarioEquipamento -> {
                    objetoAtualizado.setId(funcionarioEquipamento.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "FuncionarioEquipamento não encontrado"));
    }
}
