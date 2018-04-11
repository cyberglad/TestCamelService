import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpComponent;
import org.apache.camel.converter.stream.CachedOutputStream;
import org.apache.camel.impl.DefaultCamelContext;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class FileCopier {

        public static void main(String args[]) throws Exception {
            CamelContext context = new DefaultCamelContext();
            HttpComponent httpComponent = new HttpComponent();
            context.addComponent("http", httpComponent);
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("timer:foo?period=5000")
                    .from("direct:start")
                    .to("http4://localhost:8888/zips")
                            .log("Me Got ${body}")
                            .to("log:output?showAll=true")
                            .to("file:d:\\");
                }
            });
            context.start();
            Thread.sleep(10000);
            context.stop();
        }
    /*public static void main(String[] args) {
        CamelContext context = new DefaultCamelContext();

        try {
            HttpComponent httpComponent = new HttpComponent();
            context.addComponent("http4", httpComponent);
            ProducerTemplate template = context.createProducerTemplate();
            context.start();

            Exchange exchange = template
                    .request(
                            "http4://localhost:8888/zips",
                            new Processor() {
                                public void process(Exchange exchange)
                                        throws Exception {
                                }
                            });

            if (null != exchange) {
                Message out = exchange.getOut();
                String text= out.getBody(String.class);

                System.out.println(text);
                int responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE,
                        Integer.class);
                System.out.println("Response: " + String.valueOf(responseCode));
            }

            Thread.sleep(1000 * 3);
            context.stop();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }

        System.out.println("DONE!!");
    }*/

}
