package pe.upc.bench.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.upc.bench.entidades.Review;

public interface RepositorioReview extends JpaRepository<Review, Long>{

}
