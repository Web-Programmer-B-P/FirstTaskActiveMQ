package petr.barsukov.producer;

import petr.barsukov.parent.ActiveMQSession;
import javax.jms.JMSException;

public interface ProducerBuilder {
    ProducerBuilder createSession(ActiveMQSession session);

    ProducerBuilder createDestinationQueue(String nameOfQueue) throws JMSException;

    ProducerBuilder createNonPersistentMode() throws JMSException;

    ProducerBuilder createPersistentMode() throws JMSException;

    ProducerBuilder setMessage(String msg) throws JMSException;

    ProducerBuilder createProducer() throws JMSException;

    Producer build();
}
