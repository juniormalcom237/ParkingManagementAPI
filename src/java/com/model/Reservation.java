/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.Date;

/**
 *
 * @author JUNIOR DE LA CAPITAL
 */
public class Reservation{
    private int reservationID;
    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
    private int clientID;
    private int slotId;
    private Date reservationDate;
    private int reservationStatus;
    private Date reservationDueDate;

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }
    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(int reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Date getReservationDueDate() {
        return reservationDueDate;
    }

    public void setReservationDueDate(Date reservationDueDate) {
        this.reservationDueDate = reservationDueDate;
    }
    
}
