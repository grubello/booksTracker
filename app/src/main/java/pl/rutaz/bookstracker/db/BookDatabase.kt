package pl.rutaz.bookstracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.rutaz.bookstracker.db.entities.Book

@Database(entities = [Book::class], version = 1)
abstract class BookDatabase : RoomDatabase(){

    abstract fun bookDao(): BookDao

    companion object{

        @Volatile
        private var INSTANCE : BookDatabase? = null

        fun getDatabase(context: Context) : BookDatabase {

            if (INSTANCE != null) {
                return INSTANCE as BookDatabase
            }

            synchronized(this){
                val inst = Room.databaseBuilder(context.applicationContext, BookDatabase::class.java, "BookDatabase.db")
                    .build()
                INSTANCE = inst
                return inst
            }

        }
    }
}