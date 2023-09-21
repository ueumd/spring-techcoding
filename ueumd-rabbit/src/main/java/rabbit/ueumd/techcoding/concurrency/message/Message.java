package rabbit.ueumd.techcoding.concurrency.message;
import lombok.Data;

import java.io.Serializable;

/**
 * Description: TODO
 * Author: hsd
 * Date: 2023-07-02
 */

@Data
public class Message implements Serializable {

    public static final String QUEUE = "QUEUE_081";

    public static final String EXCHANGE = "EXCHANGE_081";

    public static final String ROUTING_KEY = "ROUTING_KEY_081";

    private String id;
}
