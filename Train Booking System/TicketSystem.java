import java.util.*;

public class TicketSystem {

    private final List<String> availableBerths = new ArrayList<>(Arrays.asList("L","U","M"));
    private final List<Passenger> confirmedTickets = new ArrayList<>();
    private final Queue<Passenger> RACQueue = new LinkedList<>();
    private final Queue<Passenger> WaitingList = new LinkedList<>();
    int totalTickets = 0;

    public void bookTicket(String name, int age, String gender, String berthPreference){
        Passenger passenger;
        String ticketId = "T" + totalTickets++;
        if(!availableBerths.isEmpty()){
            String allocatedBerth = allocateBerth(age, gender, berthPreference);
            passenger = new Passenger(name, age, gender,berthPreference, allocatedBerth, ticketId);
            confirmedTickets.add(passenger);
            availableBerths.remove(allocatedBerth);
            System.out.print("Ticket Confirmed: "+passenger);
        }else if(RACQueue.size() < 1) {
            passenger = new Passenger(name, age, gender,berthPreference, "RAC", ticketId);
            RACQueue.offer(passenger);
            System.out.println("Ticket in RAC berth "+passenger);
        } else if(WaitingList.size() < 1) {
            passenger = new Passenger(name, age, gender,berthPreference, "WL", ticketId);
            WaitingList.offer(passenger);
            System.out.println("Ticket Added to Waiting List "+passenger);
        }else{
            System.out.println("No Tickets are available");
        }
    }

    public String allocateBerth(int age,String gender, String berthPreference){
        if(age > 60 || gender.equalsIgnoreCase("female") && availableBerths.contains("L")){
            return "L";
        } else if(availableBerths.contains(berthPreference)){
            return berthPreference;
        } else{
            return availableBerths.get(0);
        }
    }

    public void cancelTicket(String ticketId){
        Optional<Passenger> passengerOpt = confirmedTickets.stream().filter(p -> p.ticketId.equals(ticketId)).findFirst();
        if(passengerOpt.isPresent()){
           Passenger passenger = passengerOpt.get();
            confirmedTickets.remove(passenger);
            availableBerths.add(passenger.allottedBerth);

            if(!RACQueue.isEmpty()){
                Passenger racpassenger = RACQueue.poll();
                String allottedBerth = allocateBerth(racpassenger.age, racpassenger.gender, racpassenger.berthPreference);
                racpassenger.allottedBerth = allottedBerth;
                confirmedTickets.add(racpassenger);
                availableBerths.remove(allottedBerth);
                System.out.println("RAC Ticket moved to Confirmed Tickets "+ racpassenger);
            }

            if(!WaitingList.isEmpty()){
                Passenger waitingListPassenger = WaitingList.poll();
                RACQueue.offer(waitingListPassenger);
                System.out.println("Waiting List ticket moved to RAC Queue");
            }
            System.out.println("Ticket Cancelled Successfully");
        } else{
            System.out.println("Ticket ID not found");
        }
    }

    public void printBookedTickets(){
       if(confirmedTickets.isEmpty()){
           System.out.println("No Tickets are booked!");
       }else{
           for(Passenger passenger : confirmedTickets){
               System.out.println(passenger);
           }
       }
    }

    public void printAvailableTickets(){
        System.out.println("Available berths "+ availableBerths.size());
        System.out.println("Available RAC Tickets "+ (1-RACQueue.size()));
        System.out.println("Available Waiting List Tickets "+ (1 - WaitingList.size()));
    }

    public void viewRACTickets(){
        if(RACQueue.isEmpty()){
            System.out.println("No RAC Tickets are available!");
        } else{
            for(Passenger passenger : RACQueue){
                System.out.println(passenger);
            }
        }
    }

    public void viewWaitingListTickets(){
        if(WaitingList.isEmpty()){
            System.out.println("No Waiting List Tickets are available!");
        }else{
            for(Passenger passenger : WaitingList){
                System.out.println(passenger);
            }
        }
    }

}
