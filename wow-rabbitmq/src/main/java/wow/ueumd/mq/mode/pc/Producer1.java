package wow.ueumd.mq.mode.pc;

import com.rabbitmq.client.*;
import com.rabbitmq.client.AMQP.BasicProperties.Builder;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Description: MQ生产者
 * Author: hsd
 * Date: 2023-06-30 18:01
 */
class Producer1 {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 1. 创建一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 2. 设置 mq 地址
        connectionFactory.setHost("localhost");


        // 3. 创建Connection 对象
        Connection connection = connectionFactory.newConnection();

        // 4. 创建channel
        Channel channel = connection.createChannel();

        // 5. 设置队例属性
        /**
         * 第一个参数：队列名称
         * 第二个参数：队列是否要持久化
         * 第三个参数：是否排他性
         * 第四个参数：是否自动删除
         * 第五个参数：是否要设置一些额外参数
         */

        channel.queueDeclare("hello", true, false, false, null);

        // 6. 发送消息
        // channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbitmq demo".getBytes());

        // 6. 发送消息 消息持久化测试
        for (int i = 0; i < 10; i++) {

            String message = "hello xiangjiao " + i;
            System.out.println("send msg = " + message);

            //消息持久化测试
            Builder builder = new Builder();
            builder.deliveryMode(2);
            BasicProperties properties = builder.build();

            channel.basicPublish("", "hello", (AMQP.BasicProperties) properties, message.getBytes());

            //消息发送慢一点
            Thread.sleep(i * 5);
        }

        // 7. 关闭资源
        channel.close();
        connection.close();
    }
}
