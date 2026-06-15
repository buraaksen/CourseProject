package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "FacilityTable")
public class Facility {
	
	//variable

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FaId")
	private int FaId;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp =  "^[A-Z][a-zA-Z0-9 ,.\\-]*$", 
	message = "Name should be with tha first capital letter and after that small letters or spaces")
	@Column(name = "Name")
	private String name; 
	
	@NotNull
	@NotEmpty
	@Pattern(regexp =  "^[A-Z][a-zA-Z0-9 ,.\\-]*$", 
	message = "Description should be with tha first capital letter and after that small letters or spaces")
	@Column(name = "Description")
	private String description;
	
	//setter and getter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	//constructor
	public Facility(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	//not arg-constructor
	public Facility() {

	}
	
	//toString method
	
	@Override
	public String toString() {
		return "Facility [name=" + name + ", description=" + description + "]";
	}


}
