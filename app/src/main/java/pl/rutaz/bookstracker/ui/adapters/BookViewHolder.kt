package pl.rutaz.bookstracker.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_book.view.*
import pl.rutaz.bookstracker.db.entities.Book

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val titleTextView = itemView.bookTitleTextView
    private val authorTextView = itemView.bookAuthorTextView
    private val isbnTextView = itemView.bookIsbnTextView
    private val numOfPagesTextView = itemView.bookNumPagesTextView
    private val rateTextView = itemView.bookRateTextView

    fun populate(book: Book, clickListener: BookViewHolderClickListener) {
        itemView.tag = book
        titleTextView.text = book.title
        authorTextView.text = book.author
        isbnTextView.text = book.isbn
        numOfPagesTextView.text = book.numOfPages.toString()
        rateTextView.text = book.rating.toString()

        itemView.setOnClickListener {
            clickListener.onClick(it.tag as Book)
        }
    }
}