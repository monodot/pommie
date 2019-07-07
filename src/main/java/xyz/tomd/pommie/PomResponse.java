package xyz.tomd.pommie;

public class PomResponse {

    private String result;

    // Required for JSON serialisation
    public PomResponse() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
