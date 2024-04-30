import java.util.Scanner;

public class StarNetwork {
    private final Server server;

    public StarNetwork(int maxClients) {
        this.server = new Server(maxClients); // Initialize server
    }

    public void addClient(Scanner input) {
        System.out.print("Enter client ID: ");
        String clientId = input.nextLine();
        Client client = new Client(clientId, server);
        server.addClient(client);
    }

    public void deleteClient(Scanner scanner) {
        System.out.print("Enter client ID to delete: ");
        String clientId = scanner.nextLine();
        server.deleteClient(clientId);
    }

    public ClientNode[] getConnectedClients() {
        return server.getConnectedClients();
    }

    public int getNumClients() {
        return server.getNumClients();
    }

    public ClientNode getClient(String clientId) {
        ClientNode[] clients = server.getConnectedClients();
        int numClients = server.getNumClients(); // Get the number of connected clients
        for (int i = 0; i < numClients; i++) { // Loop through the clients
            if (clients[i].getClientId().equals(clientId)) { // Find the client by ID
                return clients[i]; // Return the found client
            }
        }
        return null; // If not found, return null
    }

    public static ClientNode chooseClient(StarNetwork network, Scanner input) {
        ClientNode[] clients = network.getConnectedClients();
        System.out.println("Select a Client:");
        int numClients = network.getNumClients(); // Get the number of clients

        // Display the list of clients
        for (int i = 0; i < numClients; i++) {
            System.out.println((i + 1) + ". " + clients[i].getClientId());
        }

        // Prompt the user to select a client or cancel
        System.out.print("Enter client number (or 'cancel'): ");
        String choice = input.nextLine();

        // Handle cancellation
        if (choice.equalsIgnoreCase("cancel")) {
            return null; // If the user cancels, return null
        }

        try {
            // Convert choice to an integer and adjust for zero-based index
            int clientIndex = Integer.parseInt(choice) - 1;

            // Ensure the index is within valid bounds
            if (clientIndex >= 0 && clientIndex < numClients) {
                return clients[clientIndex]; // Return the selected client
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number."); // Handle invalid number input
        }

        // Default case if the input was invalid or out of bounds
        System.out.println("Invalid selection. Please try again.");
        return null; // Return null if the selection was invalid
    }


    public static void interactWithClient(ClientNode client, Scanner input) {
        boolean interacting = true;
        while (interacting) {
            System.out.println("\nClient " + client.getClientId() + " Menu:");
            System.out.println("1. Send Message");
            System.out.println("2. Exit Interaction");

            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Type your message: ");
                    String message = input.nextLine();
                    client.sendMessage(message);
                    break;
                case 2:
                    interacting = false; // Stop interacting
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}