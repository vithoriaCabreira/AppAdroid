package br.edu.utfpr.vithoriacabreira.minhasobras;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ObraAdapter extends ArrayAdapter<Obra> {

    public ObraAdapter(Context context, List<Obra> obras) {
        super(context, 0, obras);
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_obra, parent, false);
        }

        Obra obra = getItem(position);

        TextView tvNome = convertView.findViewById(R.id.tvNome);
        TextView tvArtista = convertView.findViewById(R.id.tvArtista);
        TextView tvEstilo = convertView.findViewById(R.id.tvEstilo);
        TextView tvTipo = convertView.findViewById(R.id.tvTipo);

        // 1. Busca o Artista no banco de dados usando o artistaId da Obra
        AppDatabase db = AppDatabase.getInstance(getContext());
        Artista artista = db.artistaDao().getById(obra.getArtistaId());

        // 2. Garante que o nome não fique vazio se der algum erro
        String nomeDoArtista = (artista != null) ? artista.getNome() : "Desconhecido";

        tvNome.setText(obra.getNome());
        // 3. Usa a variável nomeDoArtista em vez do antigo getArtista()
        tvArtista.setText(getContext().getString(R.string.label_artist, nomeDoArtista));
        tvEstilo.setText(getContext().getString(R.string.label_style, obra.getEstilo()));
        tvTipo.setText(getContext().getString(R.string.label_type, obra.getTipo()));

        return convertView;
    }
}