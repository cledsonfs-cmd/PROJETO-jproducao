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
public class Representante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 5)
	private String codigo;

	@Column(length = 100)
	private String nome;

	@Column(length = 100)
	private String rua;

	@Column(length = 100)
	private String bairro;

	@Column(length = 100)
	private String municipio;

	@Column(length = 2)
	private String uf;

	@Column(length = 10)
	private String cep;

	@Column(length = 20)
	private String telefone;

	@Column(length = 20)
	private String fax;

	@Column(length = 20)
	private String celular;

	@Column
	private Float percentualComissao;

	@Column(length = 20)
	private Float percentualComissaoSemDesc;

	@Column(length = 25)
	private String email;

	@Column(length = 25)
	private String contaContabil;

	@Column(length = 10)
	private String login;

	@Column(length = 100)
	private String nomeFantasia;

	@Column
	private Boolean ativo;

	@Column(name = "data_criacao",updatable = false)
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