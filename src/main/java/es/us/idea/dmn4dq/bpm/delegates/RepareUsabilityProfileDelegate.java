package es.us.idea.dmn4dq.bpm.delegates;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.us.idea.dmn4dq.bpm.models.ReparationRequest;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepareUsabilityProfileDelegate implements JavaDelegate {

    @Autowired
    private es.us.idea.dmn4dq.bpm.services.KafkaProducer kafkaProducer;

    Logger LOGGER = LoggerFactory.getLogger(RepareUsabilityProfileDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String processInstanceId = delegateExecution.getProcessInstanceId();
        delegateExecution.getCurrentActivityId();

        ReparationRequest rq = new ReparationRequest(
                (String) delegateExecution.getVariable("typeOfSource"),
                (String) delegateExecution.getVariable("dmnSource"),
                (String) delegateExecution.getVariable("usabilityProfileJson"),
                (String) delegateExecution.getVariable("setCorrectiveActions"),
                (String) delegateExecution.getVariable("targetDecision")
        );

        ObjectMapper mapper = new ObjectMapper();

        String value = mapper.writeValueAsString(rq);

        kafkaProducer.sendMessage("reparation", processInstanceId, value);
    }
}
