package com.example.ioweyou.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ioweyou.dao.ExpensesDao
import com.example.ioweyou.dao.UserDao
import com.example.ioweyou.models.Expenses
import com.example.ioweyou.models.User

@Database(entities = [User::class, Expenses::class], version = 5)
@TypeConverters(Converter::class)
abstract class IoweYouDatabase : RoomDatabase() {

    abstract fun getUser(): UserDao

    abstract fun getExpenses(): ExpensesDao


    companion object {

        //creating migration to update db changes in previous apps
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user_table ADD COLUMN password TEXT default 123 NOT NULL")
                database.execSQL("ALTER TABLE user_table ADD COLUMN gender TEXT")
                database.execSQL("ALTER TABLE user_table ADD COLUMN age INTEGER")
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE expenses ADD COLUMN paidByName TEXT")
                database.execSQL("ALTER TABLE expenses ADD COLUMN youOwe TEXT")
                database.execSQL("ALTER TABLE expenses ADD COLUMN youGetBack TEXT")
                database.execSQL("ALTER TABLE expenses ADD COLUMN splitWith TEXT")
            }
        }

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE expenses ADD COLUMN youOwe TEXT")
                database.execSQL("ALTER TABLE expenses ADD COLUMN youGetBack TEXT")
                database.execSQL("ALTER TABLE expenses ADD COLUMN splitWith TEXT")
            }
        }

        private val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE expenses ADD COLUMN isByYou INTEGER DEFAULT(0)")
            }
        }

        //writes to this field are immediately made visible to other threads.
        @Volatile
        private var INSTANCE: IoweYouDatabase? = null

        fun getDatabase(context: Context): IoweYouDatabase {
            return INSTANCE ?: synchronized(this) {
                return Room.databaseBuilder(
                    context.applicationContext,
                    IoweYouDatabase::class.java,
                    "IoweYouDB"
                )
                    .createFromAsset("user.db")
                    .addMigrations(MIGRATION_4_5)
                    .build()
            }
        }
    }
}