/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.bank.exceptions;

/**
 * Thrown when a user already exists and an
 * attempt to create it is made.
 * 
 * @author vitorgreati
 */
public class MissingAuthenticationException extends Exception {
    
    public MissingAuthenticationException(String stub) {
        super("Missing authentication");
    }
    
}
