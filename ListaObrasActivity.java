package br.edu.utfpr.vithoriacabreira.minhasobras;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class ListaObrasActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Obra> lista;
    private ObraAdapter adapter;
    private static final int REQUEST_CADASTRO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_obras);

        listView = findViewById(R.id.listViewObras);
        lista = new ArrayList<>();
        adapter = new ObraAdapter(this, lista);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);

        // Hook up the buttons from the layout
        Button btnAdicionar = findViewById(R.id.btnAdicionar);
        Button btnSobre = findViewById(R.id.btnSobre);

        btnAdicionar.setOnClickListener(v -> {
            Intent intent = new Intent(this, CadastroActivity.class);
            startActivityForResult(intent, REQUEST_CADASTRO);
        });

        btnSobre.setOnClickListener(v -> {
            startActivity(new Intent(this, SobreActivity.class));
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Obra obra = lista.get(position);
            String msg = getString(R.string.toast_selected, obra.getNome());
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ordenarLista();
        adapter.notifyDataSetChanged();
    }

    private void ordenarLista() {
        if (Preferencias.getOrdenacao(this)) {
            Collections.sort(lista, (o1, o2) ->
                    o1.getNome().compareToIgnoreCase(o2.getNome())
            );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_adicionar) {
            Intent intent = new Intent(this, CadastroActivity.class);
            startActivityForResult(intent, REQUEST_CADASTRO);
            return true;
        } else if (id == R.id.menu_config) {
            startActivity(new Intent(this, ConfiguracaoActivity.class));
            return true;
        } else if (id == R.id.menu_sobre) {
            startActivity(new Intent(this, SobreActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CADASTRO && resultCode == RESULT_OK && data != null) {
            String nome = data.getStringExtra("nome");
            String artista = data.getStringExtra("artista");
            String estilo = data.getStringExtra("estilo");
            String tipo = data.getStringExtra("tipo");
            int pos = data.getIntExtra("posicao", -1);

            Obra obra = new Obra(nome, artista, estilo, tipo);

            if (pos >= 0) {
                lista.set(pos, obra);
            } else {
                lista.add(obra);
            }

            ordenarLista();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = info.position;
        int id = item.getItemId();

        if (id == R.id.menu_editar) {
            Obra obra = lista.get(pos);
            Intent intent = new Intent(this, CadastroActivity.class);
            intent.putExtra("nome", obra.getNome());
            intent.putExtra("artista", obra.getArtistaId());
            intent.putExtra("estilo", obra.getEstilo());
            intent.putExtra("tipo", obra.getTipo());
            intent.putExtra("posicao", pos);
            startActivityForResult(intent, REQUEST_CADASTRO);
            return true;
        } else if (id == R.id.menu_excluir) {
            lista.remove(pos);
            adapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }


}