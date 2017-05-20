package edu.mum.cs545.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.mum.cs545.domain.Employee;
import edu.mum.cs545.domain.EmployeeRepository;
import edu.mum.cs545.domain.Position;
import edu.mum.cs545.domain.PositionRepository;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeRepository repository;
	@Autowired
	private PositionRepository prepository;
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/employees")
	public String index(Model model){
		List<Employee> employees = (List<Employee>) repository.findAll();
		model.addAttribute("employees", employees);
		return "employees";
	}
	
	@RequestMapping(value = "add")
	public String addEmployee(Model model){
		model.addAttribute("employee", new Employee());
		return "addEmployee";
	}
	
	@RequestMapping(value = "/edit/{id}")
    public String editEmployee(@PathVariable("id") Long employeeId, Model model){
    	model.addAttribute("employee", repository.findOne(employeeId));
        return "editEmployee";
    }	    
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Employee employee){
        repository.save(employee);
    	return "redirect:/employees";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable("id") Long employeeId, Model model) {
    	repository.delete(employeeId);
        return "redirect:/employees";
    }    
    
    @RequestMapping(value = "addEmployeePosition/{id}", method = RequestMethod.GET)
    public String addPosition(@PathVariable("id") Long employeeId, Model model){
    	model.addAttribute("positions", prepository.findAll());
		model.addAttribute("employee", repository.findOne(employeeId));
    	return "addEmployeePosition";
    }
    
    
    @RequestMapping(value="/employee/{id}/positions", method=RequestMethod.GET)
	public String emplyeesAddPosition(@PathVariable Long id, @RequestParam Long positionId, Model model) {
		Position position = prepository.findOne(positionId);
		Employee employee = repository.findOne(id);

		if (employee != null) {
			if (!employee.hasPosition(position)) {
				employee.getPositions().add(position);
			}
			repository.save(employee);
			model.addAttribute("employee", prepository.findOne(id));
			model.addAttribute("positions", prepository.findAll());
			return "redirect:/employees";
		}

		model.addAttribute("developers", repository.findAll());
		return "redirect:/employees";
	}    
    
    @RequestMapping(value = "getemployees", method = RequestMethod.GET)
    public @ResponseBody List<Employee> getEmployees() {
            return (List<Employee>)repository.findAll();
    } 
}
