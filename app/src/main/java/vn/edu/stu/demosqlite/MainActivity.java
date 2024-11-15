package vn.edu.stu.demosqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import vn.edu.stu.demosqlite.model.Sinhvien;

public class MainActivity extends AppCompatActivity {

    public static final String DB_NAME = "dbsinhvien.sqlite";
    public static final String DB_PASS_SUFFIX = "/databases/";

    ArrayAdapter<Sinhvien> adapter;
    ListView lvSV;
    Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        copyDbFileFromAssets();
        addControls();
        addEvents();
        loadDSSV();
    }

    private void loadDSSV() {
        SQLiteDatabase database = openOrCreateDatabase(DB_NAME,MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT * FROM sinhvien",null);
        adapter.clear();
        while (cursor.moveToNext()){
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String lop = cursor.getString(2);
            adapter.add(new Sinhvien(ma,ten,lop));
        }
        cursor.close();
        database.close();
        adapter.notifyDataSetChanged();
    }

    private void copyDbFileFromAssets() {
        File dbFile = getDatabasePath(DB_NAME);
        if (!dbFile.exists()) {
            File dbDir = new File(getApplicationInfo().dataDir + DB_PASS_SUFFIX);
            if (!dbDir.exists()){
                dbDir.mkdir();
            }
            try {
                InputStream is = getAssets().open(DB_NAME);
                String outputFilePath = getApplicationInfo().dataDir + DB_PASS_SUFFIX + DB_NAME;
                OutputStream os = new FileOutputStream(outputFilePath);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0){
                    os.write(buffer, 0 , length);
                }
                os.flush();
                os.close();
                is.close();
            }catch (Exception e){
                Log.e("LOI", e.toString());
            }
        }

    }

    private void addEvents() {
        btnThem.setOnClickListener(view -> xulyThem());
    }

    private void xulyThem() {

    }

    private void addControls() {
        lvSV = findViewById(R.id.lvSV);
        btnThem = findViewById(R.id.btnThem);
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);
        lvSV.setAdapter(adapter);

    }
}