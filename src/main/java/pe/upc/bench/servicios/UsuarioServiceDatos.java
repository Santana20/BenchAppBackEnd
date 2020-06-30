package pe.upc.bench.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import pe.upc.bench.entidades.Role;
import pe.upc.bench.entidades.Usuario;
import pe.upc.bench.repositorios.RepositorioUsuarioDao;

@Service
public class UsuarioServiceDatos {
	
	@Autowired
	private RepositorioUsuarioDao usuarioDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	//Registrar Usuario
	@Transactional
	public Usuario registrar(Usuario usuario) {
		Role role = new Role();
		role.setId(1L);
        usuario.agregar(role);
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuarioDao.save(usuario);
	}
	
	//OBTENER UN SOLO USUARIO
			public Usuario obtenerUsuario(Long id ) throws Exception {
				Usuario c;
				c=usuarioDao.buscarUsuario(id);
				if(c==null) throw new Exception("entidad no encontrada");
				return c;
				
			}
			
			//RETORNAR USUARIO POR USERNAME
			public Usuario retornarUsuario(String username) throws Exception {
				Usuario c;
				c=usuarioDao.retornaUsuarioUserName(username);
				if(c==null) throw new Exception("entidad no encontrada");
				return c;
			}
			
			
			
			
			//OBTENER LISTA DE USUARIOS
			public List<Usuario> obtenerUsuarios(){
				return usuarioDao.findAll();
			}
			
			
			//ELIMINAR USUARIO
			public Usuario borrarCliente(Long id) throws Exception {
				Usuario c;
				c=obtenerUsuario(id);
				if(c.getId()!=null) {
					usuarioDao.delete(c);
				}else {
					throw new Exception();
				}
				return c;
			}
			
			
			//BUSCAR POR NOMBRE USUARIO
			public List<Usuario> buscarNombre(String nombre) throws Exception{
				List<Usuario> usuarios;
				usuarios= usuarioDao.buscarNombre(nombre);
				if(usuarios==null) throw new Exception("entidades no encontradas");
				return usuarios;
				
			}
			
			//ACTUALIZAR USUARIO
			public Usuario actualizarCliente(Usuario cliente,Long id) {
				Usuario client;
				client=usuarioDao.buscarUsuario(id);
				if(cliente.getNombre()!=null) {
					client.setNombre(cliente.getNombre());
				}
				if(cliente.getEmail()!=null) {
					client.setEmail(cliente.getEmail());
				}
				if(cliente.getApellido()!=null) {
					client.setApellido(cliente.getApellido());
				}
				
				return usuarioDao.save(client);
				
			}

}
