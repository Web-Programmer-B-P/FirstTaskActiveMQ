package petr.barsukov.parent;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import java.util.Objects;

public class ActiveMQSession {
    private static final Logger LOG = LogManager.getLogger(ActiveMQSession.class.getName());
    private Session session;
    private ActiveMQConnection connection;

    public Session getSession() {
        return session;
    }

    public ActiveMQSession() {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
            connection = (ActiveMQConnection) factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            LOG.trace(e.getStackTrace());
        }
    }

    public void destroyQueue(String name) throws JMSException {
        connection.destroyDestination(new ActiveMQQueue(name));
    }

    public void closeSession() throws JMSException {
        if (Objects.nonNull(session)) {
            session.close();
        }
    }

    public void closeConnection() throws JMSException {
        if (Objects.nonNull(connection)) {
            connection.close();
        }
    }
}
