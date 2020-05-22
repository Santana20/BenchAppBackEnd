package pe.upc.bench.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.upc.bench.entidades.Pizzeria;

public interface RepositorioPizzeria extends JpaRepository<Pizzeria, Long>{
	
	@Query("SELECT c FROM Pizzeria c WHERE c.codigo=:codigo")
	Pizzeria obtenerPizzeria(@Param(value = "codigo") Long codigo);

}
