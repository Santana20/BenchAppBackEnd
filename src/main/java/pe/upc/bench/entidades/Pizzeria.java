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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="PIZZERIA")
public class Pizzeria implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_PIZZERIA")
	private Long codigo;
	private String descripcion;
	private String direccion;
	private String telefono;
	@JsonIgnore
	@OneToMany(mappedBy = "pizzeria",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Producto> productos;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pizzeria",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Oferta> ofertas;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	public List<Oferta> getOfertas() {
		return ofertas;
	}
	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	
	
	
	
	

}
