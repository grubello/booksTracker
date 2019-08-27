package pl.rutaz.bookstracker.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_edit_delete_book.*
import kotlinx.android.synthetic.main.content_add_edit_delete.*
import pl.rutaz.bookstracker.R
import pl.rutaz.bookstracker.ValidateUtils
import pl.rutaz.bookstracker.db.entities.Book
import pl.rutaz.bookstracker.viewmodel.BookListViewModel

class EditDeleteBookActivity : AppCompatActivity() {

    private val viewModel: BookListViewModel by lazy {
        ViewModelProvider(this).get(BookListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_delete_book)
        pageTitleTextView.text = getString(R.string.bookDetailsTitle)

        val book = intent.extras?.getSerializable(ValidateUtils.EXTRAS) as Book
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

    private fun setUpInputs(book: Book) {
        bookTitleEditText.setText(book.title)
        bookAuthorEditText.setText(book.author)
        bookIsbnEditText.setText(book.isbn)
        bookNumOfPagesEditText.setText(book.numOfPages.toString())
        bookRateEditText.setText(book.rating.toString())
    }

    private fun validateInputs(): Boolean {

        var success = false

        when (ValidateUtils.validateInputs(
            bookTitleEditText.text.toString(),
            bookAuthorEditText.text.toString(),
            bookIsbnEditText.text.toString(),
            bookNumOfPagesEditText.text.toString(),
            bookRateEditText.text.toString()
        )) {

            ValidateUtils.VALIDATE_SUCCESS -> success = true
            ValidateUtils.ISBN_INVALID -> {
                Toast.makeText(this, "ISBN is not valid", Toast.LENGTH_LONG).show()
            }
            ValidateUtils.INPUT_EMPTY -> {
                Toast.makeText(this, "Typed data must not be empty", Toast.LENGTH_LONG).show()
            }
            ValidateUtils.RATING_INVALID -> {
                Toast.makeText(this, "Rating must be in 1-5 range", Toast.LENGTH_LONG).show()
            }
            ValidateUtils.NUM_OF_PAGES_INVALID -> {
                Toast.makeText(this, "Number of pages must not be null and greater than 0", Toast.LENGTH_LONG).show()
            }
        }
        return success
    }

    private fun getUpdatedBook(book: Book): Book {
        book.title = bookTitleEditText.text.toString()
        book.author = bookAuthorEditText.text.toString()
        book.isbn = bookIsbnEditText.text.toString()
        book.numOfPages = bookNumOfPagesEditText.text.toString().toInt()
        book.rating = bookRateEditText.text.toString().toInt()

        return book
    }
}
