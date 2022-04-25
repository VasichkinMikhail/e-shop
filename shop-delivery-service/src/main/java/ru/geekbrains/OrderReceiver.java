package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.service.dto.OrderStatus;

@RabbitListener(queues = "new.order.queue")
public class OrderReceiver {

    private static final Logger logger = LoggerFactory.getLogger(OrderReceiver.class);

    private final AmqpTemplate rabbitTemplate;

    @Autowired
    public OrderReceiver(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitHandler
    public void receive(OrderStatus order) {
        logger.info("New order received for processing '{}'", order.getOrderId());

        new Thread(() -> {
            for (OrderStatus.OrderStatusValue status : OrderStatus.OrderStatusValue.values()) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("Sending next status {} for order {}", status, order.getOrderId());
                order.setStatus(status.toString());
                rabbitTemplate.convertAndSend("order.exchange", "processed_order", order);
            }
        }).start();
    }
}
