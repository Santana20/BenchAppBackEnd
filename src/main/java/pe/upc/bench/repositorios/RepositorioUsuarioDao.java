package pe.upc.bench.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import pe.upc.bench.entidades.Usuario;



public interface RepositorioUsuarioDao extends JpaRepository<Usuario, Long>{
public Usuario findByUsername(String username);
	
	@Query("select u from Usuario u where u.username=?1")
	public Usuario findByUsername2(String username);
	
	@Query("SELECT u FROM Usuario u WHERE u.id=:id")
	public Usuario buscarUsuario(@Param("id") Long id);
	
	@Query("select u from Usuario u where u.nombre like ?1%")
	List<Usuario> buscarNombre(String nombre);

}
