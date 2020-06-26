package pe.upc.bench.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import pe.upc.bench.entidades.Cliente2;
import pe.upc.bench.entidades.Region;

public interface RepositorioClienteDao extends JpaRepository<Cliente2, Long>{

	@Query("from Region")
	public List<Region> findAllRegiones();
}
