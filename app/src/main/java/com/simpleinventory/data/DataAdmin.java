package com.simpleinventory.data;

public class DataAdmin {
    private int id_admin;
    private String nama_admin, email;

    public DataAdmin(int id_admin, String nama_admin, String email) {
        this.id_admin = id_admin;
        this.nama_admin = nama_admin;
        this.email = email;
    }

    public int getId_admin() {
        return id_admin;
    }

    public String getNama_admin() {
        return nama_admin;
    }

    public String getEmail() {
        return email;
    }

}
