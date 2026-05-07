package br.edu.utfpr.vithoriacabreira.minhasobras;

public class Obra {
    private String nome;
    private String artista;
    private String estilo;
    private String tipo;

    public Obra(String nome, String artista, String estilo, String tipo) {
        this.nome = nome;
        this.artista = artista;
        this.estilo = estilo;
        this.tipo = tipo;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }

    public String getEstilo() { return estilo; }
    public void setEstilo(String estilo) { this.estilo = estilo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    @Override
    public String toString() {
        return nome; // Útil para identificação rápida
    }
}