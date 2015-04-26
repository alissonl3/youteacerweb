package br.com.youteacherweb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="entidadeUsuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="idUsuario")
	private Integer id;
	@Column(name="nome")
	private String nome;
	@Column(name="email")
	private String email;
	@Temporal(TemporalType.DATE)
	@Column(name="dataNascimento")
	private Date dataNascimento;
	@Column(name="senha")
	private String senha;
	@Column(name="adm")
	private String adm;
	
	@OneToMany(mappedBy="usuario", targetEntity=Video.class,fetch = FetchType.LAZY)
	private List<Video> videos;
	

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public String getAdm() {
		return adm;
	}

	public void setAdm(String adm) {
		this.adm = adm;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
