package com.teste.JWT.others;

public enum UserRole {
    admin("admin"),
    staff("staff"),
    basic("basic");

    private String userRole;

    UserRole(String userRole){
        this.userRole = userRole;
    }

    public String getUserRole(){
        return this.userRole;
    }
}
