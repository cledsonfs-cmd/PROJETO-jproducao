package org.jproducao.model.entity.pcp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empresa{
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 8)
	@NotEmpty(message = "{campo.codigo_empresa.obrigatorio}")
	private String codigoEmpresa;

	@Column(name = "razao_social",nullable = false, length = 50)
	@NotEmpty(message = "{campo.razao_social.obrigatorio}")
	private String razaoSocial;

	@Column(name = "nome_reduzido",nullable = false, length = 10)
	@NotEmpty(message = "{campo.nome_reduzido.obrigatorio}")
	private String nomeReduzido;

	@Column(nullable = false)
	@NotNull(message = "{campo.cnpj.obrigatorio}")
	@CNPJ(message = "{campo.cnpj.invalido}")
	private String cnpj;

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

	@Column(length = 100)
	private String pasta;

	@Column(length = 100)
	private String pastaHistorico;

	@Column(length = 100)
	private String inscricaoEstadual;

	@Column(length = 100)
	private String codigoBarras;

	@Column(name = "data_cadastro",updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@PrePersist
	public void prePersist(){
		setDataCadastro(LocalDate.now());
	}
}