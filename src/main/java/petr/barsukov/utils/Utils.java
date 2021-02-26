package petr.barsukov.utils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public final class Utils {
    public final static String MAIN_QUEUE = "DISPATCHER_QUEUE";
    public final static String TWO_DESTINATIONS = "QUEUE1, QUEUE2";
    public final static String TEST_MESSAGE = "Hello world!";
    public final static String EMPTY_MESSAGE = " ";
    public final static String CREATE_PRODUCER = "Билдим отправителя...";
    public final static String PRODUCER_SENT_MESSAGE_SUCCESS = "Отправитель успешно отправил сообщение!";
    public final static String CREATE_CONSUMER = "Билдим получателя...";
    public final static String CONSUMER_RECEIVED_MESSAGE = "Получатель вычитал сообщение успешно!";
    public final static String MESSAGE_VALIDATED_SUCCESS = "Сообщение прошло валидацию успешно!";
    public final static String MESSAGE_SENT_DESTINATIONS = "Сообщение отправлено в обе очереди успешно!";
    public final static String MESSAGE_IS_EMPTY = "Сообщение пустое!";

    private Utils() {
    }

    public static boolean validateMessage(Message message) throws JMSException {
        boolean result = false;
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            result = !textMessage.getText().isBlank();
        }
        return result;
    }

    public static String transformMessageToString(Message msg) throws JMSException {
        TextMessage textMessage = (TextMessage) msg;
        return textMessage.getText();
    }
}
