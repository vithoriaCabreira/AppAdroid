package br.edu.utfpr.vithoriacabreira.minhasobras;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    private EditText etNome, etArtista, etEstilo, etTipo;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        etNome = findViewById(R.id.etNome);
        etArtista = findViewById(R.id.etArtista);
        etEstilo = findViewById(R.id.etEstilo);
        etTipo = findViewById(R.id.etTipo);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(v -> salvar());
    }

    private void salvar() {
        String nome = etNome.getText().toString();
        String artista = etArtista.getText().toString();
        String estilo = etEstilo.getText().toString();
        String tipo = etTipo.getText().toString();

        if (nome.isEmpty() || artista.isEmpty() || estilo.isEmpty() || tipo.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent resultIntent = new Intent();
        resultIntent.putExtra("nome", nome);
        resultIntent.putExtra("artista", artista);
        resultIntent.putExtra("estilo", estilo);
        resultIntent.putExtra("tipo", tipo);

        setResult(RESULT_OK, resultIntent);
        finish();
    }

    // Para o botão de voltar na ActionBar funcionar
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}