package org.jproducao.rest.ambiente;

import org.jproducao.model.entity.ambiente.Chat;
import org.jproducao.model.entity.ambiente.Evento;
import org.jproducao.model.entity.pcp.Empresa;
import org.jproducao.model.repository.ambiente.ChatRepository;
import org.jproducao.model.repository.ambiente.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/evento")
@CrossOrigin("*")
public class EventoController {
    private final EventoRepository repository;

    @Autowired
    public EventoController(EventoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Evento salvar(@RequestBody @Valid Evento objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Evento acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));
    }
    @GetMapping
    public List<Evento> getAll(){
        return repository.findAll();
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(evento -> {
                    repository.delete(evento);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Evento objetoAtualizado){
        repository
                .findById(id)
                .map(evento -> {
                    objetoAtualizado.setId(evento.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));
    }
}
