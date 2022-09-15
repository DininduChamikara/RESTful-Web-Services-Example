/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Model.Nic.getNicData;
import Model.User;
import static Model.User.deleteUserData;
import static Model.User.edit;
import static Model.User.getUserData;
import static Model.User.save;
import com.google.gson.Gson;
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

    /**
     * Creates a new instance of GenericResource
     */
    public UserController() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.nic_validation_rest.UserController
     * @return an instance of java.lang.String
     */
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
