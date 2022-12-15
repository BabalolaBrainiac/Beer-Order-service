package guru.sfg.beer.order.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;


@Configuration
public class JMSConfig {
    public static final String QUEUE = "messageQueue";
    public static final String SEND_AND_RECEIVE_QUEUE = "sendAndReceive";
    public static final String BEER_ORDER_QUEUE = "BEER_ORDER_QUEUE" ;

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");

        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
