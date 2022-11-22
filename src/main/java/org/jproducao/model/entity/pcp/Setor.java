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
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@Column(length = 4)
	private String codigo;

	@Column(length = 100)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_tipo_setor")
	private TipoSetor tipoSetor;

	@Column(length = 3)
	private String abreviado;

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
