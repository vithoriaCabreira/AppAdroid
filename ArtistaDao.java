package br.edu.utfpr.vithoriacabreira.minhasobras;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface ArtistaDao {
    @Insert
    void insert(Artista artista);

    @Update
    void update(Artista artista);

    @Delete
    void delete(Artista artista);

    @Query("SELECT * FROM artistas ORDER BY nome ASC")
    List<Artista> getAll();

    @Query("SELECT * FROM artistas WHERE id = :id LIMIT 1")
    Artista getById(int id);
}