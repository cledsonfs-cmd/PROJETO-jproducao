package org.jproducao.model.entity.pc;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.jproducao.model.entity.pcp.Setor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pop{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 6)
	private String codigo;

	@ManyToOne
	@JoinColumn(name = "id_setor")
	private Setor setor;

	@Column
	private Double revisao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

	@Column(length = 100)
	private String responsavel;

	@Column(length =100)
	private String revisor;

	@Column(length = 255)
	private String tarefa;

	@Column(length = 255)
	private String resultado;

	@Column(length = 255)
	private String equipamentos;

	@Column(length = 255)
	private String epi;

	@Column(length = 255)
	private String epc;

	@Column(length = 255)
	private String recomendacao;

	@Column(length = 255)
	private String observacao;

	@Column(name = "data_cadastro",updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@PrePersist
	public void prePersist(){
		setDataCadastro(LocalDate.now());
	}
	
}
