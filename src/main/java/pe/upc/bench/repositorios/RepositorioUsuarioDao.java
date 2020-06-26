package pe.upc.bench.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pe.upc.bench.entidades.Usuario;



public interface RepositorioUsuarioDao extends CrudRepository<Usuario, Long>{
public Usuario findByUsername(String username);
	
	@Query("select u from Usuario u where u.username=?1")
	public Usuario findByUsername2(String username);

}
