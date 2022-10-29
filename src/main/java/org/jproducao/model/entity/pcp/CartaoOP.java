package org.jproducao.model.entity.pcp;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.jproducao.model.entity.ambiente.Perfil;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartaoOP {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_ordemproducao")
	private OrdemProducao ordemProducao;

	@Column(length = 1)
	private String tipo;

	@Column
	private Long sequencia;

	@Column
	private Double quantidade;

	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@Column(name="quantidade_perda")
	private Double quantidadePerda;

	@Column
	private Double peso;

	@ManyToOne
	@JoinColumn(name = "baixado_por")
	private Funcionario baixadoPor;

	@Column(name = "data_peso")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPeso;

	@Column(length = 10)
	private String turno;

	@Column(name = "data_producao")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataProducao;

	@Column(length = 15)
	private String maquina;

	@Column(name = "quantidade_pesada")
	private Double quantidadePesada;

	@ManyToOne
	@JoinColumn(name = "pesado_por")
	private Funcionario pesadoPor;

	@Column(name = "data",updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

	@Column(name = "data_update")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate update;

	@PrePersist
	public void prePersist(){
		setData(LocalDate.now());
		setUpdate(LocalDate.now());
	}
}
