package pl.rutaz.bookstracker.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_edit_delete_book.*
import pl.rutaz.bookstracker.R
import pl.rutaz.bookstracker.Utils
import pl.rutaz.bookstracker.db.entities.Book
import pl.rutaz.bookstracker.viewmodel.BookListViewModel

class EditDeleteBookActivity : AppCompatActivity() {

    private val viewModel : BookListViewModel by lazy {
        ViewModelProvider(this).get(BookListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_delete_book)

        val book = intent.extras?.getSerializable(Utils.EXTRAS) as Book
        setUpInputs(book)

        saveBookButton.setOnClickListener {
            if (validateInputs()) {
                viewModel.update(getUpdatedBook(book))
                finish()
            }
        }

        removeBookButton.setOnClickListener {
            if (validateInputs()) {
                viewModel.delete(book)
                finish()
            }
        }
    }

    private fun setUpInputs(book:Book){
        editBookTitleEditText.setText(book.title)
        editBookAuthorEditText.setText(book.author)
        editBookIsbnEditText.setText(book.isbn)
        editBookNumOfPagesEditText.setText(book.numOfPages.toString())
        editBookRateEditText.setText(book.rating.toString())
    }

    private fun validateInputs() : Boolean{

        var success  = false

        when (Utils.validateInputs(editBookTitleEditText,
            editBookAuthorEditText,
            editBookIsbnEditText,
            editBookNumOfPagesEditText,
            editBookRateEditText)){

            Utils.VALIDATE_SUCCESS -> success = true
            Utils.ISBN_INVALID -> {
                Toast.makeText(this,"ISBN is not valid",Toast.LENGTH_LONG).show()
            }
            Utils.INPUT_EMPTY -> {
                Toast.makeText(this,"Typed data must not be empty",Toast.LENGTH_LONG).show()
            }
            Utils.RATING_INVALID -> {
                Toast.makeText(this, "Rating must be in 1-5 range", Toast.LENGTH_LONG).show()
            }
            Utils.NUM_OF_PAGES_INVALID -> {
                Toast.makeText(this,"Number of pages must not be null and greater than 0",Toast.LENGTH_LONG).show()
            }
        }
        return success
    }

    private fun getUpdatedBook(book : Book) : Book{
        book.title = editBookTitleEditText.text.toString()
        book.author = editBookAuthorEditText.text.toString()
        book.isbn = editBookIsbnEditText.text.toString()
        book.numOfPages = editBookNumOfPagesEditText.text.toString().toInt()
        book.rating = editBookRateEditText.text.toString().toInt()

        return book
    }
}
