package org.jproducao.model.entity.pc;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.jproducao.model.entity.pcp.Unidade;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumoSetor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100)
	private String descricao;

	@Column
	private Double valor;

	@ManyToOne
	@JoinColumn(name = "id_unidade")
	private Unidade unidade;

	@Column(name = "data_cadastro",updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@Column(name = "data_update")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate update;

	@PrePersist
	public void prePersist(){
		setDataCadastro(LocalDate.now());
		setUpdate(LocalDate.now());
	}
	
}
