package edu.mum.cs545;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.mum.cs545.domain.Employee;
import edu.mum.cs545.domain.EmployeeRepository;
import edu.mum.cs545.domain.Position;
import edu.mum.cs545.domain.PositionRepository;
import edu.mum.cs545.domain.User;
import edu.mum.cs545.domain.UserRepository;

@SpringBootApplication
public class CrudbootApplication {
	
	private static final Logger log = LoggerFactory.getLogger(CrudbootApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(CrudbootApplication.class, args);
	}
	
	/**
	 * Save employees and positions to H2 DB for testing
	 * @param repository
	 * @return
	 */
	@Bean
	public CommandLineRunner demo(EmployeeRepository repository, PositionRepository prepository, UserRepository urepository) {
		return (args) -> {
			// save employees
			Employee employee1 = new Employee("John", "Johnson", "john@john.com", 100000); 
			repository.save(new Employee("Steve", "Stevens", "steve.stevent@st.com", 90000));
			repository.save(new Employee("Mary", "Robinson", "mary@robinson.com", 120000));
			repository.save(new Employee("Kate", "Keystone", "kate@kate.com", 115000));
			repository.save(new Employee("Diana", "Doll", "diana@doll.com", 130000));
			
			Position position1 = new Position("Developer");
			Position position2 = new Position("Tester");
			prepository.save(new Position("Finance"));
			prepository.save(new Position("Marketing"));
			
			prepository.save(position1);
			prepository.save(position2);
			
			Set<Position> positions = new HashSet<Position>();
			positions.add(position1);
			positions.add(position2);
			
			employee1.setPositions(positions); 
			repository.save(employee1);

			// Create users with BCrypt encoded password (user/user, admin/admin)
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "ADMIN");
			urepository.save(user1);
			urepository.save(user2); 
		};
	}
}
