package petr.barsukov.consumer;

import javax.jms.JMSException;
import javax.jms.Message;

public interface ConsumerReceiver {
    Message receiveMessage() throws JMSException;
}
