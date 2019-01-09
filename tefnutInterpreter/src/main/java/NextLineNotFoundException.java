public class NextLineNotFoundException extends RuntimeException {
    private String message;

    public NextLineNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
