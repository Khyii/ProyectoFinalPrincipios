package uis.edu.proyectoBackend.servicio;

import java.util.List;
import java.util.Optional;
import uis.edu.proyectoBackend.modelo.Guide;

public interface IGuideService {
	
	List<Guide> findAll();
	
	Optional<Guide> findById(String guideId);
	
	Guide create(Guide guide);
	
	Guide update(Guide guide);
	
	void delete(String guideId);
}
