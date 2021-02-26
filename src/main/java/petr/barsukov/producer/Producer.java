package petr.barsukov.producer;

import petr.barsukov.parent.ParentProducerConsumer;
import javax.jms.*;
import java.util.Objects;

public class Producer extends ParentProducerConsumer implements ProducerSender {
    public static final String DEFAULT_QUEUE = "default";

    @Override
    public void sendMessage() throws JMSException {
        if (Objects.nonNull(textMessage)) {
            producer.send(textMessage);
        }
    }
}
