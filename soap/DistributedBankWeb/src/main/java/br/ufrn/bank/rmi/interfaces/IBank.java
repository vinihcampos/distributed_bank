package br.ufrn.bank.rmi.interfaces;

import br.ufrn.bank.exceptions.AccountAlreadyExistsException;
import br.ufrn.bank.exceptions.AccountAuthenticationException;
import br.ufrn.bank.exceptions.InvalidAccountException;
import br.ufrn.bank.exceptions.NotEnoughBalanceException;
import br.ufrn.bank.model.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Remote interface for the Bank.
 * 
 * @author Vinícius Campos, Vitor Greati
 */
public interface IBank extends Remote {
    
    /**
     * Creates an account, given an account number and a password.
     * 
     * @param id
     * @param password
     * @return the account id
     * @throws br.ufrn.bank.exceptions.AccountAlreadyExistsException
     * @throws RemoteException 
     */
    public Long createAccount(User user, Long id, String password) throws AccountAlreadyExistsException, RemoteException;
    
    /**
     * Deposit an amount in an account.
     * 
     * @param value
     * @param account
     * @param updateOperation
     * @throws RemoteException 
     * @throws br.ufrn.bank.exceptions.InvalidAccountException 
     */
    public void deposit(Double value, Long account, Boolean updateOperation) throws IllegalArgumentException, InvalidAccountException, RemoteException;
    
    /**
     * Withdraw a value from an account.
     * 
     * Performs authentication and subtracts the value from the
     * account if possible.
     * 
     * @param account
     * @param password
     * @param value
     * @param updateOperation
     * @throws br.ufrn.bank.exceptions.InvalidAccountException
     * @throws br.ufrn.bank.exceptions.AccountAuthenticationException
     * @throws RemoteException 
     * @throws br.ufrn.bank.exceptions.NotEnoughBalanceException 
     */
    public void withdraw(Long account, String password, Double value, Boolean updateOperation) throws InvalidAccountException, AccountAuthenticationException, NotEnoughBalanceException, RemoteException;
    
    /**
     * Transfers an amount from an account to another.
     * 
     * @param account
     * @param password
     * @param value
     * @param anotherAccount
     * @throws RemoteException 
     * @throws br.ufrn.bank.exceptions.InvalidAccountException 
     * @throws br.ufrn.bank.exceptions.AccountAuthenticationException 
     * @throws br.ufrn.bank.exceptions.NotEnoughBalanceException 
     */
    public void transfer(Long account, String password, Double value, Long anotherAccount) throws IllegalArgumentException, InvalidAccountException, AccountAuthenticationException, NotEnoughBalanceException, RemoteException;
    
    /**
     * Returns an account statement (operations performed).
     * 
     * @param account
     * @param password
     * @return the account statement
     * @throws br.ufrn.bank.exceptions.InvalidAccountException
     * @throws br.ufrn.bank.exceptions.AccountAuthenticationException
     * @throws RemoteException 
     */
    public List<String> statement(Long account, String password) throws InvalidAccountException, AccountAuthenticationException, RemoteException;
    
    /**
     * Check account balance.
     * 
     * @param account
     * @param password
     * @return the balance
     * @throws br.ufrn.bank.exceptions.InvalidAccountException
     * @throws br.ufrn.bank.exceptions.AccountAuthenticationException
     * @throws java.rmi.RemoteException
     */
    public Double getBalance(Long account, String password) throws InvalidAccountException, AccountAuthenticationException, RemoteException;
}
