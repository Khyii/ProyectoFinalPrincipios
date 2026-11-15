package uis.edu.proyectoBackend.servicio;

import java.util.List;
import java.util.Optional;

import uis.edu.proyectoBackend.modelo.LoginDto;
import uis.edu.proyectoBackend.modelo.User;

public interface IUserService {
	
	List<User> findAll();
	
	Optional<User> findById(String username);
	
	User create(User user);
	
	User update(User user);
	
	void delete(String username);
	
	String loginValidate(User user);
}
