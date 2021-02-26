package petr.barsukov.consumer;

import petr.barsukov.parent.ParentProducerConsumer;
import javax.jms.*;

public class Consumer extends ParentProducerConsumer implements ConsumerReceiver {

    @Override
    public Message receiveMessage() throws JMSException {
        return consumer.receive();
    }
}
