package petr.barsukov.consumer;

import petr.barsukov.parent.ActiveMQSession;
import javax.jms.JMSException;

public class ConsumerBuilderImpl implements ConsumerBuilder {
    private Consumer consumer = new Consumer();

    @Override
    public ConsumerBuilder createSession(ActiveMQSession session) {
        consumer.setSession(session.getSession());
        return this;
    }

    @Override
    public ConsumerBuilder createDestinationQueue(String nameOfQueue) throws JMSException {
        consumer.setDestination(consumer.getSession().createQueue(nameOfQueue));
        return this;
    }

    @Override
    public ConsumerBuilder createConsumer() throws JMSException {
        consumer.setConsumer(
                consumer.getSession()
                        .createConsumer(consumer.getDestination()));
        return this;
    }

    @Override
    public Consumer build() {
        return consumer;
    }
}
