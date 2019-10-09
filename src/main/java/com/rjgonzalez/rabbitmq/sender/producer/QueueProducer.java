package com.rjgonzalez.rabbitmq.sender.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rjgonzalez.rabbitmq.sender.dto.ActorRQDTO;

@Component
public class QueueProducer {

	@Value("${fanout.exchange}")
	private String fanoutExchange;

	private final RabbitTemplate rabbitTemplate;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public QueueProducer(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}

	public void produce(ActorRQDTO actorRQDTO) throws Exception {
		logger.info("Storing notification...");
		rabbitTemplate.setExchange(fanoutExchange);
		rabbitTemplate.convertAndSend(new ObjectMapper().writeValueAsString(actorRQDTO));
		logger.info("Notification stored in queue sucessfully");
	}
}
