package pe.upc.bench.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import pe.upc.bench.entidades.Producto_Oferta;
import pe.upc.bench.servicios.ServicioProductoOferta;

@RestController
@RequestMapping("/api")
public class RestProductoOferta {
	@Autowired
	private ServicioProductoOferta servicioproductooferta;
	
	//REGISTRAR PRODUCTO OFERTA
	@Secured("ROLE_ADMIN")
	@PostMapping("/RegistrarPO/{codigo}/{codigo2}")
	public Producto_Oferta registrarProductoOferta(@PathVariable(value = "codigo") Long codigo,@PathVariable(value = "codigo2") Long codigo2,@RequestBody Producto_Oferta producto_Oferta) {
		Producto_Oferta po;
		try {
			po=servicioproductooferta.registrarProductoOferta(codigo, codigo2, producto_Oferta);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no es posible realizar pedido producto");
		}
		return po;
	}
	
	//MOSTRAR LISTA
	@GetMapping("/mostrarProductoOferta")
	public List<Producto_Oferta> mostrarLista(){
		List<Producto_Oferta> po;
		try {
			po=servicioproductooferta.mostrarLista();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontraron Promociones");
		}
		return po;
	}
	

	
	
	
	//buscar mayor
	@Secured("ROLE_ADMIN")
	@GetMapping("/buscarRangoFecha/{fecha}/{fechafin}")
	public List<Producto_Oferta> buscarmayor(@PathVariable(value = "fecha") String fecha,@PathVariable(value = "fechafin") String fechafin){
		List<Producto_Oferta> po;
		try {
			po=servicioproductooferta.buscarRangoFecha(fecha, fechafin);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		return po;
	}
	
	
	//eliminar producto oferta
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/eliminarPO/{codigo}")
	public Producto_Oferta eliminarProductoOferta(@PathVariable(value = "codigo") Long codigo) {
		Producto_Oferta po;
		try {
			po=servicioproductooferta.eliminar(codigo);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no se pudo eliminar");
		}
		return po;
	}
}
