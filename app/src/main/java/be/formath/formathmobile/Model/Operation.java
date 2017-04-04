package be.formath.formathmobile.Model;

public class Operation {
    private String code;
    private String label;
    private String response;
    private float rawResponse;
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

    public float getRawResponse() {
        return rawResponse;
    }

    public void setRawResponse(float rawResponse) {
        this.rawResponse = rawResponse;
        String value = String.valueOf(rawResponse);
        if (value.endsWith(".0") || value.endsWith(",0")) {
            value = value.substring(0, value.length() - 2);
        }
        this.setResponse(value);
    }
}
