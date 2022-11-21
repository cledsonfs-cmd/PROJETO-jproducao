package org.jproducao.model.entity.pc;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jproducao.model.entity.pcp.Produto;
import org.jproducao.model.entity.pcp.Setor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PontoControleRegistro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_ponto_controle")
    private PontoControle pontoControle;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @Column
    private Double quantidade;

    @Column
    private Double peso;

    @Column
    private Double valor;

    @Column(length = 255)
    private String observacao;

    @Column
    private String extra1;

    @Column
    private String extra2;

    @Column
    private String extra3;

    @Column(name = "data_cadastro",updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }
}
