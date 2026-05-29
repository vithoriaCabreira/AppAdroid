package br.edu.utfpr.vithoriacabreira.minhasobras;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    private EditText etNome, etArtista, etEstilo, etTipo;
    private Button btnSalvar;
    private int posicao = -1;

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

        // Check if editing
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("nome")) {
            etNome.setText(intent.getStringExtra("nome"));
            etArtista.setText(intent.getStringExtra("artista"));
            etEstilo.setText(intent.getStringExtra("estilo"));
            etTipo.setText(intent.getStringExtra("tipo"));
            posicao = intent.getIntExtra("posicao", -1);
            setTitle(R.string.menu_edit);
        }

        btnSalvar.setOnClickListener(v -> salvar());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_salvar) {
            salvar();
            return true;
        } else if (id == R.id.menu_limpar) {
            limpar();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void salvar() {
        String nome = etNome.getText().toString();
        String artista = etArtista.getText().toString();
        String estilo = etEstilo.getText().toString();
        String tipo = etTipo.getText().toString();

        if (nome.isEmpty() || artista.isEmpty() || estilo.isEmpty() || tipo.isEmpty()) {
            Toast.makeText(this, getString(R.string.toast_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        Intent resultIntent = new Intent();
        resultIntent.putExtra("nome", nome);
        resultIntent.putExtra("artista", artista);
        resultIntent.putExtra("estilo", estilo);
        resultIntent.putExtra("tipo", tipo);
        resultIntent.putExtra("posicao", posicao);

        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void limpar() {
        etNome.setText("");
        etArtista.setText("");
        etEstilo.setText("");
        etTipo.setText("");
        etNome.requestFocus();
        Toast.makeText(this, getString(R.string.toast_cleared), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}