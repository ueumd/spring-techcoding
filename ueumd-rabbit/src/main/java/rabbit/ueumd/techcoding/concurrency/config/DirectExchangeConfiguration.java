package rabbit.ueumd.techcoding.concurrency.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rabbit.ueumd.techcoding.concurrency.message.Message;

/**
 * Direct Exchange 示例的配置类
 * 对应的消息是 Message08
 *
 * @author 散装java
 * @date 2023-02-18
 */
@Configuration
public class DirectExchangeConfiguration {
    /**
     * 创建一个 Queue
     *
     * @return Queue
     */
    @Bean
    public Queue queue08() {
        // Queue:名字 | durable: 是否持久化 | exclusive: 是否排它 | autoDelete: 是否自动删除
        return new Queue(
                Message.QUEUE,
                true,
                false,
                false);
    }

    /**
     * 创建 Direct Exchange
     *
     * @return DirectExchange
     */
    @Bean
    public DirectExchange exchange08() {
        // name: 交换机名字 | durable: 是否持久化 | exclusive: 是否排它
        return new DirectExchange(Message.EXCHANGE,
                true,
                false);
    }

    /**
     * 创建 Binding
     * Exchange：Message08.EXCHANGE
     * Routing key：Message08.ROUTING_KEY
     * Queue：Message08.QUEUE
     *
     * @return Binding
     */
    @Bean
    public Binding binding08() {
        return BindingBuilder
                .bind(queue08()).to(exchange08())
                .with(Message.ROUTING_KEY);
    }
}
