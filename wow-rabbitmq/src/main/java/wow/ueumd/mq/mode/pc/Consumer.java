package wow.ueumd.mq.mode.pc;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Description: MQ 生产者
 * Author: hsd
 * Date: 2023-06-30 18:10
 */
class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1. 创建一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 2. 设置 rabbitmq ip 地址
        connectionFactory.setHost("localhost");

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
        channel.queueDeclare("hello", true, false, false, null);

        // 6 使用 channel 去 mq 中取消息
        /**
         * 启动一个消费者，并返回服务端生成的消费者标识
         * queue:队列名
         * autoAck：true 接收到传递过来的消息后acknowledged（应答服务器），false 接收到消息后不应答服务器
         * deliverCallback： 当一个消息发送过来后的回调接口
         * cancelCallback：当一个消费者取消订阅时的回调接口;取消消费者订阅队列时除了使用{@link Channel#basicCancel}之外的所有方式都会调用该回调方法
         * @return 服务端生成的消费者标识
         */
        channel.basicConsume(
                "hello",
                true,
                new DeliverCallback() {
                    /**
                     * 当消息从 mq 中取出来了会回调这个方法
                     * 消费者消费消息就在这个 handle中去进行处理
                     */
                    public void handle(String consumerTag, Delivery message) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("消息内容为为: " + new String(message.getBody()));
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
