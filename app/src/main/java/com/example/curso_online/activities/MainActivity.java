package com.example.curso_online.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.curso_online.R;
import com.example.curso_online.activities.alunos.AlunoActivity;
import com.example.curso_online.activities.cursos.CursoActivity;

public class MainActivity extends AppCompatActivity {

    private Button manageCoursesButton;
    private Button manageStudentsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manageCoursesButton = findViewById(R.id.btn_manage_courses);

        manageCoursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CursoActivity.class);
                startActivity(intent);
            }
        });

        manageStudentsButton = findViewById(R.id.btn_manage_students);

        manageStudentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlunoActivity.class);
                startActivity(intent);
            }
        });

    }
}
