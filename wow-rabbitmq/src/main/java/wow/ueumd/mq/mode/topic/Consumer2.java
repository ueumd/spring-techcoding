package wow.ueumd.mq.mode.topic;

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
        String exchangeName = "topic mode";
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(host);

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        // Topic模式的消息接收时必须要指定RoutingKey并且可以使用# 和 *来做统配符号
        // #表示通配任意一个单词，
        // *表示通配任意多个单词，例如aa.*.*或者aa.#都可以接收到 routingKey 为 aa.bb.cc 的发送者发送的消息
        channel.exchangeDeclare(exchangeName, "topic");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, exchangeName, "user.*");

        channel.basicConsume(
                queue,
                true,
                new DeliverCallback() {
                    /**
                     * 当消息从 mq 中取出来了会回调这个方法
                     * 消费者消费消息就在这个 handle中去进行处理
                     */
                    public void handle(String consumerTag, Delivery message){
                        System.out.println("消费者 2 user消息内容为：" + new String(message.getBody()));
                    }
                },
                new CancelCallback() {
                    /**
                     * 当消息取消了会回调这个方法
                     * @param consumerTag
                     * @throws IOException
                     */
                    public void handle(String consumerTag) {
                        System.out.println("1111");
                    }
                }
        );
    }
}
