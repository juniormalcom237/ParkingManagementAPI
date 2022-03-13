/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Client;
import java.sql.*;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author JUNIOR DE LA CAPITAL
 */

@Path("ClientController")
public class ClientController {
    @GET
    @Path("/listClient")
    @Produces({"application/json"})
    public ArrayList<Client> listclient(){
        ArrayList<Client> listclient = new ArrayList<Client>();
        try {
            Statement etat = com.connexion.Connexion.seconnecter().createStatement();
            ResultSet rs = etat.executeQuery("select * from Client");
            while(rs.next()){
                Client oneclient = new Client();
               oneclient.setClientID(rs.getInt("clientID"));
               oneclient.setName(rs.getString("name"));
               oneclient.setEmail(rs.getString("email"));
               oneclient.setTelephone(rs.getString("telephone"));
               oneclient.setCity(rs.getString("city"));
               oneclient.setStatus(rs.getString("status"));
               listclient.add(oneclient);
            }
            etat.close();
            rs.close();
        } catch (Exception e) {
            
        }
        return listclient;
    }
    

    @POST
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/json"})
    
    public Client createUser(Client user){
        int id = 0;
        boolean bool;
        String answer ="";
        try {
            String sqlStatment= "Insert into user (login,mdp)values (?,?)";
            PreparedStatement pstmt = com.connexion.Connexion.seconnecter().prepareStatement(sqlStatment);
            
            
            pstmt.setString(1, user.getLogin());
            
//            
            String login = user.getLogin();
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
            
             String sqlStatment2 = "SELECT * FROM user WHERE login='" + login + "'";
            Statement pstmt2 = com.connexion.Connexion.seconnecter().createStatement();
            ResultSet rs = pstmt2.executeQuery(sqlStatment2);
   
            while(rs.next()){
                id = rs.getInt("userID");
                System.out.println(id);
            }
            String sqlStatment1= "INSERT INTO client (userID,name,email,telephone,city,status) VALUES (?,?,?,?,?,?)";
            PreparedStatement pstmt1 = com.connexion.Connexion.seconnecter().prepareStatement(sqlStatment1);
             pstmt1.setInt(1, id);
            pstmt1.setString(2,user.getName());
            System.out.println(user.getName());
            pstmt1.setString(3, user.getEmail());
            System.out.println(user.getEmail());
            pstmt1.setString(4, user.getTelephone());
            System.out.println(user.getTelephone());
            pstmt1.setString(5, user.getCity());
            System.out.println(user.getCity());
            pstmt1.setInt(6, 1);
            
            bool = pstmt1.execute();
          
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
        return user;
    }  
    
    @PUT
    @Path("/updateUser/{userid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/json"})
    public Client updateUser(@PathParam("userid") int userid, Client user) throws SQLException{
        System.out.println(userid);
        System.out.println("ok ok ok");
        String sql2 = "update user set login=?,mdp=? where userID = ?";
        PreparedStatement stmt2 = com.connexion.Connexion.seconnecter().prepareStatement(sql2);
        stmt2.setString(1,user.getLogin());
        stmt2.setString(2,user.getPassword());
        stmt2.setInt(3,userid);
        stmt2.execute();
        String sql = "update client set name=?,email=?,telephone=?,city=?,status=? where userID = ?";
        PreparedStatement stmt = com.connexion.Connexion.seconnecter().prepareStatement(sql);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getTelephone());
        stmt.setString(4, user.getCity());
        stmt.setString(5, user.getStatus());
        stmt.setInt(6,userid);
        stmt.execute();
        System.out.println(userid);
        return user;
    }
    
    @DELETE
    @Path("/user/{userid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/json"})
    
    public String Deleteuser(@PathParam("userid") int userid){
        
        try {
            String sql = "DELETE from client where clientID = ?";
             PreparedStatement pstmt = com.connexion.Connexion.seconnecter().prepareStatement(sql);
             pstmt.setInt(1,userid);
             pstmt.execute();
        } catch (Exception e) {
            
             System.out.println("error in querry" + e.getMessage());
        } 
        return "user Deleted Sucessfully";
    }
    
    @POST
    @Path("user/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/json"})
    
    public Client login(Client user) throws SQLException{
        
        boolean rest;
        String answer = "";
        ResultSet rs = null;
        ResultSet rs2 = null;
         String sqlStatment= "Select * from user where login = '"+ user.getLogin()+"' AND mdp = '"+user.getPassword()+"'";
            Statement pstmt = com.connexion.Connexion.seconnecter().createStatement();
            rest = pstmt.execute(sqlStatment);
            rs = pstmt.getResultSet();
            System.out.println(rest);
            
            if(rs.next()){
                user.setStatus("success");
                user.setClientID(rs.getInt("userID"));
                
            }else{
                user.setStatus("failure");
            }
        return user;
    }
    
}
