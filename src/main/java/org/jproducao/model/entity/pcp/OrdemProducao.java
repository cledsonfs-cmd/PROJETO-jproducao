package org.jproducao.model.entity.pcp;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@CrossOrigin()
public class OrdemProducao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;

	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "id_lote")
	private Lote lote;

	@Column(length = 4)
	private String codigo;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "id_unidade")
	private Unidade unidade;

	@ManyToOne
	@JoinColumn(name = "id_processo")
	private Processo processo;

	@ManyToOne
	@JoinColumn(name = "id_setor")
	private Setor setor;

	@Column
	private Double prevista;

	@Column
	private Double disponivel;

	@Column
	private Double programada;

	@Column(name="entrega_imediata")
	private Boolean entregaImediata;

	@Column(length = 255)
	private String observacoes;

	@Column
	private Boolean impressa;

	@Column(length = 1)
	private String status;

	@Column(name = "nivel_arvore")
	private Integer nivelArvore;

	@Column
	private Boolean controlada;

	@Column(name = "data_conclusao")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataConclusao;

	@Column(name = "movimento_inverso")
	private Boolean movimentoInverso;

	@Column
	private Integer reprogramada;

	@Column(name = "solicitacao_reprogramacao")
	private Boolean solicitacaoReprogramacao;

	@ManyToOne
	@JoinColumn(name = "id_disponibilizado")
	private Funcionario disponibilizadoPor;

	@Column(name = "conclusao_usuario")
	private Boolean conclusaoUsuario;

	@Column
	private Boolean completa;

	@Column
	private Boolean avulsa;

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
