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
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "id_unidade")
	private Unidade unidade;

	@ManyToOne
	@JoinColumn(name = "id_embelagem")
	private Embalagem embalagem;

	@Column(length = 255)
	private String observacao;

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