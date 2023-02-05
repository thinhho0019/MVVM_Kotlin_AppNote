package vn.edu.stu.noteappmvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import vn.edu.stu.noteappmvvm.model.Notes

@Database(entities = [Notes::class], version = 1)
abstract class noteDB: RoomDatabase() {
    abstract  fun getNoteDao():NoteDao
    companion object{
        @Volatile
        private var instance :noteDB?=null
        private val LOCK = Any();
        operator fun invoke(context:Context) = instance ?: synchronized(LOCK){ //if instance null thi tra ve dong bo lock
            instance?:
            createDatabase(context).also {
                instance = it
            }
        }
        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            noteDB::class.java,
            "nore_db").build();
    }
}