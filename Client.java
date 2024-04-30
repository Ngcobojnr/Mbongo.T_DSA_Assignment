public class Client implements ClientNode {
    private final String clientId;
    private final Server server;

    public Client(String clientId, Server server) {
        this.clientId = clientId;
        this.server = server;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public void sendMessage(String message) {
        server.receiveMessage(message, this);
    }

    @Override
    public void receive(String message, String sender) {
        System.out.println("Client " + clientId + " received message: " + message + " from " + sender);
    }
}
