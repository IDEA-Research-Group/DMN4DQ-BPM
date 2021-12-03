package es.us.idea.dmn4dq.bpm.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.us.idea.dmn4dq.bpm.models.EvaluationResult;
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
public class KafkaEvaluationConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaEvaluationConsumer.class);

    @Inject
    private RuntimeService runtimeService;

    final private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            //.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @KafkaListener(topics="evaluationResult", groupId = "consumer")
    public void consume(ConsumerRecord<String, String> record) {
        String key = record.key();
        String value = record.value();

        logger.info(String.format("Received message with key %s and value %s", key, value));

        MessageCorrelationBuilder mcb = runtimeService.createMessageCorrelation("evaluationResult")
                .processInstanceId(key);

        try {
            EvaluationResult er = objectMapper.readValue(value, EvaluationResult.class);
            if(er.getError() != null) {
                //mcb.setVariable("evaluationError", er.getError());
                mcb.setVariable("evaluationError", Variables.objectValue(er.getError()).serializationDataFormat(Variables.SerializationDataFormats.JAVA));
                mcb.setVariable("evaluationErrorCausedBy", er.getErrorCausedBy());
                //mcb.setVariable("evaluationErrorCausedBy", er.getErrorCausedBy());
            } else {
                mcb.setVariable("evaluationResult", er.getEvaluationResult());
                mcb.setVariable("usabilityProfileDot", er.getUsabilityProfileDot());
                mcb.setVariable("usabilityProfileJson", er.getUsabilityProfileJson());
            }
        } catch(JsonProcessingException e ){
            //mcb.setVariable("evaluationError", "Could not parse Spark's response: "+value);
            mcb.setVariable("evaluationError", Variables.objectValue("Could not parse Spark's response: "+value).serializationDataFormat(Variables.SerializationDataFormats.JAVA));
            mcb.setVariable("evaluationErrorCausedBy", Variables.objectValue(e.toString()).serializationDataFormat(Variables.SerializationDataFormats.JAVA));
        }
        mcb.correlate();
    }

}
