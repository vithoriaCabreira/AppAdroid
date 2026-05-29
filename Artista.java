package br.edu.utfpr.vithoriacabreira.minhasobras;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "artistas")
public class Artista {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;

    public Artista(String nome) {
        this.nome = nome;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    // O toString é obrigatório para o Spinner mostrar o nome na tela e não o endereço de memória
    @Override
    public String toString() {
        return nome;
    }
}