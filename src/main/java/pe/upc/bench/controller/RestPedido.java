package pe.upc.bench.controller;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pe.upc.bench.entidades.Pedido;
import pe.upc.bench.entidades.Pedido_Producto;
import pe.upc.bench.servicios.ServicioPedido;

@RestController
@RequestMapping("/api")
public class RestPedido {
	@Autowired
	private ServicioPedido serviciopedido;
	
	//REGISTRAR PEDIDO
	@PostMapping("/pedido/{dni}")
	public Pedido registrarPedido(@PathVariable(value = "dni") String dni, @RequestBody Pedido pedido) {
		Pedido p;
		try {
			p=serviciopedido.realizarPedido(dni, pedido);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No es posible realizar el Pedido");
		}
		
		return p;
	}
	
	//ACTUALIZAMOS FECHARECEPCIONP DEL PEDIDO
	@PutMapping("/actualizarPedido/{codigo}")
	public Pedido actualizarFechaRecepcion(@PathVariable(value="codigo")Long codigo, @RequestBody Date fechaRecepcion)
	{
		Pedido p = null;
		try {
			p=serviciopedido.actualizarFechaRecepcion(codigo, fechaRecepcion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No es posible actualizar el Pedido");
		}
		
		return p;
	}
	
	//BUSCAR PEDIDO
	@GetMapping("/pedido/{codigo}")
	public Pedido obtenerPedido(@PathVariable(value = "codigo") Long codigo) {
		Pedido p=null;
		try {
			p=serviciopedido.obtenerPedido(codigo);
		} catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		return p;
	}
	
	//LISTAR PEDIDOS POR CLIENTE
	@GetMapping("/listarPedidosActivosdeCliente/{codigo}")
	public List<Pedido> listarPedidosActivosdeCliente(@PathVariable(value="codigo") Long codigo)
	{
		List<Pedido> pedidos=null;
		try {
			pedidos=serviciopedido.listarPedidodeCliente(codigo);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontraron Pedidos");
		}
		return pedidos;
	}
	
	//OBTENER TODOS LOS PEDIDOS
	@GetMapping("/pedidos")
	public List<Pedido> listaPedidos(){
		List<Pedido> pedidos=null;
		try {
			pedidos=serviciopedido.obtenerPedidos();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontraron Pedidos");
		}
		return pedidos;
	}
	
	
	//OBTENER PEDIDOS RANGO PRECIOS
	@GetMapping("/pedidosRango/{ini}/{fin}")
	public List<Pedido> rangoPreciosPedidos(@PathVariable(value = "ini") double ini,@PathVariable(value = "fin") double fin){
		List<Pedido> pedidos=null;
		try {
			pedidos=serviciopedido.buscarRangoPrecios(ini, fin);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		return pedidos;
	}
	
	
	
}
