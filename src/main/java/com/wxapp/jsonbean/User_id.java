/**
  * Copyright 2019 bejson.com 
  */
package com.wxapp.jsonbean;

import java.io.Serializable;

/**
 * Auto-generated: 2019-12-31 15:56:46
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class User_id implements Serializable {

    private Account account;
    private Operation operation;
    private FriendCounter friendCounter;
    private Setup setup;

    public User_id( ) {
    }

    public User_id(Account account, Operation operation, FriendCounter friendCounter, Setup setup) {
        this.account = account;
        this.operation = operation;
        this.friendCounter = friendCounter;
        this.setup = setup;
    }

    public void setAccount(Account account) {
         this.account = account;
     }
     public Account getAccount() {
         return account;
     }

    public void setOperation(Operation operation) {
         this.operation = operation;
     }
     public Operation getOperation() {
         return operation;
     }

    public void setFriendCounter(FriendCounter friendCounter) {
         this.friendCounter = friendCounter;
     }
     public FriendCounter getFriendCounter() {
         return friendCounter;
     }

    public void setSetup(Setup setup) {
         this.setup = setup;
     }
     public Setup getSetup() {
         return setup;
     }

}