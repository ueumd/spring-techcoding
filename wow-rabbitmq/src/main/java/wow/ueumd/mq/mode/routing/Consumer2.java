package wow.ueumd.mq.mode.routing;

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
        String exchangeName = "pubsub-routing";

        ConnectionFactory factory  = new ConnectionFactory();

        factory.setHost(host);

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        //5 设置队列属性 交换机
        // 使用Exchange的direct模式时接收者的RoutingKey必须要与发送时的RoutingKey完全一致否则无法获取消息，接收消息时队列名也必须要发送消息时的完全一致
        channel.exchangeDeclare(exchangeName, "direct");

        //创建一个随机的默认的队列，队列名是随机的，当然也可以自己指定队列名
        String queue = channel.queueDeclare().getQueue();

        // 接收类型，如果消息生产者为 info 消息2不会接收
        channel.queueBind(queue, exchangeName, "trace");
        channel.queueBind(queue, exchangeName, "error");

        //6使用 chanel 去 rabbitmq 中去取消息进行消费
        /**
         * 第一个参数：队列名称
         * 第二个参数：是否自动签收
         */
        channel.basicConsume(
                queue,
                true,
                new DeliverCallback() {
                    /**
                     * 当消息从 mq 中取出来了会回调这个方法
                     * 消费者消费消息就在这个 handle中去进行处理
                     */
                    public void handle(String consumerTag, Delivery message) {
                        System.out.println("消费者 2 消息内容为：" + new String(message.getBody()));
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
