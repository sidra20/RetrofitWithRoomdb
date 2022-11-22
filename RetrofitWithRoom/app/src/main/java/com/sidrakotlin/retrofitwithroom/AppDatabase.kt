package com.sidrakotlin.retrofitwithroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sidrakotlin.retrofitwithroom.model.PostsItem

@Database(entities = [PostsItem::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun postsDao(): PostsDao

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (instance == null) {

                synchronized(this)
                {


                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "postsdb"
                    )
                        .build()
                }
            }
            return instance!!
        }

    }
}