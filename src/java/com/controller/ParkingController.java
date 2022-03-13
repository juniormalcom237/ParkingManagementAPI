/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.ModelParking;
import java.sql.*;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author JUNIOR DE LA CAPITAL
 */

@Path("parkingController")
public class ParkingController {
    
    @GET
    @Path("listParking")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/json"})
    
    public ArrayList<ModelParking> getParking(){
        ArrayList<ModelParking> listP = new ArrayList<>();
        try {
            String sql = "SELECT * FROM parking";
            Statement stmt = com.connexion.Connexion.seconnecter().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                ModelParking parking = new ModelParking();
                parking.setParkingID(rs.getInt("parkingID"));
                parking.setParkingName(rs.getString("parkingName"));
                parking.setLongitude(rs.getString("longitude"));
                parking.setLatitude(rs.getString("latitude"));
                parking.setStatus(rs.getInt("status"));
                parking.setCountry(rs.getString("country"));
                parking.setCity(rs.getString("city"));
                parking.setImage(rs.getString("image"));
                parking.setTel(rs.getString("telephone"));
                
                listP.add(parking);
            }
            
        } catch (Exception e) {
            System.out.println("error loading data :" + e.getMessage());
        }
        
        return listP;
        
    }
    
    @POST
    @Path("parking")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/json"})
    public String addParking(ModelParking Parking){
        
        try {
            String sql = "INSERT INTO parking(parkingID, adminID, parkingName,longitude,latitude,status,country,city,telephone,image) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = com.connexion.Connexion.seconnecter().prepareStatement(sql);
            stmt.setInt(1,Parking.getParkingID());
            stmt.setInt(2,Parking.getAdminID());
            stmt.setString(3, Parking.getParkingName());
            stmt.setString(4, Parking.getLongitude());
            stmt.setString(5, Parking.getLatitude());
            stmt.setInt(6, Parking.getStatus());
            stmt.setString(7, Parking.getCountry());
            stmt.setString(8, Parking.getCity());
            stmt.setString(9, Parking.getTel());
            stmt.setString(10, Parking.getImage());
            stmt.execute();
        } catch (Exception e) {
        }
        
        return "added successfully";
    }
    
    
    @PUT
    @Path("parking/{parkingid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/json"})
    
    public String updateParking(@PathParam("parkingid") int parkingid, ModelParking parking) throws SQLException{
       
        String sql2 = "update parking set parkingName =? , longitude=?, latitude=?, status= ? where parkingID = ?";
        PreparedStatement stmt2 = com.connexion.Connexion.seconnecter().prepareStatement(sql2);
        
        stmt2.setString(1, parking.getParkingName());
        stmt2.setString(2, parking.getLongitude());
        stmt2.setString(3, parking.getLatitude());
        stmt2.setInt(4, parking.getStatus());
        stmt2.setInt(5, parkingid);
        stmt2.execute();
        
        return "updated Successfully";
        
    }

    @DELETE
    @Path("parking/{parkingid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/json"})
    public String deleteParking(@PathParam("parkingid") int id){
     
         try {
            String sql = "DELETE from parking where parkingID = ?";
             PreparedStatement pstmt = com.connexion.Connexion.seconnecter().prepareStatement(sql);
             pstmt.setInt(1,id);
             pstmt.execute();
        } catch (Exception e) {
            
             System.out.println("error in querry" + e.getMessage());
        } 
        return "user with '"+ id +"' Deleted successful";
    }
    @GET
    @Path("listeparking/{parkingid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/json"})
    
    public ModelParking getParking(@PathParam("parkingid") int id){
        ModelParking onePark = new ModelParking();
        try {
            String sql = "SELECT * FROM parking where parkingID = '"+ id +"'";
            Statement stmt = com.connexion.Connexion.seconnecter().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            onePark.setParkingID(rs.getInt("parkingID"));
            onePark.setAdminID(rs.getInt("adminID"));
            onePark.setParkingName(rs.getString("parkingName"));
            onePark.setLongitude(rs.getString("longitude"));
            onePark.setLatitude(rs.getString("latitude"));
            onePark.setStatus(rs.getInt("status"));
            onePark.setCountry(rs.getString("country"));
            onePark.setCity(rs.getString("city"));
            onePark.setImage(rs.getString("image"));
            onePark.setTel(rs.getString("telephone"));
        } catch (Exception e) {
        }
        
        return onePark;
    }
    
}
