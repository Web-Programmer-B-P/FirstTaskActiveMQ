package petr.barsukov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petr.barsukov.consumer.Consumer;
import petr.barsukov.consumer.ConsumerBuilderImpl;
import petr.barsukov.parent.ActiveMQSession;
import petr.barsukov.producer.Producer;
import petr.barsukov.producer.ProducerBuilderImpl;
import petr.barsukov.utils.Utils;

import javax.jms.*;

public class Starter {
    private static final Logger LOG = LogManager.getLogger(Starter.class.getName());

    public static void main(String[] args) {
        ActiveMQSession activeMQSession = new ActiveMQSession();
        try {
            LOG.info(Utils.CREATE_PRODUCER);
            Producer producer = new ProducerBuilderImpl()
                    .createSession(activeMQSession)
                    .createDestinationQueue(Utils.MAIN_QUEUE)
                    .createProducer()
                    .createNonPersistentMode()
                    .setMessage(Utils.TEST_MESSAGE)
//                    .setMessage(Utils.EMPTY_MESSAGE)
                    .build();
            producer.sendMessage();
            LOG.info(Utils.PRODUCER_SENT_MESSAGE_SUCCESS);

            LOG.info(Utils.CREATE_CONSUMER);
            Consumer consumer = new ConsumerBuilderImpl()
                    .createSession(activeMQSession)
                    .createDestinationQueue(Utils.MAIN_QUEUE)
                    .createConsumer()
                    .build();
            Message message = consumer.receiveMessage();
            LOG.info(Utils.CONSUMER_RECEIVED_MESSAGE);

            String validMessage = Utils.getStringOrException(message);

            LOG.info(Utils.MESSAGE_VALIDATED_SUCCESS);
            producer = new ProducerBuilderImpl()
                    .createSession(activeMQSession)
                    .createDestinationQueue(Utils.TWO_DESTINATIONS)
                    .createProducer()
                    .createNonPersistentMode()
                    .setMessage(validMessage)
                    .build();
            producer.sendMessage();
            LOG.info(Utils.MESSAGE_SENT_DESTINATIONS);

            activeMQSession.closeSession();
            activeMQSession.closeConnection();
        } catch (JMSException jmsException) {
            LOG.trace(jmsException.getMessage());
        }
    }
}
