package com.rjgonzalez.rabbitmq.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rjgonzalez.rabbitmq.sender.dto.ActorRQDTO;
import com.rjgonzalez.rabbitmq.sender.producer.QueueProducer;

@RestController
@RequestMapping({ "api/actor" })
public class ActorController {

	QueueProducer queueProducer;

	@Autowired
	public ActorController(QueueProducer queueProducer) {
		this.queueProducer = queueProducer;
	}

	@PostMapping(path = "/createActor", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void createActor(@RequestBody ActorRQDTO actorRQDTO) throws Exception {

		queueProducer.produce(actorRQDTO);

	}

}
