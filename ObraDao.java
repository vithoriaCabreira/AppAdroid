package br.edu.utfpr.vithoriacabreira.minhasobras;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface ObraDao {
    @Insert
    void insert(Obra obra);

    @Update
    void update(Obra obra);

    @Delete
    void delete(Obra obra);

    @Query("SELECT * FROM obras")
    List<Obra> getAll();

    @Query("SELECT * FROM obras ORDER BY nome ASC")
    List<Obra> getAllSorted();
}