package org.jproducao.model.entity.pcp;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estoque{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100)
	private String descricao;

	@Column
	private Double quantidade;

	@ManyToOne
	@JoinColumn(name = "id_unidade")
	private Unidade unidade;

	@Column(length = 1)
	private Character movimento;

	@Column(length = 255)
	private String observacao;

	@Column
	private Double saldo;

	@Column(name = "data_update")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate update;

	@Column(name = "data_cadastro",updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@PrePersist
	public void prePersist(){
		setDataCadastro(LocalDate.now());
		setUpdate(LocalDate.now());
	}
}
