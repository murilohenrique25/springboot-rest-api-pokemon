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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import api_rest_pokemon.br.com.murilohenrique.model.Pokemon;
import api_rest_pokemon.br.com.murilohenrique.repository.PokemonRepository;


/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
@RequestMapping("/pokemon")
public class PokemonController {
	
	@Autowired
	PokemonRepository repo;
	

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
    
    @GetMapping(value = "pokemons")
    @ResponseBody
    public ResponseEntity<List<Pokemon>> listartodos(){
    	List<Pokemon> pokemons = repo.findAll();
    	return new ResponseEntity<List<Pokemon>>(pokemons, HttpStatus.OK);
    }
    
    @GetMapping(value = "pokemons/{type}")
    @ResponseBody
    public ResponseEntity<?> listartodosPorType(@PathVariable String type){
    	
    		List<Pokemon> pokemons = repo.findAllType(type);
    		if(pokemons.size() > 0)
        	return new ResponseEntity<List<Pokemon>>(pokemons, HttpStatus.OK);
    		else	
    		return new ResponseEntity<String>("Nenhum pokemon encontrado com o tipo: " + type, HttpStatus.NOT_FOUND);
        	
        	
    }
    
    @PostMapping
    @ResponseBody
    public ResponseEntity<Pokemon> salvar(@RequestBody Pokemon poke){
    	Pokemon pokemon = repo.save(poke);
    	return new ResponseEntity<Pokemon>(pokemon, HttpStatus.CREATED);
    }
    
    @PutMapping("/{num}")
    public ResponseEntity<?> atualizar(@PathVariable Long num, @RequestBody Pokemon pokemon){
    	try {
    		Pokemon pok = repo.findById(num).get();
        	pok = repo.save(pokemon);
        	return new ResponseEntity<Pokemon>(pok, HttpStatus.CREATED);
    	}
    	catch (Exception e) {
    		return new ResponseEntity<String>(e.getMessage(), HttpStatus.CREATED);
		}
    }
    
    @DeleteMapping("/{num}")
    @ResponseBody
    public ResponseEntity<String> deletar(@PathVariable Long num){
    	try {
    		
    	repo.deleteById(num);
    	return new ResponseEntity<String>("Pokemon excluido com sucesso.", HttpStatus.OK);
    	}catch (Exception e) {
    		return new ResponseEntity<String>("\nPokemon não encontrado com o num " + num, HttpStatus.NOT_FOUND);
		}
    }
    
    @GetMapping("/{num}")//mapeia a url
    @ResponseBody //descricao da resposta
    public ResponseEntity<?> buscaruserid(@PathVariable Long num) { //recebe os dados para listar
    	try {
    		Pokemon poke = repo.findById(num).get();
    		return new ResponseEntity<Pokemon>(poke, HttpStatus.OK);
    	}catch (Exception e) {
    		return new ResponseEntity<String>( "\nPokemon não encontrado com o num " + num, HttpStatus.NOT_FOUND);
		}
    	
    }
       
    
    @GetMapping(value = "pokemon")
    public ResponseEntity<Page<Pokemon>> findAll(@Valid @RequestParam("pages") @NotBlank(message = "Informar Página") int page, @RequestParam("items") @NotBlank(message = "Informar quantidade por Página") int items) {
        PageRequest pageable = PageRequest.of(page, items, Sort.by(Sort.Direction.ASC, "num"));
        Page<Pokemon> list = repo.findAll(pageable);
        return new ResponseEntity<Page<Pokemon>> (list, HttpStatus.OK);
    }
    
}
