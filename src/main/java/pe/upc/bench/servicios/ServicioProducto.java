package pe.upc.bench.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.bench.entidades.Pizzeria;
import pe.upc.bench.entidades.Producto;
import pe.upc.bench.repositorios.RepositorioPizzeria;
import pe.upc.bench.repositorios.RepositorioProducto;

@Service
public class ServicioProducto {
	@Autowired
	private RepositorioProducto repositorioProducto;
	
	@Autowired
	private ServicioPizzeria serviciopizzeria;
	@Autowired
	private RepositorioPizzeria repositorioPizzeria;
	
	//REGISTRAR PRODUCTO
	@Transactional(rollbackFor = Exception.class)
	public Producto registrarProducto(Long codigo,Producto producto) throws Exception {
		Pizzeria pizzeria=repositorioPizzeria.obtenerPizzeria(codigo);
		producto.setPizzeria(pizzeria);
		
		
		
		return repositorioProducto.save(producto);
	}
	
	//OBTENER PRODUCTO
	public Producto obtenerProducto(Long codigo) throws Exception {
		Producto p;
		p=repositorioProducto.buscarProducto(codigo);
		if(p==null)throw new Exception("pedido no encontrado");
		return p;
	}
	
	//ACTUALIZAR PRODUCTO
	public Producto actualizarProducto(Long codigo,Producto producto) throws Exception {
		Producto p=obtenerProducto(codigo);
		if(producto.getNombre()!=null) {
			p.setNombre(producto.getNombre());
		}
		if(producto.getDescripcion()!=null) {
			p.setDescripcion(producto.getDescripcion());  
		}
		if(producto.getPrecio()!=0) {
			p.setPrecio(producto.getPrecio());
		}
		if(producto.getImagen()!=null) {
			p.setImagen(producto.getImagen());
		}
		if(producto.getPizzeria()!=null) {
			p.setPizzeria(producto.getPizzeria());
		}
		
		return repositorioProducto.save(p);
	}
	
	//ELIMINAR UN PRODUCTO
	public Producto eliminarProducto(Long codigo) throws Exception {
		Producto p;
		p=repositorioProducto.buscarProducto(codigo);
		if(p.getCodigo()!=null) {
			repositorioProducto.delete(p);
		}else {
			throw new Exception();
		}
		return p;
	}
	
	//MOSTRAR LISTA PRODUCTOS
	public List<Producto> mostrarProductos(){
		return repositorioProducto.findAll();
	}
	
	//BUSCAR POR NOMBRE EN PRODUCTOS
	public List<Producto> buscarNombreProductos(String nombre) throws Exception{
		List<Producto> productos;
		productos=repositorioProducto.buscarNombre(nombre);
		if(productos==null)throw new Exception("pedido no encontrado");
		return productos;
	}
	
	//BUSCAR POR PRECIO EN PRODUCTOS
	public List<Producto> buscarporPrecioProductos(double p1, double p2) throws Exception{
		List<Producto> productos;
		productos=repositorioProducto.buscarporPrecio(p1, p2);
		if(productos==null)throw new Exception("pedido no encontrado");
		return productos;
	}
}
