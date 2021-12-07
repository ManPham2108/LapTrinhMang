
package Model;


public class SendMessageModel {
    private String fromUserId;
    private String toUserId;
    private String message;

    public SendMessageModel(String fromUserId, String toUserId, String message) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.message = message;
    }
    public SendMessageModel(){};

    public String getFromUserId() {
        return fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
