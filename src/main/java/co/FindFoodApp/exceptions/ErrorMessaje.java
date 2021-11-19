package co.FindFoodApp.exceptions;

public class ErrorMessaje {
    private String type;
    private String message;

    public ErrorMessaje(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public ErrorMessaje(){
        this.type = "";
        this.message ="";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    


}
