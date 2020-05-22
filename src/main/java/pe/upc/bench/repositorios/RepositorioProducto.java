package pe.upc.bench.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import pe.upc.bench.entidades.Producto;

public interface RepositorioProducto extends JpaRepository<Producto, Long>{
	@Query("SELECT c FROM Producto c WHERE c.codigo=:codigo")
	Producto buscarProducto(@Param("codigo") Long codigo);
	
	@Query("select c from Producto c where c.nombre like %?1%")
	List<Producto> buscarNombre(String nombre);

	@Query("select c from Producto c where c.precio between ?1 and ?2")
	List<Producto> buscarporPrecio(double p1, double p2);
}
