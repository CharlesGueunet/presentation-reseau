/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.axellink.rest;

import java.util.ArrayList;
import java.util.HashMap;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * REST Web Service
 *
 * @author axellink
 */
@Path("rest")
public class Rest {

    @Context
    private UriInfo context;
    
    private static HashMap<String, ArrayList<String>> data = null;

    /**
     * Creates a new instance of Rest
     */
    public Rest() {
        if(data==null){
            data = new HashMap<>();
        }
    }

    private void processName(String name) throws WebApplicationException{
        if(!data.containsKey(name)) throw new WebApplicationException(404);
    }
    
    /**
     * Retrieves all the "files"
     * @return list of the "files"
     */
    @GET
    @Produces("text/plain")
    public String listFiles(){
        String res = "";
        for(String s : data.keySet()) res+=s+"\n";
        return res;
    }
    
    /**
     * Retrieve the file "name"
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{name}")
    @Produces("text/plain")
    public String getFile(@PathParam("name") String name) throws WebApplicationException {
        processName(name);
        String res = "";
        for(String s : data.get(name)) res += s + "\n";
        return res;
    }

    /**
     * Create or replace a file
     * @param content representation for the resource
     */
    @PUT
    @Path("{name}")
    public void putFile(@PathParam("name") String name) {
        data.put(name, new ArrayList<String>());
    }
    
    /**
     * Delete a file
     * @param content representation for the resource
     */
    @DELETE
    @Path("{name}")
    public void delFile(@PathParam("name") String name) {
        processName(name);
        data.remove(name);
    }
    
    /*
     * Put text in a file
     */
    @POST
    @Path("{name}")
    @Consumes("text/plain")
    public void addTextToFile(String content, @PathParam("name") String name) {
        processName(name);
        data.get(name).add(content);
    }
    
}
