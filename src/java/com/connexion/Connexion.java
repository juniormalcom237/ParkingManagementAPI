/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
/*
try and catch will help the 
application not to bug in case of
any bug but will be handle by an
exception
*/
/**
 *
 * @author JUNIOR DE LA CAPITAL
 */
public class Connexion {
    private static Connection connex = null;
    private String url="jdbc:mysql://localhost:3306/parkingmanagement";
    private String user= "root";
    private String pwd = "";
    private Connexion(){
        try { //
            Class.forName("com.mysql.jdbc.Driver"); // permrt de demarrer le pilot
        } catch (Exception e) {
            System.out.println("error of Driver:"+e.getMessage());
        }
        try {
            connex = DriverManager.getConnection(url,user,pwd);
        } catch (Exception e) {
            System.out.println("error ofconnexion:"+e.getMessage());
        }
    }
    public static Connection seconnecter(){
        if(connex == null){
            Connexion connexion = new Connexion();  
        }
         return connex;
    }
}


