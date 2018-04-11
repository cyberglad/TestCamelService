import javax.jms.ConnectionFactory;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.util.jndi.JndiContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.ConnectionFactory;
import java.io.IOException;

public class JMSCamel {
    public static final void main(String[] args) throws Exception {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        CamelContext camelContext = SpringCamelContext.springCamelContext(
                appContext, false);
        try {
            camelContext.start();
            Thread.sleep(2000);
        } finally {
            camelContext.stop();
        }
    }
}
