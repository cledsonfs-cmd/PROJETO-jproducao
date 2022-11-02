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
public class Almoxarifado{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "update")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate update;

	@ManyToOne
	@JoinColumn(name = "id_materia_prima")
	private MateriaPrima materiaPrima;

	@Column(name = "data_cadastro",updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@PrePersist
	public void prePersist(){
		setUpdate(LocalDate.now());
		setDataCadastro(LocalDate.now());
	}
	
}
