/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import java.rmi.RemoteException;

/**
 * Raised when the account doesn't exist.
 * 
 * @author vitorgreati
 */
public class InvalidAccountException extends Exception {
    
    public InvalidAccountException(Long account){
        super("Invalid account: " + account);
    }
    
}
