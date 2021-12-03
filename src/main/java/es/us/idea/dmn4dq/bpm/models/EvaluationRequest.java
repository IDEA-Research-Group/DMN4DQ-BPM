package es.us.idea.dmn4dq.bpm.models;

public class EvaluationRequest {
    
    private String typeOfSource;
    private String dmnSource;
    private String data;

    public EvaluationRequest(String typeOfSource, String dmnSource, String data) {
        this.typeOfSource = typeOfSource;
        this.dmnSource = dmnSource;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
