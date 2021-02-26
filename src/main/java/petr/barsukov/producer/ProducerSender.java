package petr.barsukov.producer;

import javax.jms.JMSException;

public interface ProducerSender {
    void sendMessage() throws JMSException;
}
