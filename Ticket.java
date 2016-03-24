package com.Brendon;

import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;


public class Ticket {

    private int priority;
    private String reporter; //Stores person or department who reported issue
    private String description;
    private Date dateReported;
    private String resolution;
    private Date resoultionDate;

    public String getDescription() {
        return description;
    }

    //STATIC Counter - accessible to all Ticket objects.
    //If any Ticket object modifies this counter, all Ticket objects will have the modified value
    //Make it private - only Ticket objects should have access
    private static int staticTicketIDCounter = 1;
    //The ID for each ticket - instance variable. Each Ticket will have it's own ticketID variable
    protected int ticketID;

    public int getTicketID() {
        return ticketID;
    }

    public Ticket(String desc, int p, String rep, Date date, String resolution, Date resDate) {
        this.description = desc;
        this.priority = p;
        this.reporter = rep;
        this.dateReported = date;
        this.resolution = resolution;
        this.resoultionDate = resDate;
        this.ticketID = staticTicketIDCounter;
        staticTicketIDCounter++;
    }



    protected int getPriority() {
        return priority;
    }

    public String toString(){
        return("ID= " + this.ticketID + " Issued: " + this.description + " Priority: " + this.priority + " Reported by: "
                + this.reporter + " Reported on: " + this.dateReported);
    }

}

