package petr.barsukov.parent;

import lombok.Getter;
import lombok.Setter;
import javax.jms.*;

@Getter
@Setter
public class ParentProducerConsumer {
    protected Session session;
    protected Destination destination;
    protected MessageProducer producer;
    protected MessageConsumer consumer;
    protected TextMessage textMessage;
    protected Message message;
}
