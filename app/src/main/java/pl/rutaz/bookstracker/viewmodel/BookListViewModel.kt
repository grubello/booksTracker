package pl.rutaz.bookstracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import pl.rutaz.bookstracker.db.BookDatabase
import pl.rutaz.bookstracker.db.entities.Book
import pl.rutaz.bookstracker.repository.BookRepository

class BookListViewModel(application: Application) : AndroidViewModel(application){

    private val repository : BookRepository
    val bookList : LiveData<List<Book>>

    init {
        val bookDao = BookDatabase.getDatabase(application).bookDao()
        repository = BookRepository(bookDao)
        bookList = repository.getBooks()
    }

    fun insert(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(book)
        }
    }

    fun delete(book : Book){
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(book)
        }
    }

    fun update(book : Book){
        viewModelScope.launch(Dispatchers.IO){
            repository.update(book)
        }
    }
}