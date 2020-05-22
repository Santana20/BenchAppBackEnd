package pe.upc.bench.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.upc.bench.entidades.Pedido;
import pe.upc.bench.entidades.Pedido_Producto;
import pe.upc.bench.entidades.Producto;
import pe.upc.bench.repositorios.RepositorioPedido;
import pe.upc.bench.repositorios.RepositorioPedidoProducto;
import pe.upc.bench.repositorios.RepositorioProducto;

@Service
public class ServicioProductoPedido {
	@Autowired
	private RepositorioPedidoProducto repositorioPedidoProducto;

	@Autowired
	private RepositorioPedido repositoriopedido;
	
	@Autowired
	private RepositorioProducto repositorioproducto;
	
	//REGISTRAR PEDIDO PRODUCTO
	@Transactional(rollbackFor = Exception.class)
	public Pedido_Producto registrarPedidoProducto(Long codigo,Long codigo2,Pedido_Producto pedido_Producto) throws Exception {
		Pedido p;
		p=repositoriopedido.buscarPedido(codigo);
		if (p==null)throw new Exception("pedido no encontrado");
	    pedido_Producto.setPedido(p);
	    
	    Producto producto;
	    producto=repositorioproducto.buscarProducto(codigo2);
		if(producto==null)throw new Exception("producto no encontrado");
	    
	    pedido_Producto.setProducto(producto);
	    return repositorioPedidoProducto.save(pedido_Producto);
	}
	//MOSTRAR LISTA
	public List<Pedido_Producto> mostrarLista() {
		return repositorioPedidoProducto.findAll();
	}
	
}
