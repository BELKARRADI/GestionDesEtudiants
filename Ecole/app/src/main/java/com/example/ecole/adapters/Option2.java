package com.example.ecole.adapters;

public class Option2 {

    private int id;
    private String role;

    public Option2(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return role; // Pour afficher le nom dans la liste déroulante
    }
}
