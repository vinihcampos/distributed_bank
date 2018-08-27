package server;

import exceptions.AccountAlreadyExistsException;
import exceptions.AuthenticationException;
import exceptions.InvalidAccountException;
import exceptions.NotEnoughBalanceException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.IBank;

/**
 * A remote bank and its operations.
 * 
 * @author vitorgreati, viniciuscampos
 */
public class Bank extends UnicastRemoteObject implements IBank, Serializable {
		
    private static final long serialVersionUID = 1L;

    private Map<Long, Account> accounts;

    public Bank() throws RemoteException {
        accounts = new HashMap<>();
    }

    @Override
    public Long createAccount(Long id, String password) throws AccountAlreadyExistsException {
        
        if(accounts.containsKey(id)) 
            throw new AccountAlreadyExistsException(id);
        
        accounts.put(id, new Account(id, password));			
        
        return id;
    }

    @Override
    public void deposit(Double value, Long account) throws IllegalArgumentException, InvalidAccountException {
        
        if (value <= 0)
            throw new IllegalArgumentException("Value to deposit must be non-negative");
        
        if(!accounts.containsKey(account)) 
            throw new InvalidAccountException(account);
        
        accounts.get(account).updateBalance(value);
        accounts.get(account).updateOperations(new Deposit(value));
    }

    @Override
    public void withdraw(Long account, String password, Double value) throws InvalidAccountException, AuthenticationException, NotEnoughBalanceException {
                   
        if(value <= 0)
            throw new IllegalArgumentException("Withdraw values must be non-negative");
        
        if(!accounts.containsKey(account))
            throw new InvalidAccountException(account);
            
        if(!accounts.get(account).getPassword().equals(password)) 
            throw new AuthenticationException();

        if(accounts.get(account).getBalance() < value)
            throw new NotEnoughBalanceException(account);

        accounts.get(account).updateBalance(-value);
        accounts.get(account).updateOperations(new Withdraw(-value));

    }

    @Override
    public void transfer(Long account, String password, Double value, Long anotherAccount) throws InvalidAccountException, AuthenticationException, NotEnoughBalanceException {
        
        withdraw(account, password, value);
        
        deposit(value, anotherAccount);
        
        accounts.get(account).updateOperations(new Transfer(anotherAccount, -value));
        accounts.get(anotherAccount).updateOperations(new Transfer(account, value));
        
    }

    @Override
    public String statement(Long account, String password) throws InvalidAccountException, AuthenticationException {
        
        if (!accounts.containsKey(account))
            throw new InvalidAccountException(account);
        
        if (!accounts.get(account).getPassword().equals(password))
            throw new AuthenticationException();
        
        return accounts.get(account).toString();
    }

    @Override
    public Double getBalance(Long account, String password) throws InvalidAccountException, AuthenticationException {
        
        if (!accounts.containsKey(account))
            throw new InvalidAccountException(account);
        
        if (!accounts.get(account).getPassword().equals(password))
            throw new AuthenticationException();
        
        return accounts.get(account).getBalance();
    }

}
