package pe.upc.bench.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.upc.bench.entidades.Usuario;
import pe.upc.bench.repositorios.RepositorioUsuarioDao;

@Service
public class UsuarioServiceDatos {
	
	@Autowired
	private RepositorioUsuarioDao usuarioDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	//Registrar Usuario
	public Usuario registrar(Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuarioDao.save(usuario);
	}

}
