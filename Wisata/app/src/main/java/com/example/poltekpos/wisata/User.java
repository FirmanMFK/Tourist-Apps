package com.example.poltekpos.wisata;

/**
 * Created by Firmanz on 6/7/2016.
 */
public class User extends Koneksi {
    String URL = "http://192.168.100.2/wisata/server.php";
    String url = "";
    String response = "";

    public String tampilnm_user() {
        try {
            url = URL + "?operasi=view_nm_user";
            System.out.println("URL Tampil Nama User: " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String insertnm_user(String nama, String password, String alamat, String umur) {
        nama = nama.replace(" ", "%20");
        password = password.replace(" ", "%20");
        alamat = alamat.replace(" ", "%20");
        umur = umur.replace(" ", "%20");

        try {
            url = URL + "?operasi=insert_nm_user&nama=" + nama + "&password=" + password + "&alamat=" + alamat + "&umur=" + umur;
            System.out.println("URL Insert Nama User : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String getnm_userById(int id) {
        try {
            url = URL + "?operasi=get_nm_user_by_id&id=" + id;
            System.out.println("URL Insert Nama User : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String updatenm_user(String id, String nama, String password, String alamat, String umur) {
        nama = nama.replace(" ", "%20");
        password = password.replace(" ", "%20");
        alamat = alamat.replace(" ", "%20");
        umur = umur.replace(" ", "%20");

        try {
            url = URL + "?operasi=update_nm_user&id=" + id + "&nama=" + nama + "&password=" + password + "&alamat=" + alamat + "&umur=" + umur;
            System.out.println("URL Insert Nama User : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String deletenm_user(int id) {
        try {
            url = URL + "?operasi=delete_nm_user&id=" + id;
            System.out.println("URL Insert Nama User : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

}
