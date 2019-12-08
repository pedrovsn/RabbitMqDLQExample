package com.example.demo.service;

import com.example.demo.domain.Employee;
import com.example.demo.exception.InvalidSalaryException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	private static final int MIN_SALARY = 998;

	@RabbitListener(queues = "demo.queue")
	public void receivedMessage(Employee employee) throws InvalidSalaryException {
		System.out.println("Received message from RabbitMQ <"+employee+">");
		if(employee.getSalary() < MIN_SALARY) {
			throw new InvalidSalaryException();
		}
	}
}
