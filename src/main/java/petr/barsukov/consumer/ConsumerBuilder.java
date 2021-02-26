package petr.barsukov.consumer;

import petr.barsukov.parent.ActiveMQSession;
import javax.jms.JMSException;

public interface ConsumerBuilder {
    ConsumerBuilder createSession(ActiveMQSession session);

    ConsumerBuilder createDestinationQueue(String nameOfQueue) throws JMSException;

    ConsumerBuilder createConsumer() throws JMSException;

    Consumer build();
}
