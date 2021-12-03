package es.us.idea.dmn4dq.bpm.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReparationResult {

    private String observedUsabilityProfile;
    private String targetUsabilityProfileDot;
    private List<Action> actions;
    private Long ert;
    private String error;
    private String errorCausedBy;

    public ReparationResult() {
    }

    public String getObservedUsabilityProfile() {
        return observedUsabilityProfile;
    }

    public void setObservedUsabilityProfile(String observedUsabilityProfile) {
        this.observedUsabilityProfile = observedUsabilityProfile;
    }

    public String getTargetUsabilityProfileDot() {
        return targetUsabilityProfileDot;
    }

    public void setTargetUsabilityProfileDot(String targetUsabilityProfileDot) {
        this.targetUsabilityProfileDot = targetUsabilityProfileDot;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Long getErt() {
        return ert;
    }

    public void setErt(Long ert) {
        this.ert = ert;
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
