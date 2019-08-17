package pl.rutaz.bookstracker.db

import androidx.lifecycle.LiveData
import androidx.room.*
import pl.rutaz.bookstracker.db.entities.Book

@Dao
interface BookDao{

    @Query("SELECT * from books ORDER BY id ASC")
    fun getBooks(): LiveData<List<Book>>

    @Insert
    suspend fun insert(book: Book)

    @Delete
    suspend fun delete(book: Book)

    @Update
    suspend fun update(book: Book)
}