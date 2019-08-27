package pl.rutaz.bookstracker.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "books")
data class Book(
    var title: String,
    var author: String,
    var isbn: String,
    var numOfPages: Int,
    var rating: Int
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
