package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Empresa;
import org.jproducao.model.entity.pcp.FuncionarioEquipamento;
import org.jproducao.model.entity.pcp.ItemPedido;
import org.jproducao.model.repository.pcp.FuncionarioEquipamentoRepository;
import org.jproducao.model.repository.pcp.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/item-pedido")
@CrossOrigin("*")
public class ItemPedidoController {
    private final ItemPedidoRepository repository;

    @Autowired
    public ItemPedidoController(ItemPedidoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemPedido salvar(@RequestBody @Valid ItemPedido objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public ItemPedido acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemPedido não encontrado"));
    }

    @GetMapping
    public List<ItemPedido> getAll(){
        return repository.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(itemPedido -> {
                    repository.delete(itemPedido);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemPedido não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid ItemPedido objetoAtualizado){
        repository
                .findById(id)
                .map(itemPedido -> {
                    objetoAtualizado.setId(itemPedido.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemPedido não encontrado"));
    }
}
