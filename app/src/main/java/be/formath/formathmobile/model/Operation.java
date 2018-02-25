package be.formath.formathmobile.model;

public class Operation {
    private String code;
    private String label;
    private String response;
    private String givenResponse;
    private boolean result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGivenResponse() {
        return givenResponse;
    }

    public void setGivenResponse(String givenResponse) {
        this.givenResponse = givenResponse;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isCorrect() {
        return givenResponse.equals(response);
    }

}
