package com.example.zendesk;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;

@Controller
public class GetTicketController {

    // http://localhost:8080
    @GetMapping("/")
    public String getAllTickets() {
        return "/ticket/index";
    }

    // http://localhost:8080/tickets
    @GetMapping("/tickets")
    public String getAllTickets(ModelMap map) {

        ArrayList<Object> ticketsList = getTickets();
        map.put("tickets", ticketsList);

        return "/ticket/ticket";
    }

    // http://localhost:8080/singleTickets?ticketId=??
    @GetMapping("/singleTickets")
    public String getSingleTickets(@RequestParam("ticketId") int ticketId, ModelMap map) {

        Object singleTicket = getSingleTicketHelper(ticketId+"");
        map.put("ticketsExistOrNot", true);
        if(Objects.isNull(singleTicket)){
            map.put("ticketsExistOrNot", false);
        }
        map.put("singleTickets", singleTicket);

        return "/ticket/singleTicket";
    }


    /**
     * @return return all the ticket
     */
    public ArrayList<Object> getTickets(){
        String json = "";
        try {
            // request url
            String url = "https://zcczanyuanyang.zendesk.com/api/v2/tickets.json";

            // create auth credentials
            String authStr = "jayingyoung@gmail.com:Jaying1996123";
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);

            // create request
            HttpEntity request = new HttpEntity(headers);

            // make a request
            ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.GET, request, String.class);

            // get JSON response
            json = response.getBody();

            JSONObject jsnobject = new JSONObject(json);

            //Getting tickets JSON array from the JSON object
            JSONArray jsonArray = jsnobject.getJSONArray("tickets");

            //Creating an empty ArrayList of type Object
            ArrayList<Object> listdata = new ArrayList<Object>();

            if (jsonArray != null) {
                //Iterating JSON array
                for (int i=0;i<jsonArray.length();i++){
                    //Adding each element of JSON array into ArrayList
                    JSONObject row = jsonArray.getJSONObject(i);
                    listdata.add(jsonArray.get(i));
                }
            }
            return listdata;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @param ticket_id this is the ticket id
     * @return return the specific tickets according to ticket id
     */
    public Object getSingleTicketHelper(String ticket_id){
        String json = "";
        Object listdata = null;
        try {
            // request url
            String url = "https://zcczanyuanyang.zendesk.com/api/v2/tickets.json";

            // create auth credentials
            String authStr = "jayingyoung@gmail.com:Jaying1996123";
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);

            // create request
            HttpEntity request = new HttpEntity(headers);

            // make a request
            ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.GET, request, String.class);

            // get JSON response
            json = response.getBody();

            JSONObject jsnobject = new JSONObject(json);

            //Getting tickets JSON array from the JSON object
            JSONArray jsonArray = jsnobject.getJSONArray("tickets");

            //Creating an empty ArrayList of type Object
//            ArrayList<Object> listdata = new ArrayList<Object>();
            if (jsonArray != null) {
                //Iterating JSON array
                for (int i=0;i<jsonArray.length();i++){
                    //Adding each element of JSON array into ArrayList
                    JSONObject row = jsonArray.getJSONObject(i);
                    if(row.getString("id").equals(ticket_id)){
                        listdata = jsonArray.get(i);
                    }
                }
            }
            return listdata;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        listdata = null;
        return listdata;
    }
}
