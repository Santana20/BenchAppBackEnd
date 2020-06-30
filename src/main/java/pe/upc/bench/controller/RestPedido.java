package pe.upc.bench.controller;

import java.util.Date;
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

import pe.upc.bench.entidades.Pedido;
import pe.upc.bench.servicios.ServicioPedido;

@RestController
@RequestMapping("/api")
public class RestPedido {
	@Autowired
	private ServicioPedido serviciopedido;
	
	//REGISTRAR PEDIDO
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/pedido/{username}")
	public Pedido registrarPedido(@PathVariable(value = "username") String username, @RequestBody Pedido pedido) {
		Pedido p;
		try {
			p=serviciopedido.realizarPedido(username, pedido);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No es posible realizar el Pedido");
		}
		
		return p;
	}
	
	//ACTUALIZAMOS FECHARECEPCIONP DEL PEDIDO
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
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
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
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
	
	//LISTAR PEDIDOS ACTIVOS POR CLIENTE
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/listarPedidosActivosdeCliente/{username}")
	public List<Pedido> listarPedidosActivosdeCliente(@PathVariable(value="username") String username)
	{
		List<Pedido> pedidos=null;
		try {
			pedidos=serviciopedido.listarPedidosActivosdeCliente(username);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontraron Pedidos");
		}
		return pedidos;
	}
	
	//LISTAR PEDIDOS ANTIGUOS POR CLIENTE
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/listarPedidosPasadosdeCliente/{username}")
	public List<Pedido> listarPedidosPasadosdeCliente(@PathVariable(value="username") String username)
	{
		List<Pedido> pedidos=null;
		try {
			pedidos=serviciopedido.listarPedidosPasadosdeCliente(username);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontraron Pedidos");
		}
		return pedidos;
	}
	
	//LISTAR TODOS LOS PEDIDOS ACTIVOS
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/listarPedidosActivos")
	public List<Pedido> listarPedidosActivos()
	{
		List<Pedido> pedidos=null;
		try {
			pedidos=serviciopedido.listarPedidosActivos();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontraron Pedidos");
		}
		return pedidos;
	}
	
	//LISTAR TODOS LOS PEDIDOS PASADOS
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/listarPedidosPasados")
	public List<Pedido> listarPedidosPasados()
	{
		List<Pedido> pedidos=null;
		try {
			pedidos=serviciopedido.listarPedidosPasados();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontraron Pedidos");
		}
		return pedidos;
	}
	
	
	//OBTENER TODOS LOS PEDIDOS
	@Secured("ROLE_ADMIN")
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
	@Secured("ROLE_ADMIN")
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
