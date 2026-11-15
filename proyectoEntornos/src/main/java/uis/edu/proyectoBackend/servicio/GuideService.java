package uis.edu.proyectoBackend.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import uis.edu.proyectoBackend.modelo.Guide;
import uis.edu.proyectoBackend.repositorio.IGuideRepository;

@Service
public class GuideService implements IGuideService{
	
	@Autowired
	private IGuideRepository guideRepo;

	@Override
	public List<Guide> findAll() {
		return guideRepo.findAll();
	}

	@Override
	public Optional<Guide> findById(String guideId) {
		return guideRepo.findById(guideId);
	}

	@Override
	public Guide create(Guide guide) {
		return guideRepo.save(guide);
	}

	@Override
	public Guide update(Guide guide) {
		return guideRepo.save(guide);
	}

	@Override
	public void delete(String guideId) {
		guideRepo.deleteById(guideId);
	}
}
