package org.jproducao.model.entity.pc;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.jproducao.model.entity.pcp.Produto;
import org.jproducao.model.entity.pcp.Setor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PontoControle{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_locacao")
	private Setor setor;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_ponto_controle")
	private List<PontoControleRegistro> registro;

	@Column(name = "data_cadastro",updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@PrePersist
	public void prePersist(){
		setDataCadastro(LocalDate.now());
	}

}
