package br.ufrn.bank.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Represents a bank account.
 * 
 * @author viniciuscampos
 */
public class Account {
	
    private Long id;
    private String password;
    private Double balance;
    private ArrayList<Operation> operations;

    public Account(Long id, String password) {
        this.id = id;
        this.password = password;
        this.balance = 0.0;
        this.operations = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Double getBalance() {
        return balance;
    }

    public void updateBalance(Double value) {
        this.balance += value;
    }

    public void updateOperations(Operation operation) {
        operations.add(operation);
    
    }
    
    public ArrayList<String> getStatement() {
        ArrayList<String> stmt = new ArrayList<>();
        for(Operation o : operations) 
            stmt.add(o.toString() + "\n");
        return stmt;
    }
		
}
