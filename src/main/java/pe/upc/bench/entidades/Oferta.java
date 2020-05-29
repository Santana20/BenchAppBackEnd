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
@Table(name ="OFERTA")
public class Oferta implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_OFERTA")
	private Long codigo;
	private String titulo;
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name ="ID_PIZZERIA")
	
	private Pizzeria pizzeria;
	
	@JsonIgnore
	@OneToMany(mappedBy = "oferta",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Producto_Oferta> productoofertas;

	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Pizzeria getPizzeria() {
		return pizzeria;
	}

	public void setPizzeria(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
	}

	public List<Producto_Oferta> getProductoofertas() {
		return productoofertas;
	}

	public void setProductoofertas(List<Producto_Oferta> productoofertas) {
		this.productoofertas = productoofertas;
	}
	
	

}
