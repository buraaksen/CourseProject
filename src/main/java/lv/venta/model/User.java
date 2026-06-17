package lv.venta.model;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "UserTable")
public class User {
	@Column(name = "IDU")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	
	@Column(name = "Name")
	@NotNull(message = "Name can not be null")
	@NotEmpty(message = "Name must not be Empty")
	@Pattern(regexp = "[A-Z]{1}[a-z]{2,20}", message = "name must begin by capital letter")
	private String name;
	
	@Column(name = "Surname")
	@NotNull(message = "Surname can not be null")
	@NotEmpty(message = "Surname must not be Empty")
	@Pattern(regexp = "[A-Z]{1}[a-z]{2,20}",message = "Surname must begin by capital letter")
	private String surname;
	@Column(name = "Email")
	@Email
	@NotNull(message = "Email can not be null")
	@NotEmpty(message = "Email must not be Empty")
	//@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$",
		//	message ="Invalide email")
	private String email;
	
	@Column(name = "Password")
	@NotNull(message = "Password can not be null")
	@NotEmpty(message = "Password must not be Empty")
	@Pattern(regexp = "[0-9]{8}", message = "Type 8-20 numbers")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Status role;
	
	@OneToMany(mappedBy = "user")
	private Collection<Reservation> reservations;
//Getters and Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Status getRole() {
		return role;
	}

	public void setRole(Status role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}
//arg no arg constructors
	public User() {}
	public User(String name, String surname, String email,String password, Status role) {
		setName(name);
		setSurname(surname);
		setEmail(email);
		setPassword(password);
		setRole(role);
	}
	public String toString() {
		return "[" + id +"]" + "Role( "+role+" )" + name +" "+ surname + " Email: " 
					+ email + " Code: " + password ;  
	}
}
