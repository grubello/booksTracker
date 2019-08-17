package pl.rutaz.bookstracker.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import pl.rutaz.bookstracker.db.BookDao
import pl.rutaz.bookstracker.db.entities.Book

class BookRepository (private val bookDao: BookDao) {

    fun getBooks(): LiveData<List<Book>> {
        return bookDao.getBooks()
    }

    @WorkerThread
    suspend fun insert(book: Book) {
        bookDao.insert(book)
    }

    @WorkerThread
    suspend fun delete(book: Book) {
        bookDao.delete(book)
    }

    @WorkerThread
    suspend fun update(book: Book) {
        bookDao.update(book)
    }
}