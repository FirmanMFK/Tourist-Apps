package com.example.poltekpos.wisata;

/**
 * Created by Firmanz on 6/5/2016.
 */
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends Activity implements OnClickListener {
    Nama_pengunjung pengunjung = new Nama_pengunjung();
    TableLayout tabelpengunjung;
    Button buttonTambahPengunjung;
    ArrayList<Button> buttonEdit = new ArrayList<Button>();
    ArrayList<Button> buttonDelete = new ArrayList<Button>();

    JSONArray arrayPengunjung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        tabelpengunjung = (TableLayout) findViewById(R.id.tablepengunjung);
        buttonTambahPengunjung = (Button) findViewById(R.id.buttonTambahPengunjung);
        buttonTambahPengunjung.setOnClickListener(this);

        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.CYAN);

        TextView viewHeaderId = new TextView(this);
        TextView viewHeaderNama = new TextView(this);
        TextView viewHeaderAlamat = new TextView(this);
        TextView viewHeaderKontak = new TextView(this);
        TextView viewHeaderAction = new TextView(this);

        viewHeaderId.setText("ID");
        viewHeaderNama.setText("Nama");
        viewHeaderAlamat.setText("Alamat");
        viewHeaderKontak.setText("Kontak");
        viewHeaderAction.setText("Action");

        viewHeaderId.setPadding(5, 1, 5, 1);
        viewHeaderNama.setPadding(5, 1, 5, 1);
        viewHeaderAlamat.setPadding(5, 1, 5, 1);
        viewHeaderKontak.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);

        barisTabel.addView(viewHeaderId);
        barisTabel.addView(viewHeaderNama);
        barisTabel.addView(viewHeaderAlamat);
        barisTabel.addView(viewHeaderKontak);
        barisTabel.addView(viewHeaderAction);

        tabelpengunjung.addView(barisTabel, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));

        try {

            arrayPengunjung = new JSONArray(pengunjung.tampilnm_pengunjung());

            for (int i = 0; i < arrayPengunjung.length(); i++) {
                JSONObject jsonChildNode = arrayPengunjung.getJSONObject(i);
                String nama = jsonChildNode.optString("nama");
                String alamat = jsonChildNode.optString("alamat");
                String kontak = jsonChildNode.optString("kontak");
                String id = jsonChildNode.optString("id");

                System.out.println("Nama :" + nama);
                System.out.println("Alamat :" + alamat);
                System.out.println("Kontak :" + kontak);
                System.out.println("ID :" + id);

                barisTabel = new TableRow(this);

                if (i % 2 == 0) {
                    barisTabel.setBackgroundColor(Color.LTGRAY);
                }

                TextView viewId = new TextView(this);
                viewId.setText(id);
                viewId.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewId);

                TextView viewNama = new TextView(this);
                viewNama.setText(nama);
                viewNama.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewNama);

                TextView viewAlamat = new TextView(this);
                viewAlamat.setText(alamat);
                viewAlamat.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewAlamat);

                TextView viewKontak = new TextView(this);
                viewKontak.setText(kontak);
                viewKontak.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewKontak);

                buttonEdit.add(i, new Button(this));
                buttonEdit.get(i).setId(Integer.parseInt(id));
                buttonEdit.get(i).setTag("Edit");
                buttonEdit.get(i).setText("Edit");
                buttonEdit.get(i).setOnClickListener(this);
                barisTabel.addView(buttonEdit.get(i));

                buttonDelete.add(i, new Button(this));
                buttonDelete.get(i).setId(Integer.parseInt(id));
                buttonDelete.get(i).setTag("Delete");
                buttonDelete.get(i).setText("Delete");
                buttonDelete.get(i).setOnClickListener(this);
                barisTabel.addView(buttonDelete.get(i));

                tabelpengunjung.addView(barisTabel, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void onClick(View view) {

        if (view.getId() == R.id.buttonTambahPengunjung) {
            // Toast.makeText(MainActivity2.this, "Button Tambah Data",
            // Toast.LENGTH_SHORT).show();

            tambahPengunjung();

        } else {
   /*
    * Melakukan pengecekan pada data array, agar sesuai dengan index
    * masing-masing button
    */
            for (int i = 0; i < buttonEdit.size(); i++) {

    /* jika yang diklik adalah button edit */
                if (view.getId() == buttonEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")) {
                    // Toast.makeText(MainActivity2.this, "Edit : " +
                    // buttonEdit.get(i).getId(), Toast.LENGTH_SHORT).show();
                    int id = buttonEdit.get(i).getId();
                    getDataByID(id);

                } /* jika yang diklik adalah button delete */
                else if (view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")) {
                    // Toast.makeText(MainActivity.this, "Delete : " +
                    // buttonDelete.get(i).getId(), Toast.LENGTH_SHORT).show();
                    int id = buttonDelete.get(i).getId();
                    deletePengunjung(id);

                }
            }
        }
    }
    public void deletePengunjung(int id) {
        pengunjung.deletenm_pengunjung(id);

  /* restart acrtivity */
        finish();
        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
        startActivity(getIntent());

    }

    public void getDataByID(int id) {

        String namaEdit = null, alamatEdit = null, kontakEdit = null;
        JSONArray arrayPersonal;

        try {

            arrayPersonal = new JSONArray(pengunjung.getnm_pengunjungById(id));

            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                namaEdit = jsonChildNode.optString("nama");
                alamatEdit = jsonChildNode.optString("alamat");
                kontakEdit = jsonChildNode.optString("kontak");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        // buat id tersembunyi di alertbuilder
        final TextView viewId = new TextView(this);
        viewId.setText(String.valueOf(id));
        viewId.setTextColor(Color.TRANSPARENT);
        layoutInput.addView(viewId);

        final EditText editNama = new EditText(this);
        editNama.setText(namaEdit);
        layoutInput.addView(editNama);

        final EditText editAlamat = new EditText(this);
        editAlamat.setText(alamatEdit);
        layoutInput.addView(editAlamat);

        final EditText editKontak = new EditText(this);
        editKontak.setText(kontakEdit);
        layoutInput.addView(editKontak);

        AlertDialog.Builder builderEditPengunjung = new AlertDialog.Builder(this);
        builderEditPengunjung.setIcon(R.drawable.kendaraan);
        builderEditPengunjung.setTitle("Update Nama Pengunjung");
        builderEditPengunjung.setView(layoutInput);
        builderEditPengunjung.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nama = editNama.getText().toString();
                String alamat = editAlamat.getText().toString();
                String kontak = editKontak.getText().toString();

                System.out.println("Nama : " + nama + " Alamat : " + alamat + " Kontak : " + kontak);

                String laporan = pengunjung.updatenm_pengunjung(viewId.getText().toString(), editNama.getText().toString(), editAlamat.getText().toString(), editKontak.getText().toString());

                Toast.makeText(MainActivity2.this, laporan, Toast.LENGTH_SHORT).show();

    /* restart acrtivity */
                finish();
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                startActivity(getIntent());
            }

        });

        builderEditPengunjung.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderEditPengunjung.show();

    }
    public void tambahPengunjung() {
  /* layout akan ditampilkan pada AlertDialog */
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final EditText editNama = new EditText(this);
        editNama.setHint("Nama");
        layoutInput.addView(editNama);

        final EditText editAlamat = new EditText(this);
        editAlamat.setHint("Alamat");
        layoutInput.addView(editAlamat);

        final EditText editKontak = new EditText(this);
        editKontak.setHint("Kontak");
        layoutInput.addView(editKontak);

        AlertDialog.Builder builderInsertPengunjung = new AlertDialog.Builder(this);
        builderInsertPengunjung.setIcon(R.drawable.kendaraan);
        builderInsertPengunjung.setTitle("Insert Pengunjung");
        builderInsertPengunjung.setView(layoutInput);
        builderInsertPengunjung.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nama = editNama.getText().toString();
                String alamat = editAlamat.getText().toString();
                String kontak = editKontak.getText().toString();

                System.out.println("Nama : " + nama + " Alamat : " + alamat + " Kontak : " + kontak);

                String laporan = pengunjung.insertnm_pengunjung(nama, alamat, kontak);

                Toast.makeText(MainActivity2.this, laporan, Toast.LENGTH_SHORT).show();

    /* restart acrtivity */
                finish();
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                startActivity(getIntent());
            }

        });

        builderInsertPengunjung.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertPengunjung.show();
    }

}
