package pl.rutaz.bookstracker.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_add_book.*
import pl.rutaz.bookstracker.R
import pl.rutaz.bookstracker.Utils
import pl.rutaz.bookstracker.db.entities.Book
import pl.rutaz.bookstracker.viewmodel.BookListViewModel

class AddBookActivity : AppCompatActivity() {

    private val viewModel : BookListViewModel by lazy {
        ViewModelProvider(this).get(BookListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        addBookButton.setOnClickListener {
            if (validateInputs()) {
                viewModel.insert(getBookFromTypedData())
                finish()
            }
        }
    }

    private fun validateInputs() : Boolean{

        var success  = false

        when (Utils.validateInputs(addBookTitleEditText,
            addBookAuthorEditText,
            addBookIsbnEditText,
            addBookNumOfPagesEditText,
            addBookRateEditText)){

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

    private fun getBookFromTypedData() : Book {

        val title = addBookTitleEditText.text.toString()
        val author = addBookAuthorEditText.text.toString()
        val isbn = addBookIsbnEditText.text.toString()
        val numOfPages = Integer.valueOf(addBookNumOfPagesEditText.text.toString())
        val rating = Integer.valueOf(addBookRateEditText.text.toString())

        return Book(title,author,isbn,numOfPages,rating)
    }
}
