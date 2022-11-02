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
public class Setor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@Column(length = 4)
	private String codigo;

	@Column(length = 100)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_tipo_setor")
	private TipoSetor tipo;

	@Column(length = 3)
	private String abreviado;

	@Column(name = "tempo_por_operacao")
	private Integer tempoPorOperacao;

	@Column(name = "horas_disponiveis_dia")
	private Integer horasDisponiveisDia;

	@Column(name = "horas_uso_dia")
	private Integer horasUsoDia;

	@Column(name = "horas_dias_seg_sex")
	private Integer horasDiaSegSex;

	@Column(name = "horas_sabado")
	private Integer horasSabado;

	@Column(name = "dias_nomes")
	private String diasNomes;

	@Column(name = "num_max_op_dia")
	private Integer numMaxOpDia;

	@Column(name = "valor_mao_obra")
	private Integer valorMaoObraNoSetor;

	@Column(name = "fator_utilizado")
	private Integer fatorUtilizado;

	@Column(name = "volume_maximo")
	private Integer volumeMaximo;

	@Column(name = "num_equipamento_disponivel")
	private Integer equipamentoDisponivel;

	@Column(name = "potencia_instalada")
	private Integer potenciaInstalada;

	@Column(name = "consumo_eletricidade")
	private Integer consumoEletricidade;

	@Column(name = "valor_equipamento0")
	private Integer valorEquipmeto0;

	@Column(name = "valor_equipamento1")
	private Integer valorEquipmeto1;

	@Column(name = "valor_equipamento2")
	private Integer valorEquipmeto2;

	@Column(name = "potencia_instalada_producao")
	private Integer potenciaInstaladaProducao;

	@Column(name = "potencia_instalada_apoio")
	private Integer potenciaInstaladaApoio;

	@Column(name = "consumo_eletricidade_producao")
	private Integer consumoEletricidadeProducao;

	@Column(name = "consumo_eletricidade_apoio")
	private Integer consumoEletricidadeApoio;

	@Column(name = "valor_equipamento_met0_producao")
	private Integer valorEquipamentoMet0Producao;

	@Column(name = "valor_equipamento_met0_apoio")
	private Integer valorEquipamentoMet0Apoio;

	@Column(name = "valor_equipamento_met1_producao")
	private Integer valorEquipamentoMet1Producao;

	@Column(name = "valor_equipamento_met1_apoio")
	private Integer valorEquipamentoMet1Apoio;

	@Column(name = "valor_equipamento_met2_producao")
	private Integer valorEquipamentoMet2Producao;

	@Column(name = "valor_equipamento_met2_apoio")
	private Integer valorEquipamentoMet2Apoio;

	@Column(name = "valor_mao_obra_direta")
	private Integer valorMaoObraDireta;

	@Column(name = "valor_mao_obra_supervisao")
	private Integer valorMaoObraSupervisao;

	@Column(name = "valor_mao_obra_direta_producao")
	private Integer valorMaoObraDiretaProducao;

	@Column(name = "valor_mao_obra_supervisao_producao")
	private Integer valorMaoObraSupervisaoProducao;

	@Column(name = "valor_mao_obra_apoio")
	private Integer valorMaoObraApoio;

	@Column(name = "valor_material_indireto")
	private Integer valorMaterialIndireto;

	@Column(name = "valor_material_indapoio")
	private Integer valorMaterialIndapoio;

	@Column(name = "valor_material_indprod")
	private Integer valorMaterialIndprod;

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
