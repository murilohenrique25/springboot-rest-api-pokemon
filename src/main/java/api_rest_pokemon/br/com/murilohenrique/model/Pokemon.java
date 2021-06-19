package api_rest_pokemon.br.com.murilohenrique.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name= "seq_pokemon", sequenceName ="seq_pokemon", allocationSize = 1, initialValue = 1)
public class Pokemon implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	private Long id;
	
	private Long num;
	
	private String name;
	
	private List<String> type;
	
	private List<PreEvolution> pre_evolution;
	
	private List<NextEvolution> next_evolution;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public List<PreEvolution> getPre_evolution() {
		return pre_evolution;
	}

	public void setPre_evolution(List<PreEvolution> pre_evolution) {
		this.pre_evolution = pre_evolution;
	}

	public List<NextEvolution> getNext_evolution() {
		return next_evolution;
	}

	public void setNext_evolution(List<NextEvolution> next_evolution) {
		this.next_evolution = next_evolution;
	}
	
	
}
