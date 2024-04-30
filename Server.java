public class Server {
    private final ClientNode[] connectedClients;
    private int clientCount;

    public Server(int maxClients) {
        connectedClients = new ClientNode[maxClients];
        clientCount = 0;
    }

    public void addClient(ClientNode client) {
        if (clientCount < connectedClients.length) {
            connectedClients[clientCount++] = client;
            System.out.println("Client " + client.getClientId() + " added to the server.");
        } else {
            System.out.println("Server capacity reached.");
        }
    }

    public void deleteClient(String clientId) {
        int targetIndex = -1;
        for (int i = 0; i < clientCount; i++) {
            if (connectedClients[i].getClientId().equals(clientId)) {
                targetIndex = i;
                break;
            }
        }

        if (targetIndex >= 0) {
            System.out.println("Client " + connectedClients[targetIndex].getClientId() + " removed.");
            for (int j = targetIndex; j < clientCount - 1; j++) {
                connectedClients[j] = connectedClients[j + 1];
            }
            connectedClients[clientCount - 1] = null; // Clear last slot
            clientCount--;
        } else {
            System.out.println("Client with ID " + clientId + " not found.");
        }
    }

    public void receiveMessage(String message, ClientNode sender) {
        System.out.println("Server received message: " + message + " from " + sender.getClientId());
        for (int i = 0; i < clientCount; i++) {
            if (!connectedClients[i].getClientId().equals(sender.getClientId())) {
                connectedClients[i].receive(message, sender.getClientId());
            }
        }
    }

    public ClientNode[] getConnectedClients() {
        return connectedClients;
    }

    public int getNumClients() {
        return clientCount;
    }
}
