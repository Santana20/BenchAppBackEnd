package pe.upc.bench.entidades;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name ="PEDIDO")

public class Pedido  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_PEDIDO")
	private Long codigo;
	private String direccion;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private double costo_total;
	
	
	@ManyToOne
	@JoinColumn(name ="ID_CLIENTE")
	
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Pedido_Producto> productos;
	
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public double getCosto_total() {
		return costo_total;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	public void setCosto_total(double costo_total) {
		this.costo_total = costo_total;
	}
	public List<Pedido_Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Pedido_Producto> productos) {
		this.productos = productos;
	}
	
}
