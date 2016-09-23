package com.example.poltekpos.wisata;

/**
 * Created by Firmanz on 6/8/2016.
 */
public class AppVar {
    public static final String LOGIN_URL =

            "http://192.168.100.2/wisata/login.php";

//Keys for email and password as defined in our $_POST['key'] in login.php

    public static final String KEY_NAMA = "nama";

    public static final String KEY_PASSWORD = "password";

//If server response is equal to this that means login is successful

    public static final String LOGIN_SUCCESS = "success";
}
