
package Controller;

import Model.AgeGroup;
import Model.NationalityCount;
import static Model.Nic.getNicData;
import Model.User;
import static Model.User.deleteUserData;
import static Model.User.edit;
import static Model.User.getUserData;
import static Model.User.save;
import Model.UsersCount;
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
        System.out.println("Received Json object:" + jsonObj);

        //we can read json properties by name 
        JsonElement requestId = jsonObj.get("requestId");
        JsonElement requestMethod = jsonObj.get("method");
        System.out.println("Received Request Id:" + requestId);

        //we can get an object inside object and map it to a class as well
        JsonElement user = jsonObj.get("user");

        User u = new Gson().fromJson(user.toString(), User.class);

        save(u);

        //create an json object to send as response
        String responseString = "{'requestId':" + requestId + ",'responseCode':1,'responseMessage':'Successfully saved'}";
        System.out.println("Response String:" + responseString);

        //parse into json 
        JsonElement response = JsonParser.parseString(responseString);

        //create json object
        JsonObject responseObject = response.getAsJsonObject();

        //create json string to send
        String toBeSend = new Gson().toJson(responseObject);
        System.out.println("Response Json:" + toBeSend);

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
        System.out.println("Received Json object:" + jsonObj);

        //we can read json properties by name 
        JsonElement requestId = jsonObj.get("requestId");
        JsonElement requestMethod = jsonObj.get("method");
        System.out.println("Received Request Id:" + requestId);

        //we can get an object inside object and map it to a class as well
        JsonElement user = jsonObj.get("user");

        User u = new Gson().fromJson(user.toString(), User.class);

        edit(u);

        //create an json object to send as response
        String responseString = "{'requestId':" + requestId + ",'responseCode':1,'responseMessage':'Successfully updated'}";
        System.out.println("Response String:" + responseString);

        //parse into json 
        JsonElement response = JsonParser.parseString(responseString);

        //create json object
        JsonObject responseObject = response.getAsJsonObject();

        //create json string to send
        String toBeSend = new Gson().toJson(responseObject);
        System.out.println("Response Json:" + toBeSend);

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
        String responseString = "{'requestId':" + requestId + ",'responseCode':1,'responseMessage':'Successfully retrieved', 'users':" + new Gson().toJson(users) + "}";
        System.out.println("Response String:" + responseString);

        //parse into json 
        JsonElement response = JsonParser.parseString(responseString);

        //create json object
        JsonObject responseObject = response.getAsJsonObject();

        //create json string to send
        String toBeSend = new Gson().toJson(responseObject);

        return toBeSend;

    }
    
    
    // getAgeGroupObj using proper json formate
    @POST
    @Path("json-age-groups")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAgeGroupObjJson(String request) {

        System.out.println("json get age group objects called");
        //parse the json string into a JsonElement object
        JsonElement jsonElement = JsonParser.parseString(request);

        //get the json element as a json object
        JsonObject jsonObj = jsonElement.getAsJsonObject();

        //we can read json properties by name 
        JsonElement requestId = jsonObj.get("requestId");
        JsonElement requestMethod = jsonObj.get("method");

        List<AgeGroup> ageGroupObj = AgeGroup.findAgeGroup();

        //create an json object to send as response
        String responseString = "{'requestId':" + requestId + ",'responseCode':1,'responseMessage':'Age groups successfully retrieved', 'ageGroups':" + new Gson().toJson(ageGroupObj) + "}";
        System.out.println("Response String:" + responseString);

        //parse into json 
        JsonElement response = JsonParser.parseString(responseString);

        //create json object
        JsonObject responseObject = response.getAsJsonObject();

        //create json string to send
        String toBeSend = new Gson().toJson(responseObject);

        return toBeSend;

    }
    

    // genarate nic using proper json formate
    @POST
    @Path("json-nic-genarate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getNicDataJson(String request) {

        //parse the json string into a JsonElement object
        JsonElement jsonElement = JsonParser.parseString(request);

        //get the json element as a json object
        JsonObject jsonObj = jsonElement.getAsJsonObject();

        //we can read json properties by name 
        JsonElement requestId = jsonObj.get("requestId");
        JsonElement requestMethod = jsonObj.get("method");
        JsonElement nic = jsonObj.get("nicNum");

        User user = new User();
        user.setNic(nic.getAsString());

        //create an json object to send as response
        String responseString = "{'requestId':" + requestId + ",'responseCode':1,'responseMessage':'Successfully Generated', 'nicInfo':" + new Gson().toJson(getNicData(user)) + "}";
        System.out.println("Response String:" + responseString);

        //parse into json 
        JsonElement response = JsonParser.parseString(responseString);

        //create json object
        JsonObject responseObject = response.getAsJsonObject();

        //create json string to send
        String toBeSend = new Gson().toJson(responseObject);

        return toBeSend;

    }

    // delete user using proper json formate
    @POST
    @Path("json-delete-user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteUserJson(String request) {

        //parse the json string into a JsonElement object
        JsonElement jsonElement = JsonParser.parseString(request);

        //get the json element as a json object
        JsonObject jsonObj = jsonElement.getAsJsonObject();

        //we can read json properties by name 
        JsonElement requestId = jsonObj.get("requestId");
        JsonElement requestMethod = jsonObj.get("method");
        JsonElement userId = jsonObj.get("id");

        User user = new User();
        user.setId(userId.getAsString());
        deleteUserData(user);

        //create an json object to send as response
        String responseString = "{'requestId':" + requestId + ",'responseCode':1,'responseMessage':'Successfully Deleted'";
        System.out.println("Response String:" + responseString);

        //parse into json 
        JsonElement response = JsonParser.parseString(responseString);

//        create json object
        JsonObject responseObject = response.getAsJsonObject();

//        create json string to send
        String toBeSend = new Gson().toJson(responseObject);

        return "Success";

    }

    @POST
    @Path("json-retrieve-db")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String dbRetrieveJson(String request) {

        //parse the json string into a JsonElement object
        JsonElement jsonElement = JsonParser.parseString(request);

        //get the json element as a json object
        JsonObject jsonObj = jsonElement.getAsJsonObject();

        //we can read json properties by name 
        JsonElement requestId = jsonObj.get("requestId");
        JsonElement requestMethod = jsonObj.get("method");
        JsonElement userId = jsonObj.get("id");

        User user = new User();
        user.setId(userId.getAsString());

        user = getUserData(user);

        //create an json object to send as response
        String responseString = "{'requestId':" + requestId + ",'responseCode':1,'responseMessage':'Successfully retrieved', 'user':" + new Gson().toJson(user) + "}";
        System.out.println("Response String:" + responseString);

        //parse into json 
        JsonElement response = JsonParser.parseString(responseString);

        //create json object
        JsonObject responseObject = response.getAsJsonObject();

        //create json string to send
        String toBeSend = new Gson().toJson(responseObject);

        return toBeSend;
    }

    
    @POST
    @Path("json-nationality-data")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getNationalityData(String request) {

        //parse the json string into a JsonElement object
        JsonElement jsonElement = JsonParser.parseString(request);

        //get the json element as a json object
        JsonObject jsonObj = jsonElement.getAsJsonObject();

        //we can read json properties by name 
        JsonElement requestId = jsonObj.get("requestId");
        JsonElement requestMethod = jsonObj.get("method");  

        NationalityCount nc = new NationalityCount();
        
        //create an json object to send as response
        String responseString = "{'requestId':" + requestId + ",'responseCode':1,'responseMessage':'Successfully retrieved', 'nationalityCounts':" + new Gson().toJson(nc.getNationalityCount()) + "}";
        System.out.println("Response String:" + responseString);

        //parse into json 
        JsonElement response = JsonParser.parseString(responseString);

        //create json object
        JsonObject responseObject = response.getAsJsonObject();

        //create json string to send
        String toBeSend = new Gson().toJson(responseObject);

        return toBeSend;
    }
    
    @POST
    @Path("json-gender-data")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getGenderData(String request) {

        //parse the json string into a JsonElement object
        JsonElement jsonElement = JsonParser.parseString(request);

        //get the json element as a json object
        JsonObject jsonObj = jsonElement.getAsJsonObject();

        //we can read json properties by name 
        JsonElement requestId = jsonObj.get("requestId");
        JsonElement requestMethod = jsonObj.get("method");

        UsersCount uc = new UsersCount();
        
        //create an json object to send as response
        String responseString = "{'requestId':" + requestId + ",'responseCode':1,'responseMessage':'Successfully retrieved', 'genderCounts':" + new Gson().toJson(uc.getUserCount()) + "}";
        System.out.println("Response String:" + responseString);

        //parse into json 
        JsonElement response = JsonParser.parseString(responseString);

        //create json object
        JsonObject responseObject = response.getAsJsonObject();

        //create json string to send
        String toBeSend = new Gson().toJson(responseObject);

        return toBeSend;
    }
    

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

}
