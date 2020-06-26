package pe.upc.bench.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import pe.upc.bench.entidades.Cliente2;
import pe.upc.bench.entidades.Region;
import pe.upc.bench.repositorios.RepositorioClienteDao;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private RepositorioClienteDao clienteDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente2> findAll() {
		return (List<Cliente2>) clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente2> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Cliente2 findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente2 save(Cliente2 cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegiones() {
		return clienteDao.findAllRegiones();
	}

}
