package org.jproducao.model.entity.ambiente;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 25)
    @NotNull(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(length = 10)
    private String caption;

    @Column(name = "acesso_rapido",length = 10)
    private String acessoRapido;

}