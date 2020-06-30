package pe.upc.bench.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import pe.upc.bench.entidades.Review;

import pe.upc.bench.repositorios.RepositorioReview;
import pe.upc.bench.repositorios.RepositorioUsuarioDao;

@Service
public class ServicioReview {
	@Autowired
	private RepositorioReview repositorioreview;
	
	
	
	@Autowired
	private RepositorioUsuarioDao usuarioDao;
	
	//REGISTRAR REVIEW
	public Review registrarReview(Review review) {

		return repositorioreview.save(review);
	}
	
	//ACTUALIZAR REVIEW 
	public Review actualizarReview(Review review,Long codigo) throws Exception{
		
		
		Review r = repositorioreview.findById(codigo).orElseThrow(()->new Exception("No se puede actualizar"));
		r.setDescripcion(review.getDescripcion());
		r.setNombre(review.getNombre());
		return repositorioreview.save(r);
	}
	
	//ELIMINAR REVIEW
	public Review eliminarReview(Long codigo) throws Exception {
		Review r;
		
		
		r = repositorioreview.findById(codigo).orElseThrow(()->new Exception("No existe la review"));
		repositorioreview.delete(r);
		return r;
	}
	
	//OBTENER TODOS LOS REVIEW
	public List<Review> obtenerReview(){
		return repositorioreview.findAll();
	}
}
