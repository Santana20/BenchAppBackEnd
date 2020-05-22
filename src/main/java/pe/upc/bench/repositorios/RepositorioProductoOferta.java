package pe.upc.bench.repositorios;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.upc.bench.entidades.Producto_Oferta;

public interface RepositorioProductoOferta extends JpaRepository<Producto_Oferta, Long>{
	
	@Query("select c from Producto_Oferta c where c.codigo=:codigo ")
	Producto_Oferta buscarProductoOferta(@Param("codigo") Long codigo);

	
	
	
	@Query("select c from Producto_Oferta c where c.fecha_inicio between :fechaini and :fechafin and c.fecha_fin between  :fechaini and :fechafin ")
	List<Producto_Oferta> BuscarRangoFecha(@Param("fechaini") Date fechaini,@Param("fechafin") Date fechafin);
	
}
