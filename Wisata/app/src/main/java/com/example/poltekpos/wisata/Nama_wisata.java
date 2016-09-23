package com.example.poltekpos.wisata;

/**
 * Created by Firmanz on 6/5/2016.
 */
public class Nama_wisata extends Koneksi {
    String URL = "http://192.168.100.2/wisata/server.php";
    String url = "";
    String response = "";

    public String tampilnm_wisata() {
        try {
            url = URL + "?operasi=view_nm_wisata";
            System.out.println("URL Tampil Nama Wisata: " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String insertnm_wisata(String nama, String jenis, String tgl_buat) {
        nama = nama.replace(" ", "%20");
        jenis = jenis.replace(" ", "%20");
        tgl_buat = tgl_buat.replace(" ", "%20");

        try {
            url = URL + "?operasi=insert_nm_wisata&nama=" + nama + "&jenis=" + jenis + "&tgl_buat=" + tgl_buat;
            System.out.println("URL Insert Nama Wisata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String getnm_wisataById(int id) {
        try {
            url = URL + "?operasi=get_nm_wisata_by_id&id=" + id;
            System.out.println("URL Insert Nama wisata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String updatenm_wisata(String id, String nama, String jenis, String tgl_buat) {
        nama = nama.replace(" ", "%20");
        jenis = jenis.replace(" ", "%20");
        tgl_buat = tgl_buat.replace(" ", "%20");

        try {
            url = URL + "?operasi=update_nm_wisata&id=" + id + "&nama=" + nama + "&jenis=" + jenis + "&tgl_buat=" + tgl_buat;
            System.out.println("URL Insert Nama Wisata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String deletenm_wisata(int id) {
        try {
            url = URL + "?operasi=delete_nm_wisata&id=" + id;
            System.out.println("URL Insert Nama Wisata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }
}
