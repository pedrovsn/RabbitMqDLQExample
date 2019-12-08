package com.example.demo.controller;

import com.example.demo.domain.Employee;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/")
public class DemoController {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@GetMapping(value = "/producer")
	public ResponseEntity producer(@RequestParam("empId") String id, @RequestParam("empName") String name, @RequestParam("salary") int salary) {
		Employee emp = new Employee();
		emp.setId(id);
		emp.setName(name);
		emp.setSalary(salary);

		amqpTemplate.convertAndSend("demoExchange", "demo", emp);

		return ResponseEntity.ok("Message sent to the RabbitMQ Successfully");
	}
}
