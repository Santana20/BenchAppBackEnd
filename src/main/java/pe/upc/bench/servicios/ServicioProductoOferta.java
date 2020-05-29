package pe.upc.bench.servicios;

import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.bench.entidades.Oferta;
import pe.upc.bench.entidades.Producto;
import pe.upc.bench.entidades.Producto_Oferta;
import pe.upc.bench.repositorios.RepositorioProductoOferta;

@Service
public class ServicioProductoOferta {
	@Autowired
	private RepositorioProductoOferta repositorioProductoOferta;
	
	@Autowired
	private ServicioProducto servicioproducto;
	
	@Autowired
	private ServicioOferta serviciooferta;
	
	//REGISTRAR PRODUCTO OFERTA
	@Transactional(rollbackFor = Exception.class)
	public Producto_Oferta registrarProductoOferta(Long codigo, Long codigo2, Producto_Oferta producto_Oferta) throws Exception {
		Producto producto=servicioproducto.obtenerProducto(codigo);
		producto_Oferta.setProducto(producto);
		Oferta oferta=serviciooferta.obtenerOferta(codigo2);
		producto_Oferta.setOferta(oferta);
		return repositorioProductoOferta.save(producto_Oferta);
	}
	
	//MOSTRAR LISTA PRODUCTO_OFERTA
	public List<Producto_Oferta> mostrarLista(){
		return repositorioProductoOferta.findAll();
	}
	
	
	
	
	//BUSCAR RANGO FECHAS
	public List<Producto_Oferta> buscarRangoFecha(String fecha,String fechafin) throws Exception{
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		Date fechaini=formatter.parse(fecha);
		Date fecha2=formatter.parse(fechafin);
		List<Producto_Oferta> po=null;
		po=repositorioProductoOferta.BuscarRangoFecha(fechaini, fecha2);
		if(po==null)throw new Exception("Listado no encontrado");
		return po;
	}
	
	
	//ELIMINAR PRODUCTO OFERTA
	public Producto_Oferta eliminar(Long codigo) throws Exception {
		Producto_Oferta po;
		po=repositorioProductoOferta.buscarProductoOferta(codigo);
		if(po.getCodigo()!=null) {
			repositorioProductoOferta.delete(po);
		}else {
			throw new Exception();
		}
		return po;
	}
}
