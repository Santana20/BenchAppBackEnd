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

import pe.upc.bench.entidades.Review;
import pe.upc.bench.servicios.ServicioReview;

@RestController
@RequestMapping("/api")
public class RestReview {
	@Autowired
	private ServicioReview servicioreview;
	
	//REGISTRAR REVIEWwww
	@PostMapping("/registrarReview")
	public Review registrarReview(@RequestBody Review review) {
		Review r;
		try {
			r=servicioreview.registrarReview(review);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		return r;
	}
	
	//ACTUALIZAR REVIEW 
	@PutMapping("/actualizarReview/{codigo}")
	public Review actualizarReview(@RequestBody Review review,@PathVariable(value = "codigo") Long codigo) {
		Review r;
		try {
			r=servicioreview.actualizarReview(review,codigo);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		return r;
	}
	
	//ELIMINAR REVIEW
	@DeleteMapping("/borrarReview/{codigo}")
	public Review eliminarReview(@PathVariable(value = "codigo")Long codigo) {
		Review r = null;
		try {
			r=servicioreview.eliminarReview(codigo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		return r;
	}
	
	//MOSTRAR TODOS LOS REVIEW
	@GetMapping("/reviews")
	public List<Review> obtenerReviews(){
		return servicioreview.obtenerReview();
	}
}
