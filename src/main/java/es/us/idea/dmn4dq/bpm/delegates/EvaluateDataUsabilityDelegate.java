package es.us.idea.dmn4dq.bpm.delegates;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.us.idea.dmn4dq.bpm.models.EvaluationRequest;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.UUID;

@Component
public class EvaluateDataUsabilityDelegate implements JavaDelegate {

    @Autowired
    private es.us.idea.dmn4dq.bpm.services.KafkaProducer kafkaProducer;

    Logger LOGGER = LoggerFactory.getLogger(EvaluateDataUsabilityDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String processInstanceId = delegateExecution.getProcessInstanceId(); // asi obtengo el process instance id
        delegateExecution.getCurrentActivityId();
        LOGGER.info("This is just a test! "+delegateExecution.getVariable("record"));

        EvaluationRequest er = new EvaluationRequest(
                (String) delegateExecution.getVariable("typeOfSource"),
                (String) delegateExecution.getVariable("dmnSource"),
                (String) delegateExecution.getVariable("data")
        );

        ObjectMapper mapper = new ObjectMapper();

        //String id = UUID.randomUUID().toString();
        String value = mapper.writeValueAsString(er);

        kafkaProducer.sendMessage("evaluation", processInstanceId, value);

        // Properties props = new Properties();
        // props.put("bootstrap.servers", "192.168.20.140:29092");
        // props.put("acks", "1");
        // props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // Producer<String, String> producer = new KafkaProducer<String, String>(props);
        // producer.send(new ProducerRecord<>("prueba", id, value));
        // producer.flush();
        // producer.close();

        // Consumer<String, String> consumer = new KafkaConsumer<String, String>(props);


    }
}
