package pe.upc.bench.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.upc.bench.entidades.Cliente2;
import pe.upc.bench.entidades.Region;



public interface IClienteService {
public List<Cliente2> findAll();
	
	public Page<Cliente2> findAll(Pageable pageable);
	
	public Cliente2 findById(Long id);
	
	public Cliente2 save(Cliente2 cliente2);
	
	public void delete(Long id);
	
	public List<Region> findAllRegiones();

}
