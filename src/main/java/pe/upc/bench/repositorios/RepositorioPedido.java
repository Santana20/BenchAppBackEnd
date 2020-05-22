package pe.upc.bench.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import pe.upc.bench.entidades.Pedido;

public interface RepositorioPedido extends JpaRepository<Pedido, Long>{

	@Query("SELECT c FROM Pedido c WHERE c.codigo=:codigo")
	Pedido buscarPedido(@Param("codigo") Long codigo);
	
	@Query("select c from Pedido c where c.costo_total between :ini and :fin")
	List<Pedido> buscarRangocCosto(@Param("ini") double ini,@Param("fin") double fin);
}
