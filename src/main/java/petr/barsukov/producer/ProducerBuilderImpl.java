package petr.barsukov.producer;

import petr.barsukov.parent.ActiveMQSession;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import java.util.Objects;

public class ProducerBuilderImpl implements ProducerBuilder {
    private final Producer producer = new Producer();

    @Override
    public ProducerBuilder createSession(ActiveMQSession session) {
        producer.setSession(session.getSession());
        return this;
    }

    @Override
    public ProducerBuilder createDestinationQueue(String nameOfQueue) throws JMSException {
        if (Objects.isNull(nameOfQueue)) {
            producer.setDestination(producer.getSession().createQueue(Producer.DEFAULT_QUEUE));
        } else {
            producer.setDestination(producer.getSession().createQueue(nameOfQueue));
        }
        return this;
    }

    @Override
    public ProducerBuilder createProducer() throws JMSException {
        producer.setProducer(
                producer.getSession()
                        .createProducer(producer.getDestination()));
        return this;
    }

    @Override
    public ProducerBuilder createNonPersistentMode() throws JMSException {
        producer.getProducer().setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        return this;
    }

    @Override
    public ProducerBuilder createPersistentMode() throws JMSException {
        producer.getProducer().setDeliveryMode(DeliveryMode.PERSISTENT);
        return this;
    }

    @Override
    public ProducerBuilder setMessage(String msg) throws JMSException {
        producer.setTextMessage(producer.getSession().createTextMessage(msg));
        return this;
    }

    @Override
    public Producer build() {
        return producer;
    }
}
