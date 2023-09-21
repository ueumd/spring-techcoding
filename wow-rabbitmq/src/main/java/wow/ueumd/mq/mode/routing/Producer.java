package wow.ueumd.mq.mode.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Description: Routing
 * Author: hsd
 * Date: 2023-07-01
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        String host = "localhost";
        String exchangeName = "pubsub-routing";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        //5 设置队列属性
        /**
         * 第一个参数：交换机名字
         * 第二个参数：交换机类型
         */
        channel.exchangeDeclare(exchangeName, "direct");

        //6 发送消息
        /**
         * 第一个参数： 交换机名称
         * 第二个参数： 路由 key
         * 第三个参数： 消息属性
         * 第四个参数： 消息内容
         */
        channel.basicPublish(exchangeName, "info", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbitmq routing mode".getBytes());

        channel.close();
        connection.close();
    }
}
