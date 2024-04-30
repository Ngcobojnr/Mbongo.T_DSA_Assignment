import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StarNetwork network = new StarNetwork(3);

        boolean running = true;
        while (running) {
            System.out.println("\nStar Network Menu:");
            System.out.println("1. Add Client");
            System.out.println("2. Delete Client");
            System.out.println("3. Select Client");
            System.out.println("4. Exit");

            int userChoice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (userChoice) {
                case 1:
                    network.addClient(input);
                    break;
                case 2:
                    network.deleteClient(input);
                    break;
                case 3:
                    ClientNode selectedClient = StarNetwork.chooseClient(network, input);
                    if (selectedClient != null) {
                        StarNetwork.interactWithClient(selectedClient, input);
                    }
                    break;
                case 4:
                    System.out.println("Goodbye from Star Network!");
                    running = false; // Exit the loop
                    break;
                default:
                    System.out.println("Invalid choice, please choose a valid option.");
            }
        }
        input.close(); // Close scanner
    }
}
