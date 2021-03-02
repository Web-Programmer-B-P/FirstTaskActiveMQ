package petr.barsukov.producer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import petr.barsukov.consumer.Consumer;
import petr.barsukov.consumer.ConsumerBuilderImpl;
import petr.barsukov.parent.ActiveMQSession;
import petr.barsukov.utils.Utils;
import javax.jms.JMSException;
import javax.jms.Message;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProducerConsumerTest {
    private static final String TEST_QUEUE = "test";
    private static final String TEST_MESSAGE = "testMessage";
    private static final String TEST_MESSAGE_EMPTY = " ";
    private Producer producer;
    private Consumer consumer;
    private ActiveMQSession activeMQSession;

    @Before
    public void setUp() throws Exception {
        activeMQSession = new ActiveMQSession();
        consumer = new ConsumerBuilderImpl()
                .createSession(activeMQSession)
                .createDestinationQueue(TEST_QUEUE)
                .createConsumer()
                .build();
    }

    @After
    public void destroy() throws Exception {
        activeMQSession.closeSession();
        activeMQSession.destroyQueue(TEST_QUEUE);
    }

    @Test
    public void whenSessionOk() {
        assertNotNull(activeMQSession);
    }

    @Test
    public void whenSendAndReceiveMessage() throws JMSException {
        buildProducer(TEST_MESSAGE);
        producer.sendMessage();
        Message message = consumer.receiveMessage();
        String actual = Utils.getStringOrException(message);
        assertThat(actual, is(TEST_MESSAGE));
    }

    @Test(expected = JMSException.class)
    public void whenThrownException() throws JMSException {
        buildProducer(TEST_MESSAGE_EMPTY);
        producer.sendMessage();
        Message message = consumer.receiveMessage();
        Utils.getStringOrException(message);
    }



    private Producer buildProducer(String message) throws JMSException {
        return producer = new ProducerBuilderImpl()
                .createSession(activeMQSession)
                .createDestinationQueue(TEST_QUEUE)
                .createProducer()
                .createNonPersistentMode()
                .setMessage(message)
                .build();
    }
}