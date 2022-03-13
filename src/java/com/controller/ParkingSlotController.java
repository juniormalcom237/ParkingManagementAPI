/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.ModelParkingSlot;
import java.sql.PreparedStatement;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author JUNIOR DE LA CAPITAL
 */
@Path("parkingSlot")
public class ParkingSlotController {
  
@POST
@Path("addSlot")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({"application/json"})

public void insertParkingSlot(ModelParkingSlot parkingSlot){
      try {
            String sqlStatment= "Insert into parkingslot(slotName,slotPrice)values (?,?)";
            PreparedStatement pstmt = com.connexion.Connexion.seconnecter().prepareStatement(sqlStatment);
            pstmt.setString(1, parkingSlot.getSlotName());
            pstmt.setString(2, parkingSlot.getSlotPrice());

            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("error in querry" + e.getMessage());
        }
}

}
