package com.madhumankatha.sdcard;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edPath,edContent;
    private Button btnRead,btnSave;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edPath = findViewById(R.id.edpath);
        edContent = findViewById(R.id.edText);

        btnRead = findViewById(R.id.btnRead);
        btnSave = findViewById(R.id.btnSave);

        edPath.setText(getApplicationContext().getFilesDir().getPath() + "/");

        btnRead.setOnClickListener(v -> {
            try {
                List<String> s = Files.readAllLines(Paths.get(edPath.getText().toString()), StandardCharsets.UTF_8);
                edContent.setText(s.get(0));
                Toast.makeText(this, "Written in SD Card!!", Toast.LENGTH_SHORT).show();
            }catch (IOException e){
                Log.d("TAG", "onCreate: " + e.getMessage());
            }
        });

        btnSave.setOnClickListener(v -> {
            try {
                Files.write(Paths.get(edPath.getText().toString()),edContent.getText().toString().getBytes(),StandardOpenOption.CREATE_NEW);
                Toast.makeText(this, "Loaded Successful!!", Toast.LENGTH_SHORT).show();
            }catch (IOException e){
                //Optional for debugging
                Log.d("TAG", "onCreate: " + e.getMessage());
                Log.d("TAG", "onCreate: " + e.getMessage());
                Log.d("TAG", "onCreate: " + getApplicationContext().getFilesDir().getPath() );
            }
        });


    }
}