package com.example.zendesk;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GetTicketControllerTest {

    GetTicketController g = new GetTicketController();
    ArrayList<Object> ticketsList = g.getTickets();

    @Test
    public void getTickets() {
        ArrayList<Object> ticketsList = g.getTickets();
        System.out.println("Total Tickets: " + ticketsList.size());
        System.out.println(ticketsList);
    }

    @Test
    public void getSingleTicketHelper() {
        Object singleTicket1 = g.getSingleTicketHelper("1");
        Assert.assertEquals(singleTicket1.toString(), ticketsList.get(0).toString());

        Object singleTicket2= g.getSingleTicketHelper("2");
        assertNull(singleTicket2);

        Object singleTicket3 = g.getSingleTicketHelper("3");
        Assert.assertEquals(singleTicket3.toString(), ticketsList.get(1).toString());

    }


}