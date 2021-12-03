package es.us.idea.dmn4dq.bpm.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.us.idea.dmn4dq.bpm.models.EvaluationResult;
import es.us.idea.dmn4dq.bpm.models.ReparationResult;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import org.camunda.bpm.engine.variable.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class KafkaReparationConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaReparationConsumer.class);

    @Inject
    private RuntimeService runtimeService;

    final private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

    @KafkaListener(topics="reparationResult", groupId = "consumer")
    public void consume(ConsumerRecord<String, String> record) {
        String key = record.key();
        String value = record.value();

        logger.info(String.format("Received message with key %s and value %s", key, value));

        ObjectMapper mapper = new ObjectMapper();

        MessageCorrelationBuilder mcb = runtimeService.createMessageCorrelation("reparationResult")
                .processInstanceId(key);

        try {
            ReparationResult rr = objectMapper.readValue(value, ReparationResult.class);
            if(rr.getError() != null) {
                mcb.setVariable("reparationError", Variables.objectValue(rr.getError()).serializationDataFormat(Variables.SerializationDataFormats.JAVA));
                mcb.setVariable("reparationErrorCausedBy", rr.getErrorCausedBy());
            } else {
                mcb.setVariable("targetUsabilityProfileDot", rr.getTargetUsabilityProfileDot());
                mcb.setVariable("optimalCorrectiveActions", mapper.writeValueAsString(rr.getActions()));
            }
        } catch(JsonProcessingException e ){
            mcb.setVariable("reparationError", Variables.objectValue("Could not parse Spark's response: "+value).serializationDataFormat(Variables.SerializationDataFormats.JAVA));
            mcb.setVariable("reparationErrorCausedBy", Variables.objectValue(e.toString()).serializationDataFormat(Variables.SerializationDataFormats.JAVA));
        }
        mcb.correlate();
    }

}
