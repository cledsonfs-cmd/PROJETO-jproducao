package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.OrdemProducao;
import org.jproducao.model.entity.pcp.Pedido;
import org.jproducao.model.repository.pcp.OrdemProducaoRepository;
import org.jproducao.model.repository.pcp.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/pedido")
@CrossOrigin("*")
public class PedidoController {
    private final PedidoRepository repository;

    @Autowired
    public PedidoController(PedidoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido salvar(@RequestBody @Valid Pedido objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Pedido acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(pedido -> {
                    repository.delete(pedido);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Pedido objetoAtualizado){
        repository
                .findById(id)
                .map(pedido -> {
                    objetoAtualizado.setId(pedido.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }
}
