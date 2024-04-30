public interface ClientNode {
    String getClientId();
    void sendMessage(String message);
    void receive(String message, String sender);
}
