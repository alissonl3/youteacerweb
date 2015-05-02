package br.com.youteacherweb.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity(name="formulario")
public class Formulario  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="pergunta")
	private String pergunta;
	
	@Column(name="resposta_A")
	private String resposta_A;
	
	@Column(name="resposta_B")
	private String resposta_B;
	
	@Column(name="resposta_C")
	private String resposta_C;
	
	@Column(name="resposta_D")
	private String resposta_D;
	
	@Column(name="resposta_E")
	private String resposta_E;
	
	@Column(name="resposta_certa")
	private String resposta_certa;
	//mappedBy indica qual tabela será a dona do relacionamento, no nosso caso setá a tabela Video, ela irá ter a fk da tabela Formulario
	@OneToOne(mappedBy="formulario")
	private Video video;
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	
	
	public String getPergunta() {
		return pergunta;
	}
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	public String getResposta_A() {
		return resposta_A;
	}
	public void setResposta_A(String resposta_A) {
		this.resposta_A = resposta_A;
	}
	public String getResposta_B() {
		return resposta_B;
	}
	public void setResposta_B(String resposta_B) {
		this.resposta_B = resposta_B;
	}
	public String getResposta_C() {
		return resposta_C;
	}
	public void setResposta_C(String resposta_C) {
		this.resposta_C = resposta_C;
	}
	public String getResposta_D() {
		return resposta_D;
	}
	public void setResposta_D(String resposta_D) {
		this.resposta_D = resposta_D;
	}
	public String getResposta_E() {
		return resposta_E;
	}
	public void setResposta_E(String resposta_E) {
		this.resposta_E = resposta_E;
	}
	public String getResposta_certa() {
		return resposta_certa;
	}
	public void setResposta_certa(String resposta_certa) {
		this.resposta_certa = resposta_certa;
	}
	

}
