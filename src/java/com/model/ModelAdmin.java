/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author JUNIOR DE LA CAPITAL
 */
public class ModelAdmin {
    private ModelUser userID;
    private int adminID;

    public ModelUser getUserID() {
        return userID;
    }

    public void setUserID(ModelUser userID) {
        this.userID = userID;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }
    
    
}
