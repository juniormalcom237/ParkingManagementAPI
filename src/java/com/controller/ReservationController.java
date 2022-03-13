/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Reservation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author JUNIOR DE LA CAPITAL
 */

@Path("reservationController")
public class ReservationController {
    @POST
    @Path("/addReservation")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({"application/json"})
    
    public void InsertReservation(Reservation reservation){
        int id = 0;
           try {
            //Insert into Car
            String sqlStatment1= "Insert into Car(carName, matricule,carType,status)values(?,?,?,?)";
            PreparedStatement pstmt1 = com.connexion.Connexion.seconnecter().prepareStatement(sqlStatment1);
            pstmt1.setString(1,reservation.getCarName());
            pstmt1.setString(2,reservation.getMatricule());
            pstmt1.setString(3, reservation.getCarType());
            pstmt1.setInt(4, 1);
            pstmt1.executeUpdate();   
            
            // Retrieve The CarID
            String sqlStatment2 = "SELECT carId from car where matricule = '"+reservation.getMatricule()+"'";
            Statement pstmt2 = com.connexion.Connexion.seconnecter().createStatement();
            ResultSet rs = pstmt2.executeQuery(sqlStatment2);
   
            while(rs.next()){
                id = rs.getInt("carId");
                System.out.println(id);
            }
            
            // Insert into Reservation.
            String sqlStatment= "Insert into reservation(carId,clientId,slotId,reservationStatus)values(?,?,?,?)";
            PreparedStatement pstmt = com.connexion.Connexion.seconnecter().prepareStatement(sqlStatment);
            pstmt.setInt(1, id);
            pstmt.setInt(2, reservation.getClientID());
            pstmt.setInt(3, reservation.getSlotId());
            pstmt.setInt(4, 1);
           pstmt.execute();
          
        } catch (Exception e) {
            System.out.println("error in querry" + e.getMessage());
        }
       
    }
}
