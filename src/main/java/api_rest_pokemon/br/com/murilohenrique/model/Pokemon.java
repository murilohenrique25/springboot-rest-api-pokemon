package api_rest_pokemon.br.com.murilohenrique.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="pokemon")
public class Pokemon implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long num;
	
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private List<Type> type;
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private List<PreEvolution> pre_evolution;

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private List<NextEvolution> next_evolution;


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


	public List<Type> getType() {
		return type;
	}

	public void setType(List<Type> type) {
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
