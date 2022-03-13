/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Client;
import com.model.ModelUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author JUNIOR DE LA CAPITAL
 */
@Path("userController")

public class UserController {
    @GET
    @Path("/listeuser")
    @Produces({"application/json"})
    public ArrayList<ModelUser> listuser(){
        ArrayList<ModelUser>listeu = new ArrayList<ModelUser>();
        try {
            ResultSet rs;
            try (Statement etat = com.connexion.Connexion.seconnecter().createStatement()) {
                rs = etat.executeQuery("select * from user");
                while(rs.next()){
                    System.out.println("ok");
                    ModelUser oneuser = new ModelUser();
                    oneuser.setUserId(rs.getInt("userID"));
                    oneuser.setPassword(rs.getString("mdp"));
                    oneuser.setLogin(rs.getString("login"));
                    
                    listeu.add(oneuser);
                }
            }
            rs.close();
            
        } catch (Exception e) {
        }
        System.out.println(listeu);
        return listeu;
    }
    

//    @GET
//    @Path("/user/{userid}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces({"application/json"})
//    
//    public void createUser(@PathParam("userid") int userid){
//        try {
//            String sqlStatment= "SELECT * from user where userID ='"+userid+"'";
//            PreparedStatement pstmt = com.connexion.Connexion.seconnecter().prepareStatement(sqlStatment);
//            pstmt.setInt(1,userid);
//            pstmt.executeQuery();
//        } catch (Exception e) {
//        }
//    }
}
