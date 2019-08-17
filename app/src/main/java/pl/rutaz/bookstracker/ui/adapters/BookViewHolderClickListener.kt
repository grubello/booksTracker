package pl.rutaz.bookstracker.ui.adapters

import pl.rutaz.bookstracker.db.entities.Book

interface BookViewHolderClickListener{
   fun onClick(book : Book)
}