package ua.profitsoft.second_method.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonAutoDetect
@JsonPropertyOrder({"date_time", "first_name", "last_name", "type", "fine_amount" })
public class Violation {

    @JsonIgnore
    @JsonProperty("date_time")
    private String date_time;

    @JsonIgnore
    @JsonProperty("first_name")
    private String first_name;

    @JsonIgnore
    @JsonProperty("last_name")
    private String last_name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("fine_amount")
    private double fine_amount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getFine_amount() {
        return fine_amount;
    }

    public void setFine_amount(double fine_amount) {
        this.fine_amount = fine_amount;
    }

}
