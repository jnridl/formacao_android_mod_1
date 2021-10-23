package id.logistics.ui.activity;

import static id.logistics.ui.activity.ContantesActivies.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import id.logistics.DAO.AlunoDAO;
import id.logistics.R;
import id.logistics.model.Aluno;

public class FormAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA = "Edita aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_aluno);

        inicializacaoDosCampos();
        carregaAluno();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_form_aluno, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if(itemId == R.id.activity_form_aluno_salvar){
            finalizaFormulario();
        }

        return super.onOptionsItemSelected(item);

    }

    private void carregaAluno() {

        Intent dados = getIntent();

        if(dados.hasExtra(CHAVE_ALUNO)){

            setTitle(TITULO_APPBAR_EDITA);

            aluno =  (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            carregaAlunos();

        }else{
            setTitle(TITULO_APPBAR_NOVO);
            aluno = new Aluno();
        }

    }

    private void carregaAlunos() {
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_form_aluno_nome);
        campoTelefone = findViewById(R.id.activity_form_aluno_telefone);
        campoEmail = findViewById(R.id.activity_form_aluno_email);
    }

    private void finalizaFormulario() {

        preencheAluno();

        if(aluno.temIdValido()){
            dao.edita(aluno);
        }else{
            dao.salva(aluno);
        }

        finish();

    }

    private void preencheAluno() {

        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);

    }

}