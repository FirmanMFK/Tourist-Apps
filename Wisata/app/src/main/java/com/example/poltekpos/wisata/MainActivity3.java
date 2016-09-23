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

public class MainActivity3 extends Activity implements OnClickListener {

    Nama_wisata wisata = new Nama_wisata();
    TableLayout tabelwisata;
    Button buttonTambahWisata;
    ArrayList<Button> buttonEdit = new ArrayList<Button>();
    ArrayList<Button> buttonDelete = new ArrayList<Button>();

    JSONArray arraywisata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        tabelwisata = (TableLayout) findViewById(R.id.tablewisata);
        buttonTambahWisata = (Button) findViewById(R.id.buttonTambahWisata);
        buttonTambahWisata.setOnClickListener(this);

        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.CYAN);

        TextView viewHeaderId = new TextView(this);
        TextView viewHeaderNama = new TextView(this);
        TextView viewHeaderJenis = new TextView(this);
        TextView viewHeaderTgl_buat = new TextView(this);
        TextView viewHeaderAction = new TextView(this);

        viewHeaderId.setText("ID");
        viewHeaderNama.setText("Nama");
        viewHeaderJenis.setText("Jenis");
        viewHeaderTgl_buat.setText("Tgl_buat");
        viewHeaderAction.setText("Action");

        viewHeaderId.setPadding(5, 1, 5, 1);
        viewHeaderNama.setPadding(5, 1, 5, 1);
        viewHeaderJenis.setPadding(5, 1, 5, 1);
        viewHeaderTgl_buat.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);

        barisTabel.addView(viewHeaderId);
        barisTabel.addView(viewHeaderNama);
        barisTabel.addView(viewHeaderJenis);
        barisTabel.addView(viewHeaderTgl_buat);
        barisTabel.addView(viewHeaderAction);

        tabelwisata.addView(barisTabel, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        try {

            arraywisata = new JSONArray(wisata.tampilnm_wisata());

            for (int i = 0; i < arraywisata.length(); i++) {
                JSONObject jsonChildNode = arraywisata.getJSONObject(i);
                String nama = jsonChildNode.optString("nama");
                String jenis = jsonChildNode.optString("jenis");
                String tgl_buat = jsonChildNode.optString("tgl_buat");
                String id = jsonChildNode.optString("id");

                System.out.println("Nama :" + nama);
                System.out.println("Jenis :" + jenis);
                System.out.println("Tgl_buat :" + tgl_buat);
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

                TextView viewJenis = new TextView(this);
                viewJenis.setText(jenis);
                viewJenis.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewJenis);

                TextView viewTgl_buat = new TextView(this);
                viewTgl_buat.setText(tgl_buat);
                viewTgl_buat.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewTgl_buat);

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

                tabelwisata.addView(barisTabel, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void onClick(View view) {
        if (view.getId() == R.id.buttonTambahWisata) {
            // Toast.makeText(MainActivity2.this, "Button Tambah Data",
            // Toast.LENGTH_SHORT).show();

            tambahWisata();
        } else {
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
                    deleteWisata(id);

                }
            }
        }
    }
    public void deleteWisata(int id) {
        wisata.deletenm_wisata(id);

  /* restart acrtivity */
        finish();
        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
        startActivity(getIntent());
    }
    public void getDataByID(int id) {

        String namaEdit = null, jenisEdit = null, tgl_buatEdit = null;
        JSONArray arrayPersonal;

        try {

            arrayPersonal = new JSONArray(wisata.getnm_wisataById(id));

            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                namaEdit = jsonChildNode.optString("nama");
                jenisEdit = jsonChildNode.optString("jenis");
                tgl_buatEdit = jsonChildNode.optString("tgl_buat");
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

        final EditText editJenis = new EditText(this);
        editJenis.setText(jenisEdit);
        layoutInput.addView(editJenis);

        final EditText editTgl_buat = new EditText(this);
        editTgl_buat.setText(tgl_buatEdit);
        layoutInput.addView(editTgl_buat);

        AlertDialog.Builder builderEditWisata = new AlertDialog.Builder(this);
        builderEditWisata.setIcon(R.drawable.kendaraan);
        builderEditWisata.setTitle("Update Nama Wisata");
        builderEditWisata.setView(layoutInput);
        builderEditWisata.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nama = editNama.getText().toString();
                String jenis = editJenis.getText().toString();
                String tgl_buat = editTgl_buat.getText().toString();

                System.out.println("Nama : " + nama + " Jenis : " + jenis + " Tgl_buat : " + tgl_buat);

                String laporan = wisata.updatenm_wisata(viewId.getText().toString(), editNama.getText().toString(), editJenis.getText().toString(), editTgl_buat.getText().toString());

                Toast.makeText(MainActivity3.this, laporan, Toast.LENGTH_SHORT).show();

    /* restart acrtivity */
                finish();
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                startActivity(getIntent());
            }

        });

        builderEditWisata.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderEditWisata.show();

    }
    public void tambahWisata() {
  /* layout akan ditampilkan pada AlertDialog */
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final EditText editNama = new EditText(this);
        editNama.setHint("Nama");
        layoutInput.addView(editNama);

        final EditText editJenis = new EditText(this);
        editJenis.setHint("Jenis");
        layoutInput.addView(editJenis);

        final EditText editTgl_buat = new EditText(this);
        editTgl_buat.setHint("Tgl_buat");
        layoutInput.addView(editTgl_buat);

        AlertDialog.Builder builderInsertWisata = new AlertDialog.Builder(this);
        builderInsertWisata.setIcon(R.drawable.kendaraan);
        builderInsertWisata.setTitle("Insert Wisata Baru");
        builderInsertWisata.setView(layoutInput);
        builderInsertWisata.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nama = editNama.getText().toString();
                String jenis = editJenis.getText().toString();
                String tgl_buat = editTgl_buat.getText().toString();

                System.out.println("Nama : " + nama + " jenis : " + jenis + " tgl_buat : " + tgl_buat);

                String laporan = wisata.insertnm_wisata(nama, jenis, tgl_buat);

                Toast.makeText(MainActivity3.this, laporan, Toast.LENGTH_SHORT).show();

    /* restart acrtivity */
                finish();
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                startActivity(getIntent());
            }

        });

        builderInsertWisata.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertWisata.show();

    }

}
