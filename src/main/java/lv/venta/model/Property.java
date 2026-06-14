package lv.venta.model;


import java.util.Collection;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "Property")
public class Property {
	
	
	//variable

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PrId")
	private int PrId;
	
	@NotNull
	@NotEmpty
	@Pattern (regexp =  "^[A-Z][a-zA-Z0-9 ,.\\-]*$",
		    message = "Must start with a capital letter")
	@Column(name = "Name")
	private String name;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp =  "^[A-Z][a-zA-Z0-9 ,.\\-]*$",
			message = "Location should be with tha first capital letter and after that small letters or spaces")
	@Column(name = "Location")
	private String location;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp =  "^[A-Z][a-zA-Z0-9 ,.\\-]*$",
			message = "Description should be with tha first capital letter and after that small letters or spaces")
	@Column(name = "Description")
	private String description;
	
	@Column(name = "IsAvailable")
	private boolean isAvailable;
	
	@Enumerated
	@Column(name = "Type")
	private Type type;
	

	@OneToMany
	@JoinColumn(name = "idRoom")
	private Collection<Room> rooms;
	
	
	//setter and getter
	public int getPrId() {
		return PrId;
	}

	public void setPrId(int prId) {
		PrId = prId;
	}
	public String getName() {
		return name;	
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	//constructor
	
	public Property(String name, String location, String description, boolean isAvailable, Type type) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.isAvailable = isAvailable;
        this.type = type;
	}
	
	
	//not arg-constructor
	public Property() {

	}
	
	//toString
	@Override
	public String toString() {
		return "Property [name=" + name + ", location=" + location + ", description=" + description + ", isAvailable="
				+ isAvailable + ", type=" + type + "]";
	}

}
