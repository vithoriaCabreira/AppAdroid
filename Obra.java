package br.edu.utfpr.vithoriacabreira.minhasobras;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.time.LocalDate;

@Entity(tableName = "obras",
        foreignKeys = @ForeignKey(entity = Artista.class,
                parentColumns = "id",
                childColumns = "artistaId",
                onDelete = ForeignKey.RESTRICT), // RESTRICT: Impede apagar um artista se ele tiver obras cadastradas
                indices = {@Index("artistaId")})




public class Obra {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nome;
    private String estilo;
    private String tipo;

    // Chave Estrangeira
    private int artistaId;

    // Atributo de Data (Requisito)
    private LocalDate dataAquisicao;

    public Obra(String nome, String estilo, String tipo, int artistaId, LocalDate dataAquisicao) {
        this.nome = nome;
        this.estilo = estilo;
        this.tipo = tipo;
        this.artistaId = artistaId;
        this.dataAquisicao = dataAquisicao;
    }

    public Obra(String nome, String artista, String estilo, String tipo) {
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEstilo() { return estilo; }
    public void setEstilo(String estilo) { this.estilo = estilo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getArtistaId() { return artistaId; }
    public void setArtistaId(int artistaId) { this.artistaId = artistaId; }

    public LocalDate getDataAquisicao() { return dataAquisicao; }
    public void setDataAquisicao(LocalDate dataAquisicao) { this.dataAquisicao = dataAquisicao; }
}