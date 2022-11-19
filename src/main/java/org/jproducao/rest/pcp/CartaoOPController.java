package org.jproducao.rest.pcp;

import org.jproducao.model.entity.pcp.Almoxarifado;
import org.jproducao.model.entity.pcp.CartaoOP;
import org.jproducao.model.repository.pcp.AlmoxarifadoRepository;
import org.jproducao.model.repository.pcp.CartaoOPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cartao-op")
@CrossOrigin("*")
public class CartaoOPController {
    private final CartaoOPRepository repository;

    @Autowired
    public CartaoOPController(CartaoOPRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartaoOP salvar(@RequestBody @Valid CartaoOP objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public CartaoOP acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CartaoOP não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(cartaoOP -> {
                    repository.delete(cartaoOP);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CartaoOP não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid CartaoOP objetoAtualizado){
        repository
                .findById(id)
                .map(cartaoOP -> {
                    objetoAtualizado.setId(cartaoOP.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CartaoOP não encontrado"));
    }
}
