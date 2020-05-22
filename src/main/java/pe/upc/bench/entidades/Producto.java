package pe.upc.bench.entidades;

import java.io.Serializable;
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
@Table(name ="PRODUCTO")
public class Producto implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_PRODUCTO")
	private Long codigo;
	private String nombre;
	private String descripcion;
	private double precio;
	
	@ManyToOne
	@JoinColumn(name="ID_PIZZERIA")
	@JsonIgnore
	private Pizzeria pizzeria;
	
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Pedido_Producto> pedido_producto;
	
	
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Producto_Oferta> producto_oferta;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public List<Pedido_Producto> getPedido_producto() {
		return pedido_producto;
	}
	public void setPedido_producto(List<Pedido_Producto> pedido_producto) {
		this.pedido_producto = pedido_producto;
	}
	public Pizzeria getPizzeria() {
		return pizzeria;
	}
	public void setPizzeria(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}
	public List<Producto_Oferta> getProducto_oferta() {
		return producto_oferta;
	}
	public void setProducto_oferta(List<Producto_Oferta> producto_oferta) {
		this.producto_oferta = producto_oferta;
	}
	
	
	

}
