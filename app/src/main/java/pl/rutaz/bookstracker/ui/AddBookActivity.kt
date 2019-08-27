package pl.rutaz.bookstracker.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_add_book.*
import kotlinx.android.synthetic.main.content_add_edit_delete.*
import pl.rutaz.bookstracker.R
import pl.rutaz.bookstracker.ValidateUtils
import pl.rutaz.bookstracker.db.entities.Book
import pl.rutaz.bookstracker.viewmodel.BookListViewModel

class AddBookActivity : AppCompatActivity() {

    private val viewModel: BookListViewModel by lazy {
        ViewModelProvider(this).get(BookListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        pageTitleTextView.text = getString(R.string.addBookTitle)

        addBookButton.setOnClickListener {
            if (validateInputs()) {
                viewModel.insert(getBookFromTypedData())
                finish()
            }
        }
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

    private fun getBookFromTypedData(): Book {

        val title = bookTitleEditText.text.toString()
        val author = bookAuthorEditText.text.toString()
        val isbn = bookIsbnEditText.text.toString()
        val numOfPages = Integer.valueOf(bookNumOfPagesEditText.text.toString())
        val rating = Integer.valueOf(bookRateEditText.text.toString())

        return Book(title, author, isbn, numOfPages, rating)
    }
}
