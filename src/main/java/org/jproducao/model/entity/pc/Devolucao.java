package org.jproducao.model.entity.pc;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.jproducao.model.entity.pcp.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Devolucao{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 8)
	@NotEmpty(message = "{campo.codigo_pedido.obrigatorio}")
	private String codpedido;

	@Column
	private BigDecimal valor;

	@Column
	private BigDecimal quantidade;

	@Column(name = "data_faturamento",updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_faturada;

	@Column(name = "data_devolucao",updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_devolucao;

	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "id_vendedor")
	private Representante vendedor;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "id_unidade")
	private Unidade unidade;

	@Column(name = "id_origem_erro")
	private String origemErro;

	@Column
	private String tipo;

	@Column
	private String motivo;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate update;

	@Column(name = "data_cadastro",updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@PrePersist
	public void prePersist(){
		setUpdate(LocalDate.now());
		setDataCadastro(LocalDate.now());
	}

}
