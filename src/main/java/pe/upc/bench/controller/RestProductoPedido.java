package pe.upc.bench.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pe.upc.bench.entidades.Pedido_Producto;
import pe.upc.bench.servicios.ServicioProductoPedido;

@RestController
@RequestMapping("/api")
public class RestProductoPedido {
	@Autowired
	private ServicioProductoPedido servicioproductopedido;
	
	//REGISTRAR PEDIDO PRODUCTO
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/RegistrarPP/{codigo}")
	public Pedido_Producto registrarPedidoProducto(@PathVariable(value = "codigo") Long codigo,@RequestBody Pedido_Producto pedido_Producto) {
		Pedido_Producto pp;
		try {
			pp=servicioproductopedido.registrarPedidoProducto(codigo, pedido_Producto);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		return pp;
	}
	
	//MOSTRAR PEDIDOS
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/pedidosProductos")
	public List<Pedido_Producto> listaPedidos(){
		List<Pedido_Producto> pp;
		try {
			pp=servicioproductopedido.mostrarLista();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontraron Detalles Pedidos");
		}
		return pp;
	}
	
	//OBTENER DETALLE PEDIDO
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/otenerPedidoProducto/{codigo}")
	public List<Pedido_Producto> obtenerPeddidoPro(@PathVariable(value = "codigo")Long codigo) {
		List<Pedido_Producto> pp;
		try {
			pp=servicioproductopedido.obtenerPP(codigo);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		return pp;
		
	}
}
