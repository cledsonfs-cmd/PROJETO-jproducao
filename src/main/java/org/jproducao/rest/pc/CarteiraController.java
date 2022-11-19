package org.jproducao.rest.pc;

import org.jproducao.model.entity.ambiente.Usuario;
import org.jproducao.model.entity.pc.Carteira;
import org.jproducao.model.repository.ambiente.UsuarioRepository;
import org.jproducao.model.repository.pc.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/carteira")
@CrossOrigin("*")
public class CarteiraController {
    private final CarteiraRepository repository;

    @Autowired
    public CarteiraController(CarteiraRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Carteira salvar(@RequestBody @Valid Carteira objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Carteira acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carteira não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(carteira -> {
                    repository.delete(carteira);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carteira não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Carteira objetoAtualizado){
        repository
                .findById(id)
                .map(carteira -> {
                    objetoAtualizado.setId(carteira.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carteira não encontrado"));
    }
}
