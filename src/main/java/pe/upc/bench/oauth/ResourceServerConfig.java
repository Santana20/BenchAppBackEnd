package pe.upc.bench.oauth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer 
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Override ///Reglas de seguridad de nuestros EndPoints
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/mostrarProductos","/api/RegistrarUsuario","/api/buscarporPrecioProducto/**","/api/buscarNombreProducto/**"
				, "/api/clientes", "/api/clientes/page/**", "/api/uploads/img/**", "/images/**",
				"/api/buscarofertaTitulo/**","/api/ofertas",
				"/api/pizzeriamostrar","/api/mostrarProductoOferta","/api/reviews","/api/uploads/img/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/RegistrarUsuario","/api/registrarReview").permitAll()
		/*.antMatchers(HttpMethod.GET, "/api/clientes/{id}").hasAnyRole("USER", "ADMIN")//no es necesario el pr
		.antMatchers(HttpMethod.POST, "/api/clientes/upload").hasAnyRole("USER", "ADMIN") // de lo especifico a los genericos
		.antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
		.antMatchers("/api/clientes/**").hasRole("ADMIN")*/
		
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
	}
	
	@Bean//CORS  Profe buenas, por donde nos comunicamos?. Para indicarle las dudas
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	@Bean//registra el Corse de arriba
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}


}
