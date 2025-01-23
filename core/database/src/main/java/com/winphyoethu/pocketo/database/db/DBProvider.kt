package com.winphyoethu.pocketo.database.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.Calendar

object DBProvider {

    fun create(context: Context): PocketoDatabase {
        return Room.databaseBuilder(context, PocketoDatabase::class.java, "pocketo_database")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    createDefaultCategoryAfterDbCreation(db)
                }
            }).build()
    }

    fun createDefaultCategoryAfterDbCreation(db: SupportSQLiteDatabase) {
        db.execSQL("INSERT INTO category (name,created_at) VALUES('Rent', ${Calendar.getInstance().timeInMillis})")
        db.execSQL("INSERT INTO category (name,created_at) VALUES('Bill', ${Calendar.getInstance().timeInMillis})")
        db.execSQL("INSERT INTO category (name,created_at) VALUES('Grocery', ${Calendar.getInstance().timeInMillis})")
        db.execSQL("INSERT INTO category (name,created_at) VALUES('Eat Out', ${Calendar.getInstance().timeInMillis})")
        db.execSQL("INSERT INTO category (name,created_at) VALUES('Subscription', ${Calendar.getInstance().timeInMillis})")
        db.execSQL("INSERT INTO category (name,created_at) VALUES('Gift', ${Calendar.getInstance().timeInMillis})")
        db.execSQL("INSERT INTO category (name,created_at) VALUES('Coffee', ${Calendar.getInstance().timeInMillis})")
        db.execSQL("INSERT INTO category (name,created_at) VALUES('Insurance', ${Calendar.getInstance().timeInMillis})")
        db.execSQL("INSERT INTO category (name,created_at) VALUES('Clothing', ${Calendar.getInstance().timeInMillis})")
        db.execSQL("INSERT INTO category (name,created_at) VALUES('Top Up', ${Calendar.getInstance().timeInMillis})")
        db.execSQL("INSERT INTO category (name,created_at) VALUES('ATM', ${Calendar.getInstance().timeInMillis})")
        db.execSQL("INSERT INTO category (name,created_at) VALUES('Other', ${Calendar.getInstance().timeInMillis})")

        db.execSQL("INSERT INTO currency (name,code,symbol,created_at) VALUES('Australian Dollar', 'AUD', '$', ${Calendar.getInstance().timeInMillis})")
        db.execSQL("INSERT INTO currency (name,code,symbol,created_at) VALUES('US Dollar', 'USD', '$', ${Calendar.getInstance().timeInMillis})")
        db.execSQL("INSERT INTO currency (name,code,symbol,created_at) VALUES('Thai Baht', 'THB', '฿', ${Calendar.getInstance().timeInMillis})")
        db.execSQL("INSERT INTO currency (name,code,symbol,created_at) VALUES('Myanmar Kyat', 'MMK', 'K', ${Calendar.getInstance().timeInMillis})")
        db.execSQL("INSERT INTO currency (name,code,symbol,created_at) VALUES('Singapore Dollar', 'SGD', 'S$', ${Calendar.getInstance().timeInMillis})")
    }

}