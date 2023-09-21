package rabbit.ueumd.techcoding;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import rabbit.ueumd.techcoding.concurrency.message.Message;
import rabbit.ueumd.techcoding.concurrency.producer.Component1;
import rabbit.ueumd.techcoding.concurrency.producer.MyProducer;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-07-02
 */
@SpringBootTest
@Slf4j
public class Producer08Test {

    @Resource
    private Component1 component1;

    @Resource
    public MyProducer producer;

    @Test
    public void mockTest() {
        System.out.println(123);
        component1.showName();
    }
    /**
     * 注意测试的方
     */
    @SneakyThrows
    @Test
    public void syncSendHello() {
        // 循环发送十个，观察消费者情况
        for (int i = 0; i < 10; i++) {
            String id = UUID.randomUUID().toString();
            System.out.println(id + Message.ROUTING_KEY);
            producer.syncSend(id, Message.ROUTING_KEY);
        }
        log.info("[{}][test producer08 syncSend] 发送成功", LocalDateTime.now());
        // System.out.println("----------------> 发送成功");
        // 这里多睡一会，确保消息全部消费完成
        TimeUnit.SECONDS.sleep(10);
    }

}