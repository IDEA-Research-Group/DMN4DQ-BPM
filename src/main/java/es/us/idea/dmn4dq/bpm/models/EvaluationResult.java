package es.us.idea.dmn4dq.bpm.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EvaluationResult {

    private String evaluationResult;
    private String usabilityProfileDot;
    private String usabilityProfileJson;
    private String error;
    private String errorCausedBy;

    public EvaluationResult() {
    }

    public String getEvaluationResult() {
        return evaluationResult;
    }

    public void setEvaluationResult(String evaluationResult) {
        this.evaluationResult = evaluationResult;
    }

    public String getUsabilityProfileDot() {
        return usabilityProfileDot;
    }

    public void setUsabilityProfileDot(String usabilityProfileDot) {
        this.usabilityProfileDot = usabilityProfileDot;
    }

    public String getUsabilityProfileJson() {
        return usabilityProfileJson;
    }

    public void setUsabilityProfileJson(String usabilityProfileJson) {
        this.usabilityProfileJson = usabilityProfileJson;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorCausedBy() {
        return errorCausedBy;
    }

    public void setErrorCausedBy(String errorCausedBy) {
        this.errorCausedBy = errorCausedBy;
    }
}
