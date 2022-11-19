package org.jproducao.rest.ambiente;

import org.jproducao.model.entity.ambiente.Acesso;
import org.jproducao.model.entity.ambiente.Chat;
import org.jproducao.model.repository.ambiente.AcessoRepository;
import org.jproducao.model.repository.ambiente.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
public class ChatController {
    private final ChatRepository repository;

    @Autowired
    public ChatController(ChatRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Chat salvar(@RequestBody @Valid Chat objeto){
        return repository.save(objeto);
    }

    @GetMapping("{id}")
    public Chat acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(chat -> {
                    repository.delete(chat);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Chat objetoAtualizado){
        repository
                .findById(id)
                .map(chat -> {
                    objetoAtualizado.setId(chat.getId());
                    return repository.save(objetoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat não encontrado"));
    }
}
