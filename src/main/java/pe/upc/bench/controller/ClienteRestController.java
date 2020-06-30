package pe.upc.bench.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import pe.upc.bench.entidades.Producto;

import pe.upc.bench.entidades.Role;
import pe.upc.bench.entidades.Usuario;

import pe.upc.bench.servicios.IUploadFileService;
import pe.upc.bench.servicios.ServicioProducto;
import pe.upc.bench.servicios.UsuarioService;
import pe.upc.bench.servicios.UsuarioServiceDatos;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	
	@Autowired
	private IUploadFileService uploadService;
	
	@Autowired
	private UsuarioServiceDatos usuarioServiceDatos;
	
	//REGISTRAR USUARIO
	
	@PostMapping("/RegistrarUsuario")
	public Usuario registrarUsuario(@RequestBody Usuario usuario) {
		return usuarioServiceDatos.registrar(usuario);
	}
	
	//REGISTRAR ADMIN
	@Secured("ROLE_ADMIN")
	@PostMapping("/RegistrarAdmin")
	public Usuario registrarAdmin(@RequestBody Usuario usuario) {
		return usuarioServiceDatos.registrarAdmin(usuario);
	}
	//RETORNAR USUARIO POR USERNAME
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/retonarUsuario/{username}")
	public Usuario retonarUsuario(@PathVariable(value = "username")String username) {
		Usuario c=null;
		try {
			c=usuarioServiceDatos.retornarUsuario(username);
		} catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		return c;
	}
	
	//OBTENER UN USUARIO
	    @Secured("ROLE_ADMIN")
		@GetMapping("/cliente/{id}")
		public Usuario obtenerCliente(@PathVariable(value = "dni") Long id) {
			Usuario c=null;
			try {
				c=usuarioServiceDatos.obtenerUsuario(id);
			} catch (Exception e) {
				
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
			}
			return c;
		}
		
		//OBTENER LISTA USUARIOS
	    @Secured("ROLE_ADMIN")
		@GetMapping("/clientes")
		public List<Usuario> obtenerClientes(){
			List<Usuario> c;
			try {
				c=usuarioServiceDatos.obtenerUsuarios();
			} catch (Exception e) {
				
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontraron Clientes");
			}
			return c;
		}
		
		
		//ELIMINAR USUARIOS
	    @Secured("ROLE_ADMIN")
		@DeleteMapping("/borrar/{id}")
		public Usuario borrarCliente(@PathVariable(value = "dni") Long id) {
			Usuario c;
			try {
				c=usuarioServiceDatos.borrarCliente(id);
			}catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no se puede borrar");
			}
			return c;
		}
		
		//ACTUALIZAR CLIENTE
	    @Secured("ROLE_ADMIN")
		@PutMapping("/actualizarCliente/{id}")
		public Usuario actualizarCliente(@RequestBody Usuario cliente,@PathVariable(value = "id") Long id) {
			Usuario client=null;
			try {
				client=usuarioServiceDatos.actualizarCliente(cliente, id);
			}catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no es posible actualizar");
			}
			return client;
		}
		//BUSCAR POR NOMBRE USUARIOS
	    @Secured("ROLE_ADMIN")
		@GetMapping("/buscarNombre/{nombre}")
		public List<Usuario> buscarNombreClientes(@PathVariable(value = "nombre") String nombre){
			List<Usuario> client =null;
			try {
				client=usuarioServiceDatos.buscarNombre(nombre);
			}catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
			}
			return client;
		}
	
	// private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
     /*
	@GetMapping("/clientes")
	public List<Cliente2> index() {
		return clienteService.findAll();
	}
	
	
	
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente2> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return clienteService.findAll(pageable);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Cliente2 cliente = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			cliente = clienteService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente2>(cliente, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente2 cliente, BindingResult result) {
		
		Cliente2 clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			clienteNew = clienteService.save(cliente);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente ha sido creado con éxito!");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente2 cliente, BindingResult result, @PathVariable Long id) {

		Cliente2 clienteActual = clienteService.findById(id);

		Cliente2 clienteUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (clienteActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setCreateAt(cliente.getCreateAt());
			clienteActual.setRegion(cliente.getRegion());

			clienteUpdated = clienteService.save(clienteActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido actualizado con éxito!");
		response.put("cliente", clienteUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			Cliente2 cliente = clienteService.findById(id);
			String nombreFotoAnterior = cliente.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
			
		    clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Cliente2 cliente = clienteService.findById(id);
		
		if(!archivo.isEmpty()) {

			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = cliente.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
						
			cliente.setFoto(nombreArchivo);
			
			clienteService.save(cliente);
			
			response.put("cliente", cliente);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){

		Resource recurso = null;
		
		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones(){
		return clienteService.findAllRegiones();
	}*/

}
