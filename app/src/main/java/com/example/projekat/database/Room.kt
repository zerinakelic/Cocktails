package com.example.projekat.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ModelDao {
    @Query("select * from databasemodel")
    fun getItemsFromDatabase(): LiveData<List<DatabaseModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(drinks: List<DatabaseModel>)

    @Query("Delete from DatabaseModel")
    fun deleteAll()
}

@Database(entities = [DatabaseModel::class], version = 1, exportSchema = false)
abstract class Baza : RoomDatabase() {
    abstract val modelDao: ModelDao
}

private lateinit var INSTANCE: Baza

fun getDatabase(context: Context): Baza {
    synchronized(Baza::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                Baza::class.java,
                "mojaBaza"
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}