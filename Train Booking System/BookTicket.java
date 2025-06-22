import java.util.Scanner;

public class BookTicket {


    public static void main(String[] args){
        TicketSystem ticketSystem = new TicketSystem();

        System.out.println("1. Book a Ticket");
        System.out.println("2. Cancel a Ticket");
        System.out.println("3. View Available Tickets");
        System.out.println("4. View Booked Tickets");
        System.out.println("5. View RAC Tickets");
        System.out.println("6. View Waiting List Tickets");
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a Choice: ");
        int choice = in.nextInt();
        switch (choice){
            case 1:
            {
                System.out.println("Enter name: ");
                String name = in.next();
                System.out.println("Enter age: ");
                int age = in.nextInt();
                System.out.println("Enter gender: ");
                String gender = in.next();
                System.out.println("Enter Berth Preference: ");
                String berthPreference = in.next();
                ticketSystem.bookTicket(name, age, gender, berthPreference);
                break;
            }
            case 2:
            {
                System.out.println("Enter ticket id: ");
                String ticketId = in.next();
                ticketSystem.cancelTicket(ticketId);
                break;
            }
            case 3:
            {
                ticketSystem.printAvailableTickets();
                break;
            }
            case 4:
            {
                ticketSystem.printBookedTickets();
                break;
            }
            case 5:
            {
                ticketSystem.viewRACTickets();
                break;
            }
            case 6:
            {
                ticketSystem.viewWaitingListTickets();
                break;
            }
            default:
            {
                System.out.println("Enter a valid choice");
            }
        }

    }

}
