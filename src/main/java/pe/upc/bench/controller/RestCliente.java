package pe.upc.bench.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pe.upc.bench.entidades.Cliente;
import pe.upc.bench.entidades.Pedido;
import pe.upc.bench.servicios.ServicioCliente;

@RestController
@RequestMapping("/api")
public class RestCliente {

	@Autowired
	private ServicioCliente serviciocliente;
	
	//REGISTRAR UN CLIENTE
	@PostMapping("/cliente")
	public Cliente registrarcliente(@RequestBody Cliente cliente) {
		Cliente c;
		try {
			c=serviciocliente.registrarCliente(cliente);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no se puede resgistrar");
		}
		return c;
	}
	
	//OBTENER UN CLIENTE
	@GetMapping("/cliente/{dni}")
	public Cliente obtenerCliente(@PathVariable(value = "dni") String dni) {
		Cliente c=null;
		try {
			c=serviciocliente.obtenerCliente(dni);
		} catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		return c;
	}
	
	//OBTENER LISTA CLIENTES
	@GetMapping("/clientes")
	public List<Cliente> obtenerClientes(){
		return serviciocliente.obtenerClientes();
	}
	
	
	//ELIMINAR CLIENTE
	@DeleteMapping("/borrar/{dni}")
	public Cliente borrarCliente(@PathVariable(value = "dni") String dni) {
		Cliente c;
		try {
			c=serviciocliente.borrarCliente(dni);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no se puede borrar");
		}
		return c;
	}
	
	//ACTUALIZAR CLIENTE
	@PutMapping("/actualizarCliente/{dni}")
	public Cliente actualizarCliente(@RequestBody Cliente cliente,@PathVariable(value = "dni") String dni) {
		Cliente client=null;
		try {
			client=serviciocliente.actualizarCliente(cliente, dni);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no es posible actualizar");
		}
		return client;
	}
	//BUSCAR POR NOMBRE CLIENTE
	@GetMapping("/buscarNombre/{nombre}")
	public List<Cliente> buscarNombreClientes(@PathVariable(value = "nombre") String nombre){
		List<Cliente> client =null;
		try {
			client=serviciocliente.buscarNombre(nombre);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		return client;
	}
	//BUSCAR PEDIDOS DE UN CLIENTE
	@GetMapping("/pedidosCliente/{dni}")
	public List<Pedido> listaPedidoCliente(@PathVariable(value = "dni") String dni){
		List<Pedido> pedidos =null;
		try {
			pedidos=serviciocliente.obtenerPedidosCliente(dni);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no se encontro Pedidos");
		}
		return pedidos;
	}
	
}
