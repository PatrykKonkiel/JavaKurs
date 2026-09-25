package zl6;

public class OperationResult<T> {
    private final boolean success;
    private final T value;
    private final String message;

    private OperationResult(boolean success, T value, String message) {
        this.success = success;
        this.value = value;
        this.message = message;
    }

    public T getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public static <T> OperationResult<T> success(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Operacja się nie powiodłą");
        } else {
            return new OperationResult<>(true, value, "Operacja zakończona sukcesem");
        }
    }

}
