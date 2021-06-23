package api_rest_pokemon.br.com.murilohenrique.repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import api_rest_pokemon.br.com.murilohenrique.model.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long >{
	
	Page<Pokemon> findAll(Pageable page);
	
	@Query(value = "select u from Pokemon u where trim(u.type) like %?1%")
	List<Pokemon> buscarPorType(String type);

}
