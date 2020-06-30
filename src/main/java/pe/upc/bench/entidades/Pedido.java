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
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_pedido;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_recepcion;
	private boolean status;
	private double costo_total;
	
	
	
	@OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Pedido_Producto> productos;
	
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	
	private Usuario usuario;
	
	
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
	
	
	public double getCosto_total() {
		return costo_total;
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
	public Date getFecha_pedido() {
		return fecha_pedido;
	}
	public void setFecha_pedido(Date fecha_pedido) {
		this.fecha_pedido = fecha_pedido;
	}
	public Date getFecha_recepcion() {
		return fecha_recepcion;
	}
	public void setFecha_recepcion(Date fecha_recepcion) {
		this.fecha_recepcion = fecha_recepcion;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
