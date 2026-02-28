package wood.mike.sbetcd.model;

public record GetResponse(String message, String value) {
    public static GetResponse success(String value) { return new GetResponse("success", value); }
    public static GetResponse failure(String reason) { return new GetResponse("Failed to get value: " + reason, null); }
}
