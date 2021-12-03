package es.us.idea.dmn4dq.bpm.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Action {

    private String brdv;
    private String from;
    private String to;
    private Integer cost;

    public Action() {
    }

    public String getBrdv() {
        return brdv;
    }

    public void setBrdv(String brdv) {
        this.brdv = brdv;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
