package org.jproducao.model.entity.pc;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.jproducao.model.entity.pcp.Funcionario;
import org.jproducao.model.entity.pcp.Maquina;
import org.jproducao.model.entity.pcp.MateriaPrima;
import org.jproducao.model.entity.pcp.Processo;

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
public class FolhaObservacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String folha;

	@ManyToOne
	@JoinColumn(name = "id_processo")
	private Processo processo;

	@Column(name = "nome_peca")
	private String nomePeca;

	@ManyToOne
	@JoinColumn(name = "id_maquina")
	private Maquina maquina;

	@ManyToOne
	@JoinColumn(name = "id_operador")
	private Funcionario operador;

	@Column(name = "experiencia_servico")
	private String experienciaServico;

	@ManyToOne
	@JoinColumn(name = "id_mestre")
	private Funcionario mestre;

	@Column
	private LocalDate data;

	@Column(name = "numero_operacao")
	private Long numeroOperacao;

	@Column(name = "numero_peca")
	private Long numeroPeca;

	@Column(name = "numero_maquina")
	private Long numeroMaquina;

	@Column
	private String sexo;

	@ManyToOne
	@JoinColumn(name = "id_materia_prima")
	private MateriaPrima materiaPrima;

	@Column(name = "numero_secao")
	private String numeroSecao;

	@Column
	private LocalDate inicio;

	@Column
	private LocalDate fim;

	@Column(name = "numero_maquinas")
	private Long numeroMaquinas;

	@Column(name = "unidades_acabadas")
	private Long unidadesAcabadas;

	@Column
	private Double fadiga;

	@Column
	private Double setup;

	@Column
	private Double jornada;

	@Column(name = "data_cadastro",updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@PrePersist
	public void prePersist(){
		setDataCadastro(LocalDate.now());
	}

}
