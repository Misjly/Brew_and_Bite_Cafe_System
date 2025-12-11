package com.example.model;
import java.util.Objects;

import com.example.enums.Role;


public class UserAccount {
    private final String username;
    private final String passwordHash;
    private final Role role;


    public UserAccount(String username, String passwordHash, Role role) {
        if(username ==null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank");
        }
        if(passwordHash ==null || passwordHash.isBlank()) {
            throw new IllegalArgumentException("Password hash cannot be null or blank");
        }
        if(role == null){ 
            throw new IllegalArgumentException("Role cannot be null");
        }

        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;        
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString(){
        return "UserAccount {" + "username ='" + username + '\'' +" role=" + role +'}';
    }


    @Override
    public boolean equals (Object object) {
        if(this == object) return true;
        if(!(object instanceof UserAccount)) return false;
        UserAccount that = (UserAccount) object;
        return Objects.equals(username, that.username) &&
               Objects.equals(passwordHash, that.passwordHash) &&
               role == that.role;
    }


    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

}