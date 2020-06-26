package pe.upc.bench.servicios;

import pe.upc.bench.entidades.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);

}
