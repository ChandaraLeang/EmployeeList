package edu.mum.cs545.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Employee {
    private long id;
    private String firstname;
	private String lastname;
    private String email;
    private double salary;
	private Set<Position> positions = new HashSet<Position>(0);

    public Employee() {
    	
    }
    
	public Employee(String firstname, String lastname, String email, double salary){
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.salary = salary;
    }
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	@Column(name="firstname")
	public String getFirstname(){
		return firstname;
	}
	
	public void setFirstname(String firstname){
		this.firstname = firstname;
	}
	
	@Column(name="lastname")
	public String getLastname(){
		return lastname;
	}
	
	public void setLastname(String lastname){
		this.lastname = lastname;
	}
	
	@Column(name="email")
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	@Column(name="salary")
	public double getSalary(){
		return salary;
	}
	
	public void setSalary(double salary){
		this.salary = salary;
	}
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "employee_position", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "positionid") })
	public Set<Position> getPositions() {
		return this.positions;
	}

	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}
	
	public boolean hasPosition(Position position) {
		for (Position employeePosition: getPositions()) {
			if (employeePosition.getPositionid() == position.getPositionid()) {
				return true;
			}
		}
		return false;
	}	
}