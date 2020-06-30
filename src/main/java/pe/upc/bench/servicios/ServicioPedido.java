package pe.upc.bench.servicios;

import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.upc.bench.entidades.Pedido;
import pe.upc.bench.entidades.Pedido_Producto;
import pe.upc.bench.entidades.Usuario;

import pe.upc.bench.repositorios.RepositorioPedido;
import pe.upc.bench.repositorios.RepositorioUsuarioDao;

@Service
public class ServicioPedido {
	
	
	@Autowired
	private RepositorioUsuarioDao usuarioDao;
	
	@Autowired
	private RepositorioPedido repositorioPedido;
	
	//REALIZAR UN PEDIDO
	
	@Transactional(noRollbackFor =  Exception.class)
	public Pedido realizarPedido(String username,Pedido pedido) throws Exception {
		Usuario c = null;
		c=usuarioDao.retornaUsuarioUserName(username);
		if(c==null) throw new Exception("entidad no encontrada");
		for(Pedido_Producto aux : pedido.getProductos())
		{
			aux.setPedido(pedido);
		}
		pedido.setUsuario(c);
		return repositorioPedido.save(pedido);
	}
	
	//ACTUALIZAR FECHARECEPCION DEL PEDIDO
	public Pedido actualizarFechaRecepcion(Long codigo, Date fechaRecepcion) throws Exception
	{
		Pedido pedido = repositorioPedido.buscarPedido(codigo);
		
		if ( pedido != null )
		{
			pedido.setFecha_recepcion(fechaRecepcion);
			pedido.setStatus(true);
			repositorioPedido.save(pedido);
		}
		return pedido;
	}
	
	//LISTAR PEDIDOS ACTIVOS POR CLIENTE
	public List<Pedido> listarPedidosActivosdeCliente(String username) throws Exception
	{
		return (List<Pedido>) repositorioPedido.listarPedidosActivosdeCliente(username);
	}
	
	//LISTAR PEDIDOS ANTIGUOS POR CLIENTE
	public List<Pedido> listarPedidosPasadosdeCliente(String username) throws Exception
	{
		return (List<Pedido>) repositorioPedido.listarPedidosPasadosdeCliente(username);
	}
	
	
	//LISTAR TODOS LOS PEDIDOS ACTIVOS
	public List<Pedido> listarPedidosActivos() throws Exception
	{
		return (List<Pedido>) repositorioPedido.listarPedidosActivos();
	}
	
	//LISTAR TODOS LOS PEDIDOS ACTIVOS
	public List<Pedido> listarPedidosPasados() throws Exception
	{
		return (List<Pedido>) repositorioPedido.listarPedidosPasados();
	}
	
	
	
	//OBTENER PEDIDOS
	public List<Pedido> obtenerPedidos(){
		return (List<Pedido>) repositorioPedido.findAll();
	}
	
	//OBTENER PEDIDO 
	public Pedido obtenerPedido(Long codigo) throws Exception {
		Pedido p;
		p=repositorioPedido.buscarPedido(codigo);
		if (p==null)throw new Exception("pedido no encontrado");
		return p;
	}
	
	//BUSCAR RANGO PRECIOS
	public List<Pedido> buscarRangoPrecios(double ini,double fin) throws Exception{
		List<Pedido> pedidos;
		pedidos=repositorioPedido.buscarRangocCosto(ini, fin);
		if(pedidos==null) throw new Exception("pedidos no encontrados");
		return pedidos;
	}
	
}
