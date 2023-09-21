package wow.ueumd.mq.mode.pc;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Description: MQ生产者
 * Author: hsd
 * Date: 2023-06-30 18:01
 */
class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1. 创建一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 2. 设置 mq 地址
        connectionFactory.setHost("localhost");

        //amqp协议端口
//        connectionFactory.setPort("");
        //设置具体的vhost(想象成数据库)
//        connectionFactory.setVirtualHost("");
//        //设置用户名密码信息
//        connectionFactory.setUsername("");
//        connectionFactory.setPassword("");

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
        channel.queueDeclare("hello", true, false, true, null);

        // 6. 发送消息
        channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbitmq demo".getBytes());

        // 7. 关闭资源
        channel.close();
        connection.close();
    }
}
