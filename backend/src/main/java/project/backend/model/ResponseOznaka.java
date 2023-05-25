package project.backend.model;

import java.util.List;

public class ResponseOznaka {

    List<String> oznake;

    boolean success;

    public ResponseOznaka(List<String> oznake, boolean success) {
        super();
        this.oznake = oznake;
        this.success = success;
    }

    public List<String> getOznake() {
        return oznake;
    }

    public void setOznake(List<String> oznake) {
        this.oznake = oznake;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
