package com.example.mqmicroservicesender.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mqmicroservicesender.config.RabbitMqConfiguration;
import com.example.mqmicroservicesender.entities.CompletedOrder;
import com.example.mqmicroservicesender.repositories.OrderRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@GetMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CompletedOrder>> getAll(Pageable pageable) {
		
		return new ResponseEntity<List<CompletedOrder>>(orderRepository.findAll(), HttpStatus.OK);
	}
	
	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CompletedOrder> save(@RequestBody CompletedOrder order) {
		
		CompletedOrder savedOrder = orderRepository.save(order);
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(RabbitMqConfiguration.queueName, savedOrder.toString());
        
		return new ResponseEntity<CompletedOrder>(savedOrder, HttpStatus.CREATED);
	}
	
}
