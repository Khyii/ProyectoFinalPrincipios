package uis.edu.proyectoBackend.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name= "guide")
public class Guide {
	
	/******************
	 * Clave primaria *
	 ******************/
	@Id
	@NotBlank(message= "El ID es obligatorio")
	@Size(min = 8, max = 8, message = "El ID debe tener 8 dígitos")
	@Pattern( regexp= "[0-9]*",
              message= "El ID debe estar compuesto de números")
	private String guideId;
	
	/**********************
	 * Username del autor *
	 **********************/
	@NotBlank(message= "El username es obligatorio")
	@Size(min = 3, max = 12, message = "El username debe tener entre 3 y 12 dígitos")
	@Pattern( regexp= "[a-zA-Z0-9][a-zA-Z0-9 ]*[a-zA-Z0-9]",
              message= "El username no puede empezar o terminar en espacios o símbolos especiales")
	@Column(name= "guideAuthor", nullable = false, length = 150)
	private String guideAuthor;
	
	/********
	 * Tema *
	 ********/
	@NotBlank(message= "El tema es obligatorio")
	@Pattern( regexp= "[a-zñA-ZÑ0-9ÁÉÍÓÚáéíóú][a-zñA-ZÑ0-9 _.:,;¨´'¿?!¡ÁÉÍÓÚáéíóú]*[a-zñA-ZÑ0-9_.:,;¨´'¿?!¡ÁÉÍÓÚáéíóú]",
	          message= "El tema no puede empezar o terminar en espacios o símbolos especiales")
	@Size(min= 2, max= 40, message="El título debe tener entre 2 y 40 caracteres")
	@Column(name= "guideTopic", nullable = false, length = 40)
	private String guideTopic;
	
	/*********************
	 * Fecha publicación *
	 *********************/
	@NotBlank(message= "La fecha es obligatoria")
	@Pattern( regexp= "[0-3][0-9]/[0-1][0-9]/20[0-9][0-9]",
	          message= "El título debe seguir la estructura DD/MM/AAAA")
	@Column(name= "publishDate", nullable = false, length = 10)
	private String publishDate;
	
	/***************
	 * Título guía *
	 ***************/
	@NotBlank(message= "El título es obligatorio")
	@Pattern( regexp= "[a-zñA-ZÑ0-9ÁÉÍÓÚáéíóú][a-zñA-ZÑ0-9 _.:,;¨´'¿?!¡ÁÉÍÓÚáéíóú]*[a-zñA-ZÑ0-9_.:,;¨´'¿?!¡ÁÉÍÓÚáéíóú]",
	          message= "El título no puede empezar o terminar en espacios o símbolos especiales")
	@Size(min= 2, max= 150, message="El título debe tener entre 2 y 150 caracteres")
	@Column(name= "guideTitle", nullable = false, length = 150)
	private String guideTitle;
	
	/***************
	 * Cuerpo guía *
	 ***************/
	@NotBlank(message= "El cuerpo es obligatorio")
	@Pattern( regexp= "[a-zñA-ZÑ0-9ÁÉÍÓÚáéíóú][a-zñA-ZÑ0-9 _.:,;¨´'¿?!¡ÁÉÍÓÚáéíóú]*[a-zÑA-ZÑ0-9_.:,;¨´'¿?!¡ÁÉÍÓÚáéíóú]",
	          message= "El cuerpo no puede empezar o terminar en espacios o símbolos especiales")
	@Size(min= 2, max= 1000, message="El título debe tener entre 2 y 1000 caracteres")
	@Column(name= "guideBody", nullable = false, length = 1000)
	private String guideBody;

	public String getGuideId() {
		return guideId;
	}

	public void setGuideId(String guideId) {
		this.guideId = guideId;
	}

	public String getGuideAuthor() {
		return guideAuthor;
	}

	public void setGuideAuthor(String guideAuthor) {
		this.guideAuthor = guideAuthor;
	}

	public String getGuideTopic() {
		return guideTopic;
	}

	public void setGuideTopic(String guideTopic) {
		this.guideTopic = guideTopic;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getGuideTitle() {
		return guideTitle;
	}

	public void setGuideTitle(String guideTitle) {
		this.guideTitle = guideTitle;
	}

	public String getGuideBody() {
		return guideBody;
	}

	public void setGuideBody(String guideBody) {
		this.guideBody = guideBody;
	}
}
