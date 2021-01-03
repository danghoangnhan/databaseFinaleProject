package com.naman14.timber.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class authentication {
    @SerializedName("userName")
    @Expose
    private String account;
    public int getId() {
        return id;
    }
    @SerializedName("passWord")
    @Expose
    private String password;
    @SerializedName("userId")
    @Expose
    private int id;
    public authentication(String username,String password,int id){
        this.account=username;
        this.password=password;
        this.id = id;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}