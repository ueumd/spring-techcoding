package wow.ueumd.mq.mode.pubsub;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-07-01
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        String host = "localhost";
        String exchangeName = "pubsub-change";

        // 1 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 2 设置 rabbititmq ip 地址
        connectionFactory.setHost(host);

        // 3 创建连接
        Connection connection = connectionFactory.newConnection();

        // 4 创建channel
        Channel channel = connection.createChannel();

        // 5 设置队列属性
        // 使用fanout模式获取消息时不需要绑定特定的队列名称，只需使用channel.queueDeclare().getQueue();
        // 获取一个随机的队列名称，然后绑定到指定的Exchange即可获取消息。
        // 这种模式中，可以同时启动多个接收者，只要都绑定到同一个Exchange上，即可让所有接收者同时接收同一个消息，是一种广播的消息机制
        channel.exchangeDeclare(exchangeName, "fanout");
        String queue = channel.queueDeclare().getQueue();

        channel.queueBind(queue, exchangeName, "");

        // 6 使用 chanel 去 rabbitmq 中去取消息进行消费
        /**
         * 第一个参数：队列名称
         * 第二个参数：是否自动签收
         */
        channel.basicConsume(
                queue,
                true,
                new DeliverCallback() {

                    public void handle(String consumerTag, Delivery message) throws IOException {
                        System.out.println("消费者 1 消息内容为：" + new String(message.getBody()));
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
