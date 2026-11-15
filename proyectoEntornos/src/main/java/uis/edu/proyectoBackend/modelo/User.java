package uis.edu.proyectoBackend.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {
	
	/******************
	 * Clave primaria *
	 ******************/
	@Id
	@NotBlank(message= "El username es obligatorio")
	@Size(min = 3, max = 12, message = "El username debe tener entre 3 y 12 dígitos")
	@Pattern( regexp= "[a-zA-Z0-9][a-zA-Z0-9 ]*[a-zA-Z0-9]",
              message= "El username no puede empezar o terminar en espacios o símbolos especiales")
	private String username;

	/************************
	 * Nombre (y apellidos) *
	 ************************/
	@NotBlank(message= "El nombre es obligatorio")
	@Pattern( regexp= "[a-zA-Z0-9][a-zA-Z0-9 ]*[a-zA-Z0-9]",
	          message= "El nombre no puede empezar o terminar en espacios o símbolos especiales")
	@Size(min= 2, max= 150, message="El nombre debe tener entre 2 y 150 caracteres")
	@Column(name= "nombre", nullable = false, length = 150)
	private String nombre;

	/**************
	 * Contraseña *
	 **************/
	@NotBlank(message= "La contraseña es obligatoria")
	@Pattern( regexp= "[a-zA-Z0-9_][a-zA-Z0-9_]*",
			  message= "La contraseña debe estar compuesta de letras, números y guiones bajos")
	@Size(min= 8, max= 26, message="La contraseña deben tener entre 8 y 26 caracteres")
	@Column(name= "password", nullable = false, length = 26)
	private String password;

	/**********************
	 * Puede crear guías? *
	 **********************/
	@NotNull( message= "Se deben especificar los permisos de creación")
	@Column(name= "canCreate", nullable = false)
	private Boolean canCreate;
	
	/***********************
	 * Puede editar guías? *
	 ***********************/
	@NotNull( message= "Se deben especificar los permisos de edición")
	@Column(name= "canEdit", nullable = false)
	private Boolean canEdit;

	/**********************
	 * Puede remover guías? *
	 **********************/
	@NotNull( message= "Se deben especificar los permisos de eliminación")
	@Column(name= "canDelete", nullable = false)
	private Boolean canDelete;

	/**********************
	 * Correo electrónico *
	 **********************/
	@NotBlank(message= "La dirección de correo electrónica es obligatoria")
	@Email(message= "El correo no sigue un formato válido")
	@Size(min= 6, max= 70, message="El correo debe tener entre 6 y 70 caracteres")
	@Column(name= "email", nullable = false, length = 70)
	private String email;

	/*********************
	 * Número teléfónico *
	 *********************/
	@NotBlank(message= "El número de teléfono es obligatorio")
	@Size(min= 10, max= 10, message="El número de teléfono debe tener 10 caracteres")
	@Pattern( regexp= "[0-9]*", message="El número de teléfono sólo puede contener números")
	@Column(name= "telefono", nullable = true, length = 10)
	private String telefono;

	/*************************
	 * Dirección de vivienda *
	 *************************/
	@Size(min= 0, max= 150, message="La dirección no debe tener más de 150 caracteres")
	@Column(name= "direccion", nullable = true, length = 150)
	private String direccion;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getCanCreate() {
		return canCreate;
	}

	public void setCanCreate(Boolean canCreate) {
		this.canCreate = canCreate;
	}

	public Boolean getCanEdit() {
		return canEdit;
	}

	public void setCanEdit(Boolean canEdit) {
		this.canEdit = canEdit;
	}

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}