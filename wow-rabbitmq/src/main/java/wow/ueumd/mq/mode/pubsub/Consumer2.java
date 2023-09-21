package wow.ueumd.mq.mode.pubsub;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-07-01
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        String host = "localhost";
        String exchangeName = "pubsub-change";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(exchangeName, "fanout");

        String queue = channel.queueDeclare().getQueue();

        channel.queueBind(queue, exchangeName, "");

        channel.basicConsume(
                queue,
                true,
                new DeliverCallback() {

                    public void handle(String consumerTag, Delivery message) throws IOException {
                        System.out.println("消费者 2 消息内容为：" + new String(message.getBody()));
                    }
                },
                new CancelCallback() {
                    public void handle(String consumerTag) throws IOException {
                        System.out.println("1111");
                    }
                }
        );
    }
}
