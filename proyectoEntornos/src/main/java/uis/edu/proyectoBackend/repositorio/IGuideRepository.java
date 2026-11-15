package uis.edu.proyectoBackend.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import uis.edu.proyectoBackend.modelo.Guide;

public interface IGuideRepository extends JpaRepository<Guide, String>{
	
}
