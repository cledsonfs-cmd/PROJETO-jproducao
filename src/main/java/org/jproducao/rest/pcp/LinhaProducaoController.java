package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.ItemPedido;
import org.jproducao.model.entity.pcp.LinhaProducao;
import org.jproducao.model.repository.pcp.ItemPedidoRepository;
import org.jproducao.model.repository.pcp.LinhaProducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/linha-producao")
@CrossOrigin("*")
public class LinhaProducaoController {
    private final LinhaProducaoRepository repository;

    @Autowired
    public LinhaProducaoController(LinhaProducaoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LinhaProducao salvar(@RequestBody @Valid LinhaProducao objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public LinhaProducao acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LinhaProducao não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(linhaProducao -> {
                    repository.delete(linhaProducao);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LinhaProducao não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid LinhaProducao objetoAtualizado){
        repository
                .findById(id)
                .map(linhaProducao -> {
                    objetoAtualizado.setId(linhaProducao.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LinhaProducao não encontrado"));
    }
}
