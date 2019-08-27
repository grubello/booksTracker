package pl.rutaz.bookstracker.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.rutaz.bookstracker.R
import pl.rutaz.bookstracker.db.entities.Book

class BookRecyclerAdapter(private val clickListener: BookViewHolderClickListener) :
    RecyclerView.Adapter<BookViewHolder>() {

    var bookList: List<Book> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book: Book = bookList[position]
        holder.populate(book, clickListener)
    }

}