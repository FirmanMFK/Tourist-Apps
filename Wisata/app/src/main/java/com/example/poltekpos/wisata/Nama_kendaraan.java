package com.example.poltekpos.wisata;

/**
 * Created by Firmanz on 6/5/2016.
 */
public class Nama_kendaraan extends Koneksi {
    String URL = "http://192.168.100.2/wisata/server.php";
    String url = "";
    String response = "";

    public String tampilnm_kendaraan() {
        try {
            url = URL + "?operasi=view_nm_kendaraan";
            System.out.println("URL Tampil Nama Kendaraan: " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String insertnm_kendaraan(String jenis, String plat, String merk) {
        jenis = jenis.replace(" ", "%20");
        plat = plat.replace(" ", "%20");
        merk = merk.replace(" ", "%20");

        try {
            url = URL + "?operasi=insert_nm_kendaraan&jenis=" + jenis + "&plat=" + plat + "&merk=" + merk;
            System.out.println("URL Insert Nama Kendaraan : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String getnm_kendaraanById(int id) {
        try {
            url = URL + "?operasi=get_nm_kendaraan_by_id&id=" + id;
            System.out.println("URL Insert Nama Kendaraan : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String updatenm_kendaraan(String id, String jenis, String plat, String merk) {
        jenis = jenis.replace(" ", "%20");
        plat = plat.replace(" ", "%20");
        merk = merk.replace(" ", "%20");

        try {
            url = URL + "?operasi=update_nm_kendaraan&id=" + id + "&jenis=" + jenis + "&plat=" + plat + "&merk=" + merk;
            System.out.println("URL Insert Nama Kendaraan : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String deletenm_kendaraan(int id) {
        try {
            url = URL + "?operasi=delete_nm_kendaraan&id=" + id;
            System.out.println("URL Insert Nama Kendaraan : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

}
