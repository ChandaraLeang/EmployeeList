package edu.mum.cs545.domain;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Position {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)	
	private long positionid;
	
    @Column(name="positionname")
	private String name; 
     
    @ManyToMany(mappedBy = "positions")    
    private Set<Employee> employees;
    
    public Position(){
    	
    }
    
    public Position(String name){
    	this.name = name;
    }
    
    public long getPositionid(){
    	return positionid;
    }
    
    public void setPositionid(long positionid){
    	this.positionid = positionid;
    }
    
    public String getName(){
    	return name;
    }
    
    public void setName(String name){
    	this.name = name;
    }
    
    public Set<Employee> getEmployees(){
    	return employees;
    }
    
    public void setEmployees(Set<Employee> employees){
    	this.employees = employees;
    }
}
