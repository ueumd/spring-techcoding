package wow.ueumd.mq.mode.wrok;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Description: MQ 生产者
 * Author: hsd
 * Date: 2023-06-30 18:10
 */
class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {

        String host = "localhost";
        String queueName = "work-02";

        // 1. 创建一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 2. 设置 rabbitmq ip 地址
        connectionFactory.setHost(host);

        // 3 创建 Connection 对象
        Connection connection = connectionFactory.newConnection();

        // 4 创建Channel
        Channel channel = connection.createChannel();

        // 5 设置队列属性
        /**
         * 第一个参数：队列名称
         * 第二个参数：队列是否要持久化
         * 第三个参数：是否排他性
         * 第四个参数：是否自动删除,如果没有消费者连接就自动删除
         * 第五个参数：是否要设置一些额外参数
         * */
        channel.queueDeclare(queueName, true, false, false, null);

        // 能者多收，不在公平接收
        channel.basicQos(1);

        // 6 使用 channel 去 mq 中取消息
        /**
         * 第一个参数：队列名称
         * 第二个参数：是否自动签收 false 手动签收
         */
        channel.basicConsume(
                queueName,
                false,
                new DeliverCallback() {
                    /**
                     * 当消息从 mq 中取出来了会回调这个方法
                     * 消费者消费消息就在这个 handle中去进行处理
                     */
                    public void handle(String consumerTag, Delivery message) throws IOException {
                        try {
                           Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("消费者 1：消息内容为为" + new String(message.getBody()));
                        channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                    }
                },
                new CancelCallback() {
                    /**
                     * 当消息取消了会回调这个方法
                     * @param consumerTag
                     */
                    public void handle(String consumerTag) {
                        System.out.println("1111" + consumerTag);
                    }
                }
        );
    }
}
