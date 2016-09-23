package com.example.poltekpos.wisata;

/**
 * Created by Firmanz on 6/7/2016.
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

public class MainActivity4 extends Activity implements OnClickListener {

    User user = new User();
    TableLayout tabeluser;

    Button buttonTambahUser;
    ArrayList<Button> buttonEdit = new ArrayList<Button>();
    ArrayList<Button> buttonDelete = new ArrayList<Button>();

    JSONArray arrayUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        tabeluser = (TableLayout) findViewById(R.id.tableuser);
        buttonTambahUser = (Button) findViewById(R.id.buttonTambahUser);
        buttonTambahUser.setOnClickListener(this);

        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.CYAN);

        TextView viewHeaderId = new TextView(this);
        TextView viewHeaderNama = new TextView(this);
        TextView viewHeaderPassword = new TextView(this);
        TextView viewHeaderAlamat = new TextView(this);
        TextView viewHeaderUmur = new TextView(this);
        TextView viewHeaderAction = new TextView(this);

        viewHeaderId.setText("ID");
        viewHeaderNama.setText("Nama");
        viewHeaderPassword.setText("Password");
        viewHeaderAlamat.setText("Alamat");
        viewHeaderUmur.setText("Umur");
        viewHeaderAction.setText("Action");

        viewHeaderId.setPadding(5, 1, 5, 1);
        viewHeaderNama.setPadding(5, 1, 5, 1);
        viewHeaderPassword.setPadding(5, 1, 5, 1);
        viewHeaderAlamat.setPadding(5, 1, 5, 1);
        viewHeaderUmur.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);

        barisTabel.addView(viewHeaderId);
        barisTabel.addView(viewHeaderNama);
        barisTabel.addView(viewHeaderPassword);
        barisTabel.addView(viewHeaderAlamat);
        barisTabel.addView(viewHeaderUmur);
        barisTabel.addView(viewHeaderAction);

        tabeluser.addView(barisTabel, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));

        try {

            arrayUser = new JSONArray(user.tampilnm_user());

            for (int i = 0; i < arrayUser.length(); i++) {
                JSONObject jsonChildNode = arrayUser.getJSONObject(i);
                String nama = jsonChildNode.optString("nama");
                String password = jsonChildNode.optString("password");
                String alamat = jsonChildNode.optString("alamat");
                String umur = jsonChildNode.optString("umur");
                String id = jsonChildNode.optString("id");

                System.out.println("Nama :" + nama);
                System.out.println("Password :" + password);
                System.out.println("Alamat :" + alamat);
                System.out.println("Umur :" + umur);
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

                TextView viewPassword = new TextView(this);
                viewPassword.setText(password);
                viewPassword.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewPassword);

                TextView viewAlamat = new TextView(this);
                viewAlamat.setText(alamat);
                viewAlamat.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewAlamat);

                TextView viewUmur = new TextView(this);
                viewUmur.setText(umur);
                viewUmur.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewUmur);

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

                tabeluser.addView(barisTabel, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {

        if (view.getId() == R.id.buttonTambahUser) {
            // Toast.makeText(MainActivity.this, "Button Tambah Data",
            // Toast.LENGTH_SHORT).show();

            tambahUser();

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
                    deleteUser(id);

                }
            }
        }
    }

    public void deleteUser(int id) {
        user.deletenm_user(id);

  /* restart acrtivity */
        finish();
        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
        startActivity(getIntent());

    }

    public void getDataByID(int id) {

        String namaEdit = null, passwordEdit = null, alamatEdit = null, umurEdit = null;
        JSONArray arrayPersonal;

        try {

            arrayPersonal = new JSONArray(user.getnm_userById(id));

            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                namaEdit = jsonChildNode.optString("nama");
                passwordEdit = jsonChildNode.optString("password");
                alamatEdit = jsonChildNode.optString("alamat");
                umurEdit = jsonChildNode.optString("umur");
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

        final EditText editPassword = new EditText(this);
        editPassword.setText(passwordEdit);
        layoutInput.addView(editPassword);

        final EditText editAlamat = new EditText(this);
        editAlamat.setText(alamatEdit);
        layoutInput.addView(editAlamat);

        final EditText editUmur = new EditText(this);
        editUmur.setText(umurEdit);
        layoutInput.addView(editUmur);

        AlertDialog.Builder builderEditUser = new AlertDialog.Builder(this);
        builderEditUser.setIcon(R.drawable.kendaraan);
        builderEditUser.setTitle("Update Nama Pengguna");
        builderEditUser.setView(layoutInput);
        builderEditUser.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nama = editNama.getText().toString();
                String password = editPassword.getText().toString();
                String alamat = editAlamat.getText().toString();
                String umur = editUmur.getText().toString();

                System.out.println("Nama : " + nama + " Password : " + password + " Alamat : " + alamat + " Umur : " + umur);

                String laporan = user.updatenm_user(viewId.getText().toString(), editNama.getText().toString(), editPassword.getText().toString(), editAlamat.getText().toString(), editUmur.getText().toString());

                Toast.makeText(MainActivity4.this, laporan, Toast.LENGTH_SHORT).show();

    /* restart acrtivity */
                finish();
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                startActivity(getIntent());
            }

        });

        builderEditUser.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderEditUser.show();

    }

    public void tambahUser() {
  /* layout akan ditampilkan pada AlertDialog */
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final EditText editNama = new EditText(this);
        editNama.setHint("Nama");
        layoutInput.addView(editNama);

        final EditText editPassword = new EditText(this);
        editPassword.setHint("Password");
        layoutInput.addView(editPassword);

        final EditText editAlamat = new EditText(this);
        editAlamat.setHint("Alamat");
        layoutInput.addView(editAlamat);

        final EditText editUmur = new EditText(this);
        editUmur.setHint("Umur");
        layoutInput.addView(editUmur);

        AlertDialog.Builder builderInsertUser = new AlertDialog.Builder(this);
        builderInsertUser.setIcon(R.drawable.kendaraan);
        builderInsertUser.setTitle("Insert User");
        builderInsertUser.setView(layoutInput);
        builderInsertUser.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nama = editNama.getText().toString();
                String password = editPassword.getText().toString();
                String alamat = editAlamat.getText().toString();
                String umur = editUmur.getText().toString();

                System.out.println("Nama : " + nama + " Password : " + password + " Alamat : " + alamat + " Umur : " + umur);

                String laporan = user.insertnm_user(nama, password, alamat, umur);

                Toast.makeText(MainActivity4.this, laporan, Toast.LENGTH_SHORT).show();

    /* restart acrtivity */
                finish();
                overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
                startActivity(getIntent());
            }

        });

        builderInsertUser.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertUser.show();
    }

}
