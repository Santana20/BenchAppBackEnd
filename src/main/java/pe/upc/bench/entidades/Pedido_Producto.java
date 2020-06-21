package pe.upc.bench.entidades;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity
@Table(name ="PEDIDO_PRODUCTO")
public class Pedido_Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_PEDIDO_PRODUCTO")
	private Long codigo;
	private double precio;
	private long cantidad_pedida;
	
	
	@ManyToOne
	@JoinColumn(name="ID_PRODUCTO")
	
	private Producto producto;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ID_PEDIDO")
	
	private Pedido pedido;

	

	public Long getCodigo() {
		return codigo;
	}


	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public long getCantidad_pedida() {
		return cantidad_pedida;
	}


	public void setCantidad_pedida(long cantidad_pedida) {
		this.cantidad_pedida = cantidad_pedida;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
	public Long getCodigoProducto() {
		return producto.getCodigo();
		
	}
	
	
	
}
