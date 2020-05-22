package pe.upc.bench.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.bench.entidades.Oferta;
import pe.upc.bench.entidades.Pizzeria;
import pe.upc.bench.repositorios.RepositorioOferta;
import pe.upc.bench.repositorios.RepositorioPizzeria;

@Service
public class ServicioOferta {
	@Autowired
	private RepositorioOferta repositorioOferta;
	
	@Autowired
	private ServicioPizzeria serviciopizzeria;
	
	@Autowired
	private RepositorioPizzeria repositorioPizzeria;
	
	//REGISTRAR UNA OFERTA 
	@Transactional(rollbackFor = Exception.class)
	public Oferta registrarOferta(Long codigo,Oferta oferta) throws Exception {
		Pizzeria pizzeria=repositorioPizzeria.obtenerPizzeria(codigo);
		oferta.setPizzeria(pizzeria);
		return repositorioOferta.save(oferta);
	}
	
	
	//ACTUALIZAR UNA OFERTA
	public Oferta actualizarOferta(Oferta oferta,Long codigo) throws Exception {
         Oferta ofer=repositorioOferta.buscarOferta(codigo);
		 if(oferta.getTitulo()!=null) {
			 ofer.setTitulo(oferta.getTitulo());
		 }
		 if(oferta.getDescripcion()!=null) {
			 ofer.setDescripcion(oferta.getDescripcion());
		 }
		 return repositorioOferta.save(ofer);
		
	}
	
	//OBTENER OFERTA
	public Oferta obtenerOferta(Long codigo) throws Exception {
		Oferta o;
		o=repositorioOferta.buscarOferta(codigo);
		if(o==null) throw new Exception("entidad no encontrada");
		return o;
	}
	
	
	
	//ELIMINAR UNA OFERTA
	public Oferta eliminarOferta(Long  codigo) throws Exception {
		Oferta o;
		o=obtenerOferta(codigo);
		if(o.getCodigo()!=null) {
			repositorioOferta.delete(o);
		}else {
			throw new Exception();
		}
		return o;
	}
	
	//BUSCAR OFERTA POR TITULO
	public List<Oferta> buscarOfertaTitulo(String titulo) throws Exception{
		List<Oferta> oferta;
		oferta=repositorioOferta.buscarOferta(titulo);
		if(oferta==null) throw new Exception("entidad no encontrada");
		return oferta;
	}
	
	//MOSTRAR LISTA
	public List<Oferta> mostrarLista(){
		return repositorioOferta.findAll();
		
	}
	
}
