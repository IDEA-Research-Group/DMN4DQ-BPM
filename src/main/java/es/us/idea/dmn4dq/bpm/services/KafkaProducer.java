package es.us.idea.dmn4dq.bpm.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Service;

@Service("KafkaProducer")
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
//        return new KafkaTemplate<String, String>(producerFactory);
//    }
//
//    @Bean
//    public ProducerFactory<String, String> producerFactory(KafkaProperties kafkaProperties) {
//        return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
//    }

    public void sendMessage(String topic, String key, String data) {
        logger.info("Sending message");
        kafkaTemplate.send(topic, key, data);
    }


}
