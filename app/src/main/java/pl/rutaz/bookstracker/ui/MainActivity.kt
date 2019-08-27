package pl.rutaz.bookstracker.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import pl.rutaz.bookstracker.R
import pl.rutaz.bookstracker.ValidateUtils
import pl.rutaz.bookstracker.db.entities.Book
import pl.rutaz.bookstracker.ui.adapters.BookRecyclerAdapter
import pl.rutaz.bookstracker.ui.adapters.BookViewHolderClickListener
import pl.rutaz.bookstracker.viewmodel.BookListViewModel

class MainActivity : BookViewHolderClickListener, AppCompatActivity() {

    private val viewModel: BookListViewModel by lazy {
        ViewModelProvider(this).get(BookListViewModel::class.java)
    }

    private val adapter: BookRecyclerAdapter by lazy {
        BookRecyclerAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        bookListRecyclerView.layoutManager = LinearLayoutManager(this)
        bookListRecyclerView.adapter = adapter

        viewModel.bookList.observe(this, Observer {
            if (it.isNotEmpty()) {
                bookListRecyclerView.visibility = View.VISIBLE
                noResultsTextView.visibility = View.GONE

                adapter.bookList = it
                adapter.notifyDataSetChanged()
            } else {
                bookListRecyclerView.visibility = View.GONE
                noResultsTextView.visibility = View.VISIBLE
            }
        })

        addBookFab.setOnClickListener {
            val intent = Intent(this, AddBookActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(book: Book) {
        val intent = Intent(this, EditDeleteBookActivity::class.java)
        intent.putExtra(ValidateUtils.EXTRAS, book)
        startActivity(intent)
    }
}
