package org.jproducao.model.entity.pc;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.jproducao.model.entity.pcp.Produto;
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
public class PontoControle{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_locacao")
	private Setor locacao;

	@Column(name = "sub_setor")
	private Boolean subSetor;

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
