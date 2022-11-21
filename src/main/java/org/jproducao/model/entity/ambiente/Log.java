package org.jproducao.model.entity.ambiente;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.jproducao.model.entity.pcp.Empresa;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Log {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "data_cadastro",updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "id_evento")
	private Evento evento;

	@Column(length = 250, nullable = false)
	@NotNull(message = "{campo.ocorrencia.obrigatorio}")
	private String ocorrencia;

	@PrePersist
	public void prePersist(){
		setDataCadastro(LocalDate.now());
	}
}