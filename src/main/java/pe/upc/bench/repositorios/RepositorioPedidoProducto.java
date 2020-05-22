package pe.upc.bench.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.upc.bench.entidades.Pedido_Producto;

public interface RepositorioPedidoProducto extends JpaRepository<Pedido_Producto, Long> {

}
