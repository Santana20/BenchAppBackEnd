package pe.upc.bench.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.upc.bench.entidades.Pedido_Producto;

public interface RepositorioPedidoProducto extends JpaRepository<Pedido_Producto, Long> {
	@Query("select c from Pedido_Producto c where c.pedido.codigo=:codigo ")
	List<Pedido_Producto> obtenerPP(@Param("codigo") Long codigo);

}
