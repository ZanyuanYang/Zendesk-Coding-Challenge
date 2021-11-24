package com.example.zendesk;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;

/**
 * @author Zanyuan Yang
 */
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
        map.put("authenticate", true);
        if(ticketsList == null){
            map.put("authenticate", false);
        }
        map.put("tickets", ticketsList);

        return "/ticket/ticket";
    }

    // http://localhost:8080/singleTickets?ticketId=??
    @GetMapping("/singleTickets")
    public String getSingleTickets(@RequestParam("ticketId") int ticketId, ModelMap map) {

        ArrayList<Object> ticketsList = getTickets();
        map.put("authenticate", true);
        if(ticketsList == null){
            map.put("authenticate", false);
        }else{
            Object singleTicket = getSingleTicketHelper(ticketId+"");
            map.put("ticketsExistOrNot", true);
            if(Objects.isNull(singleTicket)){
                map.put("ticketsExistOrNot", false);
            }
            map.put("singleTickets", singleTicket);
        }

        return "/ticket/singleTicket";
    }


    /**
     * @return return all the ticket
     */
    public ArrayList<Object> getTickets(){
        String json = "";
        try {
            // request url
            String url = GlobalConst.URL;

            // create auth credentials
            String authStr = GlobalConst.AUTH;
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);

            // create request
            HttpEntity request = new HttpEntity(headers);

            try{
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
                        listdata.add(jsonArray.get(i));
                    }
                }
                return listdata;
            }catch(HttpClientErrorException e){
                System.out.println(e);
                return null;
            }
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
            String url = GlobalConst.URL;

            // create auth credentials
            String authStr = GlobalConst.AUTH;
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
