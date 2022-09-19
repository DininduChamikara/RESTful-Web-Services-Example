/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Model.Nic.getNicData;
import Model.Student;
import Model.User;
import static Model.User.deleteUserData;
import static Model.User.edit;
import static Model.User.getUserData;
import static Model.User.save;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author acer
 */
@Path("user")
@RequestScoped
public class UserController {

    @Context
    private UriInfo context;

    public UserController() {
    }
    
    // save using proper json formate
    @POST
    @Path("json-save-user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String saveUserJson(String request) {
        
        //parse the json string into a JsonElement object
        JsonElement jsonElement = JsonParser.parseString(request);
        
        //get the json element as a json object
        JsonObject jsonObj = jsonElement.getAsJsonObject();
        
        //this object can be contverted back into json string
        System.out.println("Received Json object:"+jsonObj);
        
        //we can read json properties by name 
        JsonElement requestId = jsonObj.get("requestId");
        JsonElement requestMethod = jsonObj.get("method");
        System.out.println("Received Request Id:"+requestId);
        
        //we can get an object inside object and map it to a class as well
        JsonElement user = jsonObj.get("user");
        
        User u = new Gson().fromJson(user.toString(), User.class);
        
        save(u);
        
        //create an json object to send as response
        String responseString = "{'requestId':"+requestId+",'responseCode':1,'responseMessage':'Successfully saved'}";
        System.out.println("Response String:"+responseString);
        
        //parse into json 
        JsonElement response = JsonParser.parseString(responseString);
        
        //create json object
        JsonObject responseObject = response.getAsJsonObject();
        
        //create json string to send
        String toBeSend = new Gson().toJson(responseObject);
        System.out.println("Response Json:"+toBeSend);
        
        return toBeSend;
    }
    
    
    // edit using proper json formate
    @POST
    @Path("json-edit-user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String editUserJson(String request) {
        
        //parse the json string into a JsonElement object
        JsonElement jsonElement = JsonParser.parseString(request);
        
        //get the json element as a json object
        JsonObject jsonObj = jsonElement.getAsJsonObject();
        
        //this object can be contverted back into json string
        System.out.println("Received Json object:"+jsonObj);
        
        //we can read json properties by name 
        JsonElement requestId = jsonObj.get("requestId");
        JsonElement requestMethod = jsonObj.get("method");
        System.out.println("Received Request Id:"+requestId);
        
        //we can get an object inside object and map it to a class as well
        JsonElement user = jsonObj.get("user");
        
        User u = new Gson().fromJson(user.toString(), User.class);
        
        edit(u);
        
        //create an json object to send as response
        String responseString = "{'requestId':"+requestId+",'responseCode':1,'responseMessage':'Successfully updated'}";
        System.out.println("Response String:"+responseString);
        
        //parse into json 
        JsonElement response = JsonParser.parseString(responseString);
        
        //create json object
        JsonObject responseObject = response.getAsJsonObject();
        
        //create json string to send
        String toBeSend = new Gson().toJson(responseObject);
        System.out.println("Response Json:"+toBeSend);
        
        return toBeSend;
    }
    
    // view using proper json formate
    @POST
    @Path("json-all-users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAllUsersJson(String request) {
        
        System.out.println("json all users called");
        //parse the json string into a JsonElement object
        JsonElement jsonElement = JsonParser.parseString(request);
        
        //get the json element as a json object
        JsonObject jsonObj = jsonElement.getAsJsonObject();
        
        //we can read json properties by name 
        JsonElement requestId = jsonObj.get("requestId");
        JsonElement requestMethod = jsonObj.get("method");
        
        List<User> users = User.find();
        
        //create an json object to send as response
        String responseString = "{'requestId':"+requestId+",'responseCode':1,'responseMessage':'Successfully retrieved', 'users':"+ new Gson().toJson(users) +"}";
        System.out.println("Response String:"+responseString);
        
        //parse into json 
        JsonElement response = JsonParser.parseString(responseString);
        
        //create json object
        JsonObject responseObject = response.getAsJsonObject();
        
        //create json string to send
        String toBeSend = new Gson().toJson(responseObject);
        
        return toBeSend;

    }
    
    
//    test json method pass start
    @POST
    @Path("json-test")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAllUserJson(String request) {
        
        System.out.println("get all user json called");
        //parse the json string into a JsonElement object
        JsonElement jsonElement = JsonParser.parseString(request);
        
        //get the json element as a json object
        JsonObject jsonObj = jsonElement.getAsJsonObject();
        
        //this object can be contverted back into json string
        System.out.println("Received Json object:"+jsonObj);
        
        //we can read json properties by name 
        JsonElement requestId = jsonObj.get("requestId");
        System.out.println("Received Request Id:"+requestId);
        
        //we can get an object inside object and map it to a class as well
        JsonElement user = jsonObj.get("user");
        Student student = new Gson().fromJson(user.toString(), Student.class);
        System.out.println("Received Student Name:"+student.getName());
        System.out.println("Received Student Age:"+student.getAge());
        
        //create an json object to send as response
        String responseString = "{'requestId':'54564','responseCode':0,'method':'searchUser','user':" + new Gson().toJson(student) + "}";
        System.out.println("Response String:"+responseString);
        
        //parse into json 
        JsonElement response = JsonParser.parseString(responseString);
        
        //create json object
        JsonObject responseObject = response.getAsJsonObject();
        
        //create json string to send
        String toBeSend = new Gson().toJson(responseObject);
        System.out.println("Response Json:"+toBeSend);
        
        return toBeSend;
    }
    //    test json method pass start
    
    
    
    
    @GET
    @Path("all-users")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
        List<User> users = User.find();
        return new Gson().toJson(users);
    }

    @POST
    @Path("save-user")
    @Produces(MediaType.APPLICATION_JSON)
    public String saveUser(User user) {
        save(user);
        return user.getId();
    }
    
    @POST
    @Path("edit-user")
    @Produces(MediaType.APPLICATION_JSON)
    public String editUser(User user) {
        edit(user);
        return user.getId();
    }
    
    @POST
    @Path("nic-generate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String nicGenarate(User user) {
//        getNicData(user);
        return new Gson().toJson(getNicData(user));
    }
    
    @POST
    @Path("retrieve-db")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String dbRetrieve(User user) {
//        getNicData(user);
        return new Gson().toJson(getUserData(user));
    }
    
    @POST
    @Path("delete-user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUser(User user) {
        System.out.println("Delete user user controller calling");
        deleteUserData(user);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

}
