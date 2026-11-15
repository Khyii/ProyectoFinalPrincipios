package uis.edu.proyectoBackend.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import uis.edu.proyectoBackend.modelo.User;

public interface IUserRepository extends JpaRepository<User, String>{
	
}
