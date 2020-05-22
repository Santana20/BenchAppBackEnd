package pe.upc.bench.entidades;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="PEDIDO")
public class Pedido implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_PEDIDO")
	private Long codigo;
	private String direccion;
	private Date fecha;
	private double costo_total;
	
	
	@ManyToOne
	@JoinColumn(name ="ID_CLIENTE")
	@JsonIgnore
	private Cliente cliente;
	
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Pedido_Producto> pedido_producto;
	
	
	
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
	
	public List<Pedido_Producto> getPedido_producto() {
		return pedido_producto;
	}
	public void setPedido_producto(List<Pedido_Producto> pedido_producto) {
		this.pedido_producto = pedido_producto;
	}
	public void setCosto_total(double costo_total) {
		this.costo_total = costo_total;
	}
	
	
	
	
	
	
}
