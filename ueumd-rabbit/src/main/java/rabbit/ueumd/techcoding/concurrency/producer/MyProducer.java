package rabbit.ueumd.techcoding.concurrency.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import rabbit.ueumd.techcoding.concurrency.message.Message;

import javax.annotation.Resource;

/**
 * Description: 生产者
 * Author: hsd
 * Date: 2023-07-02
 */
@Component
public class MyProducer {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void syncSend(String id, String routingKey) {
        Message message = new Message();
        message.setId(id);
        rabbitTemplate.convertAndSend(Message.EXCHANGE, routingKey, message);
    }
}
