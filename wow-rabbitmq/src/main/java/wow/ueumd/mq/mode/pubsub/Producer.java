package wow.ueumd.mq.mode.pubsub;

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
        String exchangeName = "pubsub-change";

        // 1创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 2设置IP
        connectionFactory.setHost(host);

        // 3创建连接
        Connection connection = connectionFactory.newConnection();

        // 4创建channel
        Channel channel = connection.createChannel();

        // 5设置队列属性

        /**
         * 第一个参数：交换机名字
         * 第二个参数：交换机类型
         */
        channel.exchangeDeclare(exchangeName, "fanout");

        // 6 发送消息
        /**
         * 第一个参数： 交换机名称
         * 第二个参数： 路由 key
         * 第三个参数： 消息属性
         * 第四个参数： 消息内容
         */
        channel.basicPublish(exchangeName, "", MessageProperties.PERSISTENT_TEXT_PLAIN, "Hello rabbitmq pubsub model".getBytes());
    }
}
