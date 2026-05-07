package br.edu.utfpr.vithoriacabreira.minhasobras;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

        // Clique simples: Toast internacionalizado
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Obra obra = lista.get(position);
            String msg = getString(R.string.toast_selected, obra.getNome());
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Sempre verifica a ordenação ao voltar para esta tela
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
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_adicionar) {
            Intent intent = new Intent(this, CadastroActivity.class);
            startActivityForResult(intent, REQUEST_CADASTRO);
            return true;
        } else if (id == R.id.menu_sobre) {
            startActivity(new Intent(this, SobreActivity.class));
            return true;
        } else if (id == R.id.menu_config) {
            // Se você criou a tela de configurações separada
            startActivity(new Intent(this, ConfiguracaoActivity.class));
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

    // Menu de contexto para Editar/Excluir
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contexto, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = info.position;

        if (item.getItemId() == R.id.menu_edit) {
            Obra obra = lista.get(pos);
            Intent intent = new Intent(this, CadastroActivity.class);
            intent.putExtra("nome", obra.getNome());
            intent.putExtra("artista", obra.getArtista());
            intent.putExtra("estilo", obra.getEstilo());
            intent.putExtra("tipo", obra.getTipo());
            intent.putExtra("posicao", pos);
            startActivityForResult(intent, REQUEST_CADASTRO);
            return true;
        } else if (item.getItemId() == R.id.menu_delete) {
            lista.remove(pos);
            adapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}