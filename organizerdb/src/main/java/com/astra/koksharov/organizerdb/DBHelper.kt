package com.astra.koksharov.organizerdb

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build

class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION) {
//    mOpenHelper = new MainDatabaseHelper(
//    getContext(),        // the application context
//    DBNAME,              // the name of the database)
//    null,                // uses the default SQLite cursor
//    1                    // the version number
//    );

    /*
     * Creates the data repository. This is called when the provider attempts to open the
     * repository and SQLite reports that it doesn't exist.
     */
    override fun onCreate(db: SQLiteDatabase) {
        // Creates the main table
        db.execSQL(SQL_CREATE_MAIN)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
