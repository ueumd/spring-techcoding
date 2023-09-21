package wow.ueumd.mq.mode.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-07-01
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        String host = "localhost";
        String exchangeName = "topic mode";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare(exchangeName, "topic");

        channel.basicPublish(exchangeName, "employee.save", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbitmq topic".getBytes());

        channel.close();
        connection.close();
    }

}
