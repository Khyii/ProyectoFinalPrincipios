package uis.edu.proyectoBackend.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import uis.edu.proyectoBackend.modelo.LoginDto;
import uis.edu.proyectoBackend.modelo.User;
import uis.edu.proyectoBackend.repositorio.IUserRepository;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private IUserRepository userRepo;

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public Optional<User> findById(String username) {
		return userRepo.findById(username);
	}

	@Override
	public User create(User usuario) {
		return userRepo.save(usuario);
	}

	@Override
	public User update(User usuario) {
		return userRepo.save(usuario);
	}

	@Override
	public void delete(String username) {
		userRepo.deleteById(username);
	}

	@Override
	public String loginValidate(User user) {
		
		String namama = user.getUsername();
		System.err.println(namama);
		
		Optional<User> suspect = userRepo.findById(namama);
		System.err.println(suspect);
		
		if(suspect.isPresent()) {
			
			User guilty = suspect.get();
			
			if(guilty.getPassword().equals(user.getPassword())) {
				   return "1";} //Login correcto: Aceptación
			else { return "0";} //Login correcto: Rechazo
		}
		else {
			System.err.println("Error");
			return "2"; //Login incorrecto: Error
		}
		
	}
}
