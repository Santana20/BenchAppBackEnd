package pe.upc.bench.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.bench.entidades.Pizzeria;
import pe.upc.bench.repositorios.RepositorioPizzeria;

@Service
public class ServicioPizzeria {
	
	@Autowired
	private RepositorioPizzeria repositorioPizzeria;
	
	//REGISTRAR PIZZERIA
	@Transactional(rollbackFor = Exception.class)
	public Pizzeria registrarPizzeria(Pizzeria pizzeria) throws Exception {
		if(pizzeria.getCodigo()==null) {
			return repositorioPizzeria.save(pizzeria);
		}else {
			throw new Exception();
		}
	}
	
	//ACTUALIZAR DATOS DE LA PIZZERIA 
	public Pizzeria actualizarPizzeria(Pizzeria pizzeria,Long codigo) throws Exception {
		Pizzeria pizz=repositorioPizzeria.obtenerPizzeria(codigo);
		if(pizzeria.getDescripcion()!=null) {
			pizz.setDescripcion(pizzeria.getDescripcion());
		}
		if(pizzeria.getDireccion()!=null) {
			pizz.setDireccion(pizzeria.getDireccion());
		}
		if(pizzeria.getTelefono()!=null) {
			pizz.setTelefono(pizzeria.getTelefono());
		}
		return repositorioPizzeria.save(pizz);
	}
	
	
	
	//OBTENER DATOS DE LA PIZZERIA
	public List<Pizzeria> obtenerDatosPizzeria()  {
		List<Pizzeria> pizzeria;
		pizzeria=repositorioPizzeria.findAll();
		return pizzeria;
	}
}
