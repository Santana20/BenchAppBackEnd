package pe.upc.bench.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import pe.upc.bench.entidades.Producto;
import pe.upc.bench.servicios.ServicioProducto;

@RestController
@RequestMapping("/api")
public class RestProducto {

	@Autowired
	private ServicioProducto servicioproducto;
	
	//REGISTRAR PRODUCTO
	@PostMapping("/producto/{codigo}")
	public Producto registrarProducto(@PathVariable(value = "codigo") Long codigo,@RequestBody Producto producto) {
		Producto p;
		try {
			p=servicioproducto.registrarProducto(codigo, producto);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no se puede registrar producto");
		}
		return p;
	}
	
	//ACTUALIZAR PRODUCTO
	@PostMapping("/actualizarProducto/{codigo}")
	public Producto actualizarProducto(@PathVariable(value = "codigo") Long codigo,@RequestBody Producto producto) {
		Producto p;
		try {
			p=servicioproducto.actualizarProducto(codigo, producto);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no se puede registrar producto");
		}
		return p;
	}
	
	
	//ELIMINAR PRODUCTO
	@DeleteMapping("/eliminarProducto/{codigo}")
	public Producto eliminarProducto(@PathVariable(value = "codigo") Long codigo) {
		Producto p;
		try {
			Producto producto = servicioproducto.obtenerProducto(codigo);
			String nomAnterior=producto.getImagen();
			
			if(nomAnterior != null && nomAnterior.length() > 0) {
				Path rutaImgAnt = Paths.get("uploads").resolve(nomAnterior).toAbsolutePath();
				File archImgAnt = rutaImgAnt.toFile();
				if (archImgAnt.exists() && archImgAnt.canRead()) {
					archImgAnt.delete();
				}
			}
			
			p=servicioproducto.eliminarProducto(codigo);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no se puede borrar");
		}
		return p;
	}
	
	//MOSTRAR PRODUCTOS
	@GetMapping("/mostrarProductos")
	public List<Producto> mostrarProductos(){
		List<Producto> productos;
		try {
			productos=servicioproducto.mostrarProductos();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontraron Productos");
		}
		return productos;
	}
	
	//BUSCAR POR NOMBRE PRODUCTOS
	@GetMapping("/buscarNombreProducto/{nombre}")
	public List<Producto> buscarNombreProducto(@PathVariable(value = "nombre") String nombre){
		List<Producto> productos;
		try {
			productos=servicioproducto.buscarNombreProductos(nombre);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		return productos;
	}
	
	//BUSCAR POR NOMBRE PRODUCTOS
	@GetMapping("/buscarporPrecioProducto/{p1}/{p2}")
	public List<Producto> buscarporPrecioProducto(@PathVariable(value = "p1") double p1, @PathVariable(value="p2") double p2){
		List<Producto> productos;
		try {
			productos=servicioproducto.buscarporPrecioProductos(p1, p2);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		return productos;
	}
	
	@GetMapping("/buscarProducto/{codigo}")
	public Producto buscarProductoC(@PathVariable(value="codigo")Long codigo) {
		Producto p;
		
		try {
			p=servicioproducto.obtenerProducto(codigo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		
		return p;
	}
	
	
	//Subir Imagen para el create
		@PostMapping("/producto/uploadC")
		public ResponseEntity<?> uploadCreate(@RequestParam("archivo") MultipartFile archivo, @RequestParam("producto") Producto producto){
			Map<String, Object> response=new HashMap<>();
			
			if(!archivo.isEmpty()) {
				String nombreArch=UUID.randomUUID().toString()+"_"+ archivo.getOriginalFilename().replace(" ", "");
				Path ruta=Paths.get("uploads").resolve(nombreArch).toAbsolutePath();
				try {
					Files.copy(archivo.getInputStream(), ruta);
				} catch (IOException e) {
					response.put("mensaje", "Error al subir la imagen"+nombreArch);
					response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
				//Borrar imagen anterior si existe
				String nomAnterior=producto.getImagen();
				
				if(nomAnterior != null && nomAnterior.length() > 0) {
					Path rutaImgAnt = Paths.get("uploads").resolve(nomAnterior).toAbsolutePath();
					File archImgAnt = rutaImgAnt.toFile();
					if (archImgAnt.exists() && archImgAnt.canRead()) {
						archImgAnt.delete();
					}
				}
				
				producto.setImagen(nombreArch);
				
				
				response.put("producto", producto);
				response.put("mensaje", "Se subio correctamente el archivo");
			}
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}
	
	//Subir Imagen
	@PostMapping("/producto/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response=new HashMap<>();
		Producto producto;
		try {
			producto=servicioproducto.obtenerProducto(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontraron Productos");
		}
		if(!archivo.isEmpty()) {
			String nombreArch=UUID.randomUUID().toString()+"_"+ archivo.getOriginalFilename().replace(" ", "");
			Path ruta=Paths.get("uploads").resolve(nombreArch).toAbsolutePath();
			try {
				Files.copy(archivo.getInputStream(), ruta);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen"+nombreArch);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			//Borrar imagen anterior si existe
			String nomAnterior=producto.getImagen();
			
			if(nomAnterior != null && nomAnterior.length() > 0) {
				Path rutaImgAnt = Paths.get("uploads").resolve(nomAnterior).toAbsolutePath();
				File archImgAnt = rutaImgAnt.toFile();
				if (archImgAnt.exists() && archImgAnt.canRead()) {
					archImgAnt.delete();
				}
			}
			
			producto.setImagen(nombreArch);
			
			try {
				servicioproducto.actualizarProducto(id, producto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no se puede registrar producto");
				
			}
			
			response.put("producto", producto);
			response.put("mensaje", "Se subio correctamente el archivo");
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreimg:.+}")
	public ResponseEntity<Resource> verImagen(@PathVariable("nombreimg") String nombreImg){
		Path ruta = Paths.get("uploads").resolve(nombreImg).toAbsolutePath();
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(ruta.toUri());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error, no se pudo cargar la imagen:"+ nombreImg);
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+recurso.getFilename()+"\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
		
	}
	
}

