package pl.rutaz.bookstracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.rutaz.bookstracker.db.entities.Book

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {

        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getDatabase(context: Context): BookDatabase? {

            val temp = INSTANCE
            if (temp != null) {
                return temp
            }

            synchronized(this) {
                if (INSTANCE == null) {
                    val inst =
                        Room.databaseBuilder(context.applicationContext, BookDatabase::class.java, "BookDatabase.db")
                            .build()
                    INSTANCE = inst
                }
                return INSTANCE
            }

        }
    }
}