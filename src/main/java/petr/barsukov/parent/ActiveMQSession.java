package petr.barsukov.parent;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

public class ActiveMQSession {
    private static final Logger LOG = LogManager.getLogger(ActiveMQSession.class.getName());
    private Session session;
    private Connection connection;

    public Session getSession() {
        return session;
    }

    public ActiveMQSession() {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            LOG.trace(e.getStackTrace());
        }
    }

    public void closeSessionAndConnection() throws JMSException {
        session.close();
        connection.close();
    }
}
