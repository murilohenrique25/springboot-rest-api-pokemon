package api_rest_pokemon.br.com.murilohenrique.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api_rest_pokemon.br.com.murilohenrique.model.NextEvolution;

@Repository
public interface NextEvolutionRepository extends JpaRepository<NextEvolution, Long> {

}
