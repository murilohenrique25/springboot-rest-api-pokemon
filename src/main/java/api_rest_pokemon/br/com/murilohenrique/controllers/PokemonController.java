package api_rest_pokemon.br.com.murilohenrique.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import api_rest_pokemon.br.com.murilohenrique.model.NextEvolution;
import api_rest_pokemon.br.com.murilohenrique.model.Pokemon;
import api_rest_pokemon.br.com.murilohenrique.model.PreEvolution;
import api_rest_pokemon.br.com.murilohenrique.repository.NextEvolutionRepository;
import api_rest_pokemon.br.com.murilohenrique.repository.PokemonRepository;
import api_rest_pokemon.br.com.murilohenrique.repository.PreEvolutionRepositoty;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class PokemonController {
	
	@Autowired
	PokemonRepository repo;
	
	@Autowired
	PreEvolutionRepositoty preRepo;
	
	@Autowired
	NextEvolutionRepository nexRepo;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    //@RequestMapping(value = "/{name}", method = RequestMethod.GET)
    //@ResponseStatus(HttpStatus.OK)
    //public String greetingText(@PathVariable String name) {
    //    return "Hello " + name + "!";
    //}
    
    @GetMapping(value = "listartodos")
    @ResponseBody
    public ResponseEntity<List<Pokemon>> listartodos(){
    	List<Pokemon> pokemons = repo.findAll();
    	return new ResponseEntity<List<Pokemon>>(pokemons, HttpStatus.OK);
    }
    
    @PostMapping(value = "salvar")
    @ResponseBody
    public ResponseEntity<Pokemon> salvar(@RequestBody Pokemon poke){
    	Pokemon pokemon = repo.save(poke);
    	return new ResponseEntity<Pokemon>(pokemon, HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "deletar")
    @ResponseBody
    public ResponseEntity<String> deletar(@RequestParam Long id){    	
    	repo.deleteById(id);
    	return new ResponseEntity<String>("Pokemon excluido com sucesso.", HttpStatus.OK);
    }
    
    @GetMapping(value = "buscaid")//mapeia a url
    @ResponseBody //descricao da resposta
    public ResponseEntity<Pokemon> buscaruserid(@RequestParam(name = "id") Long id){ //recebe os dados para listar
    	Pokemon poke = repo.findById(id).get(); //retornando usuario pelo id
    	
    	return new ResponseEntity<Pokemon>(poke, HttpStatus.OK);
    }
    
    @PostMapping(value = "atualizapreEvolution")//mapeia a url
    @ResponseBody //descricao da resposta
    public ResponseEntity<String> atualizapreEvolution(@RequestParam(name = "id") Long id , @RequestBody PreEvolution preEvolution){ //recebe os dados para listar
    	Pokemon user = repo.findById(id).get();
    	preEvolution.setPokemon(user);
    	
    	preRepo.save(preEvolution);
    	
    	return new ResponseEntity<String>("Inserido com sucesso", HttpStatus.OK);
    }
    
    @PostMapping(value = "atualizanextEvolution")//mapeia a url
    @ResponseBody //descricao da resposta
    public ResponseEntity<String> atualizanexEvolution(@RequestParam(name = "id") Long id , @RequestBody NextEvolution nexEvolution){ //recebe os dados para listar
    	Pokemon user = repo.findById(id).get();
    	nexEvolution.setPokemon(user);
    	
    	nexRepo.save(nexEvolution);
    	
    	return new ResponseEntity<String>("Inserido com sucesso", HttpStatus.OK);
    }
    
    @GetMapping(value = "pokemon")
    public ResponseEntity<Page<Pokemon>> findAll(@Valid @RequestParam("pages") @NotBlank(message = "Informar Página") int page, @RequestParam("items") @NotBlank(message = "Informar quantidade por Página") int items) {
        PageRequest pageable = PageRequest.of(page, items, Sort.by(Sort.Direction.ASC, "id"));
        Page<Pokemon> list = repo.findAll(pageable);
        return new ResponseEntity<Page<Pokemon>> (list, HttpStatus.OK);
    }
    
    @GetMapping("/list/type")
    public List<Pokemon> getByType(@RequestParam String name){
        return repo.buscarPorType(name);
    }
}
