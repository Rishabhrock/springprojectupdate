package com.ascent.springproject.service;

import com.ascent.springproject.model.CtcDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${ascent.rabbitmq.exchange}")
    private String exchange;

    @Value("${ascent.rabbitmq.routingkey}")
    private String routingkey;

    public void send(CtcDto ctcDto) {
        rabbitTemplate.convertAndSend(exchange, routingkey, ctcDto);
        System.out.println("Send msg = " + ctcDto);

    }
}
