package br.edu.utfpr.vithoriacabreira.minhasobras;

import android.os.Bundle;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class ConfiguracaoActivity extends AppCompatActivity {

    private Switch switchOrdenar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);
        setTitle(R.string.title_cadastro);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        switchOrdenar = findViewById(R.id.switchOrdenar);

        // Carrega o estado atual das SharedPreferences
        switchOrdenar.setChecked(Preferencias.getOrdenacao(this));

        // Salva sempre que o usuário tocar no switch
        switchOrdenar.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Preferencias.salvarOrdenacao(this, isChecked);
            String status = getString(R.string.toast_sort) + " " + isChecked;
            android.widget.Toast.makeText(this, status, android.widget.Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}