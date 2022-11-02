package org.jproducao.model.entity.pcp;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 10)
	@NotEmpty(message = "{campo.codigo_cliente.obrigatorio}")
	private String codigo;

	@Column(nullable = false, length = 150)
	@NotEmpty(message = "{campo.nome_cliente.obrigatorio}")
	private String nome;

	@Column(length = 150)
	private String rua;

	@Column(length = 150)
	private String bairro;

	@Column(length = 100)
	private String municipio;

	@Column(length = 12)
	private String uf;

	@Column(length = 15)
	private String cep;

	@Column(length = 20)
	private String telefone;

	@Column(length = 20)
	private String fax;

	@Column(length = 20)
	private String celular;

	@Column(name="percentual_comissao")
	private Double percentualComissao;

	@Column(name="percentual_comissao_sem_desconto")
	private Double percentualComissaoSemDesc;

	@Column(length = 30)
	private String email;

	@Column(name="conta_contabil", length = 50)
	private String contaContabil;

	@Column(name = "nome_fantasia", length = 150)
	private String nomeFantasia;

	@Column
	private Boolean ativo;

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

