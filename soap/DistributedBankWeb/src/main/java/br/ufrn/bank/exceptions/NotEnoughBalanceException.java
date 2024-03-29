/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.bank.exceptions;

import java.rmi.RemoteException;

/**
 * Raised when the balance is not enough for an operation.
 * 
 * @author vitorgreati
 */
public class NotEnoughBalanceException extends Exception {
    
    public NotEnoughBalanceException(String msg) {
        super("Balance is too low for this operation in account " + msg);
    }
    
}
