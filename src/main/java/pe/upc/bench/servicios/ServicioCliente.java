package pe.upc.bench.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.bench.entidades.Cliente;
import pe.upc.bench.repositorios.RepositorioCliente;

@Service
public class ServicioCliente {
	@Autowired
	private RepositorioCliente repositorioCliente;
	
	//OBTENER UN SOLO CLIENTE
		public Cliente obtenerCliente(String dni ) throws Exception {
			Cliente c;
			c=repositorioCliente.buscarCliente(dni);
			if(c==null) throw new Exception("entidad no encontrada");
			return c;
			
		}
		
		//REGISTRAR UN SOLO CLIENTE
		@Transactional(rollbackFor = Exception.class)
		public Cliente registrarCliente(Cliente cliente) throws Exception {
			if(cliente.getCodigo()==null) {
				return repositorioCliente.save(cliente);
			}else {
				throw new Exception();
			}
		}
		
		
		
		
		//OBTENER LISTA DE CLIENTES
		public List<Cliente> obtenerClientes(){
			return repositorioCliente.findAll();
		}
		
		
		//ELIMINAR CLIENTE
		public Cliente borrarCliente(String dni) throws Exception {
			Cliente c;
			c=obtenerCliente(dni);
			if(c.getCodigo()!=null) {
				repositorioCliente.delete(c);
			}else {
				throw new Exception();
			}
			return c;
		}
		
		
		//BUSCAR POR NOMBRE
		public List<Cliente> buscarNombre(String nombre) throws Exception{
			List<Cliente> clientes;
			clientes= repositorioCliente.buscarNombre(nombre);
			if(clientes==null) throw new Exception("entidades no encontradas");
			return clientes;
			
		}
		
		//ACTUALIZAR CLIENTE
		public Cliente actualizarCliente(Cliente cliente,String dni) {
			Cliente client;
			client=repositorioCliente.buscarCliente(dni);
			if(cliente.getNombre()!=null) {
				client.setNombre(cliente.getNombre());
			}
			if(cliente.getEmail()!=null) {
				client.setEmail(cliente.getEmail());
			}
			if(cliente.getPassword()!=null) {
				client.setPassword(cliente.getPassword());
			}
			if(cliente.getTelefono()!=null) {
				client.setTelefono(cliente.getTelefono());
			}
			return repositorioCliente.save(client);
			
		}
		
		
		
}
