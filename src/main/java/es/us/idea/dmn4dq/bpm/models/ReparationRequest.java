package es.us.idea.dmn4dq.bpm.models;

public class ReparationRequest {

    private String typeOfSource;
    private String dmnSource;
    private String usabilityProfileJson;
    private String setCorrectiveActions;
    private String targetDecision;

    public ReparationRequest(String typeOfSource, String dmnSource, String usabilityProfileJson, String setCorrectiveActions, String targetDecision) {
        this.typeOfSource = typeOfSource;
        this.dmnSource = dmnSource;
        this.usabilityProfileJson = usabilityProfileJson;
        this.setCorrectiveActions = setCorrectiveActions;
        this.targetDecision = targetDecision;
    }

    public String getTypeOfSource() {
        return typeOfSource;
    }

    public void setTypeOfSource(String typeOfSource) {
        this.typeOfSource = typeOfSource;
    }

    public String getDmnSource() {
        return dmnSource;
    }

    public void setDmnSource(String dmnSource) {
        this.dmnSource = dmnSource;
    }

    public String getUsabilityProfileJson() {
        return usabilityProfileJson;
    }

    public void setUsabilityProfileJson(String usabilityProfileJson) {
        this.usabilityProfileJson = usabilityProfileJson;
    }

    public String getSetCorrectiveActions() {
        return setCorrectiveActions;
    }

    public void setSetCorrectiveActions(String setCorrectiveActions) {
        this.setCorrectiveActions = setCorrectiveActions;
    }

    public String getTargetDecision() {
        return targetDecision;
    }

    public void setTargetDecision(String targetDecision) {
        this.targetDecision = targetDecision;
    }
}
