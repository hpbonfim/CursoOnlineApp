package com.example.curso_online.activities.alunos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.curso_online.R;
import com.example.curso_online.entities.Aluno;
import com.example.curso_online.entities.Curso;
import com.example.curso_online.viewmodel.AlunoViewModel;
import com.example.curso_online.viewmodel.CursoViewModel;

public class AddEditAlunoActivity extends AppCompatActivity {

    private EditText editTextNome, editTextEmail, editTextTelefone;
    private AutoCompleteTextView editTextCurso;
    private Button buttonSave;
    private AlunoViewModel alunoViewModel;
    private CursoViewModel cursoViewModel;


    public static final String EXTRA_ID = "com.example.curso_online.EXTRA_ID";
    public static final String EXTRA_NOME = "com.example.curso_online.EXTRA_NOME";
    public static final String EXTRA_CURSO = "com.example.curso_online.EXTRA_CURSO";
    public static final String EXTRA_EMAIL = "com.example.curso_online.EXTRA_EMAIL";
    public static final String EXTRA_TELEFONE = "com.example.curso_online.EXTRA_TELEFONE";
    public static final int EDIT_ALUNO_REQUEST = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_aluno);

        editTextNome = findViewById(R.id.edit_text_title);
        editTextCurso = findViewById(R.id.auto_complete_curso);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextTelefone = findViewById(R.id.edit_text_telefone);
        buttonSave = findViewById(R.id.button_save);

        alunoViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(AlunoViewModel.class);
        cursoViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(CursoViewModel.class);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle(R.string.edit_student);
            editTextNome.setText(intent.getStringExtra(EXTRA_NOME));
            editTextCurso.setText(intent.getStringExtra(EXTRA_CURSO));
            editTextEmail.setText(intent.getStringExtra(EXTRA_EMAIL));
            editTextTelefone.setText(intent.getStringExtra(EXTRA_TELEFONE));
        } else {
            setTitle(R.string.add_student);
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAluno();
            }
        });
    }
    private void saveAluno() {
        String nomeAluno = editTextNome.getText().toString();
        String cursoAluno = editTextCurso.getText().toString();
        String emailAluno = editTextEmail.getText().toString();
        String telefoneAluno = editTextTelefone.getText().toString();

        if (nomeAluno.trim().isEmpty() || cursoAluno.trim().isEmpty() || emailAluno.trim().isEmpty() || telefoneAluno.trim().isEmpty()) {
            Toast.makeText(this, R.string.please_insert, Toast.LENGTH_SHORT).show();
            return;
        }

        cursoViewModel.getCursoByName(cursoAluno).observe(this, new Observer<Curso>() {
            @Override
            public void onChanged(Curso curso) {
                if (curso == null) {
                    Toast.makeText(AddEditAlunoActivity.this, R.string.course_not_found, Toast.LENGTH_SHORT).show();
                    return;
                }

                Aluno aluno = new Aluno(curso.getCursoId(), nomeAluno, emailAluno, telefoneAluno);

                Intent data = new Intent();
                data.putExtra(EXTRA_NOME, nomeAluno);
                data.putExtra(EXTRA_CURSO, cursoAluno);
                data.putExtra(EXTRA_EMAIL, emailAluno);
                data.putExtra(EXTRA_TELEFONE, telefoneAluno);

                int id = getIntent().getIntExtra(EXTRA_ID, -1);
                if (id != -1) {
                    aluno.setAlunoId(id);
                    alunoViewModel.update(aluno);
                    data.putExtra(EXTRA_ID, id);
                    Toast.makeText(AddEditAlunoActivity.this, R.string.student_updated, Toast.LENGTH_SHORT).show();
                } else {
                    alunoViewModel.insert(aluno);
                    Toast.makeText(AddEditAlunoActivity.this, R.string.student_saved, Toast.LENGTH_SHORT).show();
                }

                setResult(RESULT_OK, data);
                finish();
            }
        });
    }


}
