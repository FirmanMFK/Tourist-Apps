package com.example.poltekpos.wisata;

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

public class MainActivity extends Activity implements OnClickListener {
    Nama_kendaraan kendaraan = new Nama_kendaraan();
    TableLayout tabelkendaraan;

    Button buttonTambahKendaraan;
    ArrayList<Button> buttonEdit = new ArrayList<Button>();
    ArrayList<Button> buttonDelete = new ArrayList<Button>();

    JSONArray arrayKendaraan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        tabelkendaraan = (TableLayout) findViewById(R.id.tablekendaraan);
        buttonTambahKendaraan = (Button) findViewById(R.id.buttonTambahKendaraan);
        buttonTambahKendaraan.setOnClickListener(this);

        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.CYAN);

        TextView viewHeaderId = new TextView(this);
        TextView viewHeaderJenis = new TextView(this);
        TextView viewHeaderPlat = new TextView(this);
        TextView viewHeaderMerk = new TextView(this);
        TextView viewHeaderAction = new TextView(this);

        viewHeaderId.setText("ID");
        viewHeaderJenis.setText("Jenis");
        viewHeaderPlat.setText("Plat");
        viewHeaderMerk.setText("Merk");
        viewHeaderAction.setText("Action");

        viewHeaderId.setPadding(5, 1, 5, 1);
        viewHeaderJenis.setPadding(5, 1, 5, 1);
        viewHeaderPlat.setPadding(5, 1, 5, 1);
        viewHeaderMerk.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);

        barisTabel.addView(viewHeaderId);
        barisTabel.addView(viewHeaderJenis);
        barisTabel.addView(viewHeaderPlat);
        barisTabel.addView(viewHeaderMerk);
        barisTabel.addView(viewHeaderAction);

        tabelkendaraan.addView(barisTabel, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));

        try {

            arrayKendaraan = new JSONArray(kendaraan.tampilnm_kendaraan());

            for (int i = 0; i < arrayKendaraan.length(); i++) {
                JSONObject jsonChildNode = arrayKendaraan.getJSONObject(i);
                String jenis = jsonChildNode.optString("jenis");
                String plat = jsonChildNode.optString("plat");
                String merk = jsonChildNode.optString("merk");
                String id = jsonChildNode.optString("id");

                System.out.println("Jenis :" + jenis);
                System.out.println("Plat :" + plat);
                System.out.println("Merk :" + merk);
                System.out.println("ID :" + id);

                barisTabel = new TableRow(this);

                if (i % 2 == 0) {
                    barisTabel.setBackgroundColor(Color.LTGRAY);
                }

                TextView viewId = new TextView(this);
                viewId.setText(id);
                viewId.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewId);

                TextView viewJenis = new TextView(this);
                viewJenis.setText(jenis);
                viewJenis.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewJenis);

                TextView viewPlat = new TextView(this);
                viewPlat.setText(plat);
                viewPlat.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewPlat);

                TextView viewMerk = new TextView(this);
                viewMerk.setText(merk);
                viewMerk.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewMerk);

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

                tabelkendaraan.addView(barisTabel, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {

        if (view.getId() == R.id.buttonTambahKendaraan) {
            // Toast.makeText(MainActivity.this, "Button Tambah Data",
            // Toast.LENGTH_SHORT).show();

            tambahKendaraan();

        } else {
   /*
    * Melakukan pengecekan pada data array, agar sesuai dengan index
    * masing-masing button
    */
            for (int i = 0; i < buttonEdit.size(); i++) {

    /* jika yang diklik adalah button edit */
                if (view.getId() == buttonEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")) {
                    // Toast.makeText(MainActivity.this, "Edit : " +
                    // buttonEdit.get(i).getId(), Toast.LENGTH_SHORT).show();
                    int id = buttonEdit.get(i).getId();
                    getDataByID(id);

                } /* jika yang diklik adalah button delete */
                else if (view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")) {
                    // Toast.makeText(MainActivity.this, "Delete : " +
                    // buttonDelete.get(i).getId(), Toast.LENGTH_SHORT).show();
                    int id = buttonDelete.get(i).getId();
                    deleteKendaraan(id);

                }
            }
        }
    }

    public void deleteKendaraan(int id) {
        kendaraan.deletenm_kendaraan(id);

  /* restart acrtivity */
        finish();
        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
        startActivity(getIntent());

    }

    public void getDataByID(int id) {

        String jenisEdit = null, platEdit = null, merkEdit = null;
        JSONArray arrayPersonal;

        try {

            arrayPersonal = new JSONArray(kendaraan.getnm_kendaraanById(id));

            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                jenisEdit = jsonChildNode.optString("jenis");
                platEdit = jsonChildNode.optString("plat");
                merkEdit = jsonChildNode.optString("merk");
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

        final EditText editJenis = new EditText(this);
        editJenis.setText(jenisEdit);
        layoutInput.addView(editJenis);

        final EditText editPlat = new EditText(this);
        editPlat.setText(platEdit);
        layoutInput.addView(editPlat);

        final EditText editMerk = new EditText(this);
        editMerk.setText(merkEdit);
        layoutInput.addView(editMerk);

        AlertDialog.Builder builderEditKendaraan = new AlertDialog.Builder(this);
        builderEditKendaraan.setIcon(R.drawable.kendaraan);
        builderEditKendaraan.setTitle("Update Nama Kendaraaan");
        builderEditKendaraan.setView(layoutInput);
        builderEditKendaraan.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String jenis = editJenis.getText().toString();
                String plat = editPlat.getText().toString();
                String merk = editMerk.getText().toString();

                System.out.println("Jenis : " + jenis + " Plat : " + plat + " Merk : " + merk);

                String laporan = kendaraan.updatenm_kendaraan(viewId.getText().toString(), editJenis.getText().toString(), editPlat.getText().toString(), editMerk.getText().toString());

                Toast.makeText(MainActivity.this, laporan, Toast.LENGTH_SHORT).show();

    /* restart acrtivity */
                finish();
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                startActivity(getIntent());
            }

        });

        builderEditKendaraan.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderEditKendaraan.show();

    }

    public void tambahKendaraan() {
  /* layout akan ditampilkan pada AlertDialog */
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final EditText editJenis = new EditText(this);
        editJenis.setHint("Jenis");
        layoutInput.addView(editJenis);

        final EditText editPlat = new EditText(this);
        editPlat.setHint("Plat");
        layoutInput.addView(editPlat);

        final EditText editMerk = new EditText(this);
        editMerk.setHint("Merk");
        layoutInput.addView(editMerk);

        AlertDialog.Builder builderInsertKendaraan = new AlertDialog.Builder(this);
        builderInsertKendaraan.setIcon(R.drawable.kendaraan);
        builderInsertKendaraan.setTitle("Insert Kendaraan");
        builderInsertKendaraan.setView(layoutInput);
        builderInsertKendaraan.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String jenis = editJenis.getText().toString();
                String plat = editPlat.getText().toString();
                String merk = editMerk.getText().toString();

                System.out.println("Jenis : " + jenis + " Plat : " + plat + " Merk : " + merk);

                String laporan = kendaraan.insertnm_kendaraan(jenis, plat, merk);

                Toast.makeText(MainActivity.this, laporan, Toast.LENGTH_SHORT).show();

    /* restart acrtivity */
                finish();
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                startActivity(getIntent());
            }

        });

        builderInsertKendaraan.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertKendaraan.show();
    }
}
