package ${package}.util;

public abstract class MessagedProgressListener implements ProgressListener {
    private final String message;


    public MessagedProgressListener(String message) {
        this.message = message;
    }


    public void progressChanged(double progress) {
        progressChanged(message,progress);
    }
}
