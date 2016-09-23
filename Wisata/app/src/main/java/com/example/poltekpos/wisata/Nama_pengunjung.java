package com.example.poltekpos.wisata;

/**
 * Created by Firmanz on 6/5/2016.
 */

public class Nama_pengunjung extends Koneksi {
        String URL = "http://192.168.100.2/wisata/server.php";
        String url = "";
        String response = "";

        public String tampilnm_pengunjung() {
            try {
                url = URL + "?operasi=view_nm_pengunjung";
                System.out.println("URL Tampil Nama Pengunjung: " + url);
                response = call(url);
            } catch (Exception e) {
            }
            return response;
        }

        public String insertnm_pengunjung(String nama, String alamat, String kontak) {
            nama = nama.replace(" ", "%20");
            alamat = alamat.replace(" ", "%20");
            kontak = kontak.replace(" ", "%20");

            try {
                url = URL + "?operasi=insert_nm_pengunjung&nama=" + nama + "&alamat=" + alamat + "&kontak=" + kontak;
                System.out.println("URL Insert Nama Pengunjung : " + url);
                response = call(url);
            } catch (Exception e) {
            }
            return response;
        }

        public String getnm_pengunjungById(int id) {
            try {
                url = URL + "?operasi=get_nm_pengunjung_by_id&id=" + id;
                System.out.println("URL Insert Nama Pengunjung : " + url);
                response = call(url);
            } catch (Exception e) {
            }
            return response;
        }

        public String updatenm_pengunjung(String id, String nama, String alamat, String kontak) {
            nama = nama.replace(" ", "%20");
            alamat = alamat.replace(" ", "%20");
            kontak = kontak.replace(" ", "%20");

            try {
                url = URL + "?operasi=update_nm_pengunjung&id=" + id + "&nama=" + nama + "&alamat=" + alamat + "&kontak=" + kontak;
                System.out.println("URL Insert Nama Pengunjung : " + url);
                response = call(url);
            } catch (Exception e) {
            }
            return response;
        }

        public String deletenm_pengunjung(int id) {
            try {
                url = URL + "?operasi=delete_nm_pengunjung&id=" + id;
                System.out.println("URL Insert Nama Pengunjung : " + url);
                response = call(url);
            } catch (Exception e) {
            }
            return response;
        }
    }


