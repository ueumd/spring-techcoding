package rabbit.ueumd.techcoding.concurrency.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import rabbit.ueumd.techcoding.concurrency.message.Message;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-07-02
 */
@Component
@RabbitListener(queues = Message.QUEUE, concurrency = "2")
@Slf4j
public class Consumer {
    @RabbitListener
    public void onMessage(Message message) throws InterruptedException {
        log.info("[{}][Consumer08 onMessage][线程编号:{} 消息内容：{}]", LocalDateTime.now(), Thread.currentThread().getId(), message);
        TimeUnit.SECONDS.sleep(1);
    }
}
