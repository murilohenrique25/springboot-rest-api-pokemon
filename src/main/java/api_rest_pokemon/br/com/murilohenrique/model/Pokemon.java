package api_rest_pokemon.br.com.murilohenrique.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name="pokemon")
public class Pokemon implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	
	private Long id;
	
	private Long num;
	
	private String name;
	
	private String type;
	
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},mappedBy = "pokemon")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<PreEvolution> pre_evolution = new ArrayList<>();

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},mappedBy = "pokemon")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<NextEvolution> next_evolution = new ArrayList<>();

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
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
