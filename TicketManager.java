package com.Brendon;


import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class TicketManager {

    public static void main(String[] args) {

        LinkedList<Ticket> ticketQueue = new LinkedList<Ticket>();
        LinkedList<Ticket> resolvedTicket = new LinkedList<Ticket>();

        Scanner scan = new Scanner(System.in);

        while(true){

            System.out.println("1. Enter Ticket\n2. Delete by ID/Issue\n3. Delete record\n4. Search by name\n" +
                    "5. Display All Tickets\n6. Quit");
            int task = Integer.parseInt(scan.nextLine());

            if (task == 1) {
                //Call addTickets, which will let us enter any number of new tickets
                addTickets(ticketQueue);

            } else if (task == 2) {
                //delete a ticket
                deleteByID(ticketQueue);

            } else if (task == 3) {
                //delete a ticket
                deleteTicket(ticketQueue,resolvedTicket);

            }else if (task == 4) {

                searchByName(ticketQueue);

            } else if ( task == 6 ) {
                //Quit. Future prototype may want to save all tickets to a file
                System.out.println("Quitting program");
                break;
            }
            else {
                //this will happen for 3 or any other selection that is a valid int
                //Default will be print all tickets
                printAllTickets(ticketQueue);
            }
        }

        scan.close();

    }

    protected static void addTickets(LinkedList<Ticket> ticketQueue) {
        Scanner sc = new Scanner(System.in);
        boolean moreProblems = true;
        String description, reporter;
        Date dateReported = new Date(); //Default constructor creates date with current date/time
        int priority;

        while (moreProblems){
            System.out.println("Enter problem");
            description = sc.nextLine();
            System.out.println("Who reported this issue?");
            reporter = sc.nextLine();
            System.out.println("Enter priority of " + description);
            priority = Integer.parseInt(sc.nextLine());

            Ticket t = new Ticket(description, priority, reporter, dateReported,"N/A", dateReported);
            //ticketQueue.add(t);
            addTicketInPriorityOrder(ticketQueue, t);

            printAllTickets(ticketQueue);

            System.out.println("More tickets to add?");
            String more = sc.nextLine();
            if (more.equalsIgnoreCase("N")) {
                moreProblems = false;
            }
        }
    }

    protected static void addTicketInPriorityOrder(LinkedList<Ticket> tickets, Ticket newTicket){

        //Logic: assume the list is either empty or sorted

        if (tickets.size() == 0 ) {//Special case - if list is empty, add ticket and return
            tickets.add(newTicket);
            return;
        }

        //Tickets with the HIGHEST priority number go at the front of the list. (e.g. 5=server on fire)
        //Tickets with the LOWEST value of their priority number (so the lowest priority) go at the end

        int newTicketPriority = newTicket.getPriority();

        for (int x = 0; x < tickets.size() ; x++) {    //use a regular for loop so we know which element we are looking at

            //if newTicket is higher or equal priority than the this element, add it in front of this one, and return
            if (newTicketPriority >= tickets.get(x).getPriority()) {
                tickets.add(x, newTicket);
                return;
            }
        }

        //Will only get here if the ticket is not added in the loop
        //If that happens, it must be lower priority than all other tickets. So, add to the end.
        tickets.addLast(newTicket);
    }


    protected static void printAllTickets(LinkedList<Ticket> tickets) {
        System.out.println(" ------- All open tickets ----------");

        for (Ticket t : tickets ) {
            System.out.println(t); //Write a toString method in Ticket class
            //println will try to call toString on its argument
        }
        System.out.println(" ------- End of ticket list ----------");

    }

    protected static void deleteTicket(LinkedList<Ticket> ticketQueue, LinkedList resolvedTicket) {
        printAllTickets(ticketQueue);   //display list for user

        if (ticketQueue.size() == 0) {    //no tickets!
            System.out.println("No tickets to delete!\n");
            return;
        }

        //Loop over all tickets. Delete the one with this ticket ID
        boolean found = false;

        while (found == false) {

            Scanner deleteScanner = new Scanner(System.in);
            System.out.println("Enter ID of ticket to delete");
            int deleteID = deleteScanner.nextInt();
            

            for (Ticket ticket : ticketQueue) {

                if (ticket.getTicketID() == deleteID) {
                    found = true;
                    ticketQueue.remove(ticket);
                    System.out.println("Why are you deleting this record?");
                    String reason = deleteScanner.nextLine();


                    System.out.println(String.format("Ticket %d deleted", deleteID));
                    break; //don't need loop any more.
                } else {

                    System.out.println("That is an incorrect ID, please try again.");
                    continue;
                }
            }

            printAllTickets(ticketQueue);  //print updated list

        }
    }

    protected static void searchByName(LinkedList<Ticket> tickets) {

        System.out.println("Please enter a description keyword: ");
        Scanner scanner = new Scanner(System.in);
        String entry = scanner.nextLine();
        LinkedList entryList = new LinkedList();

        System.out.println("------Tickets with keyword " + entry + "---------");
        for (Ticket t : tickets) {

            String desc = t.getDescription();

            if (entry.equalsIgnoreCase(desc)) {

                entryList.add(t);
                System.out.println(t);

            }

        }

        System.out.println("---------Search Closed---------");
        System.out.println("Do you want to delete a record?");
        String delete = scanner.nextLine();

        if (delete.equalsIgnoreCase("Y")) {

            Scanner id = new Scanner(System.in);
            System.out.println("Enter the ID of the ticket you wish to delete.");
            int deleteRecord = id.nextInt();

            for (Ticket ticket : tickets) {

                if (ticket.getTicketID() == deleteRecord) {
                    tickets.remove(ticket);
                }

            }

        }

    }

    protected static void deleteByID(LinkedList<Ticket> ticketQueue) {

        System.out.println("Please enter a description keyword: ");
        Scanner scanner = new Scanner(System.in);
        String entry1 = scanner.nextLine();
        LinkedList entryList = new LinkedList();

        System.out.println("------Tickets with keyword " + entry1 + "---------");
        for (Ticket t : ticketQueue) {

            String desc = t.getDescription();

            if (entry1.equalsIgnoreCase(desc)) {

                entryList.add(t);
                System.out.println(t);

            }

        }

        System.out.println("---------Search Closed---------");
        System.out.println("Do you want to delete a record?");
        String delete = scanner.nextLine();

        if (delete.equalsIgnoreCase("Y")) {


                Scanner id = new Scanner(System.in);
                System.out.println("Enter the ID of the ticket you wish to delete.");
                int deleteRecord = id.nextInt();

                for (Ticket ticket : ticketQueue) {

                    if (ticket.getTicketID() == deleteRecord) {
                        ticketQueue.remove(ticket);

                    }

                }

            printAllTickets(entryList);

            }
        }

    }

