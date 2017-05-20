package edu.mum.cs545;

import org.junit.Test;
import org.junit.runner.*;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.mum.cs545.CrudbootApplication;
import edu.mum.cs545.WebSecurityConfig;
import edu.mum.cs545.domain.Employee;
import edu.mum.cs545.domain.EmployeeRepository;
import edu.mum.cs545.domain.User;
import edu.mum.cs545.domain.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {CrudbootApplication.class, WebSecurityConfig.class })
public class CrudbootApplicationTests {


	private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private EmployeeRepository employeeRepository;

    @Autowired
    public void setStudentRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }    
    
    @Test
    public void addUser() {
    	User user = new User("testuser", "testuser", "USER");

    	assertNull(user.getId());
    	userRepository.save(user);
    	assertNotNull(user.getId());
    }
    
	@Test
    public void addEmployee() {
		Employee employee = new Employee("Test", "Student", "test@test.com", 150000);
		
		employeeRepository.save(employee);
		Employee findEmployee = employeeRepository.findOne(employee.getId());
		assertNotNull(findEmployee);
    }
}