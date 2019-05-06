package com.astra.koksharov.organizerdb

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.database.sqlite.SQLiteDatabase
import android.util.Log


class OrganizerContentProvider : ContentProvider() {

    /*
     * Defines a handle to the database helper object. The MainDatabaseHelper class is defined
     * in a following snippet.
     */
    private var mOpenHelper: DBHelper? = null

    // Holds the database object
    private var db: SQLiteDatabase? = null

    //region URI Matcher
    private val sURIMatcher = UriMatcher(UriMatcher.NO_MATCH)

    override fun onCreate(): Boolean {

        sURIMatcher.addURI(AUTHORITY, TABLE_TASKS, URI_TABLE_TASKS);

        /*
         * Creates a new helper object. This method always returns quickly.
         * Notice that the database itself isn't created or opened
         * until SQLiteOpenHelper.getWritableDatabase is called
         */
        mOpenHelper = DBHelper(
            context // the application context
        )
        db = mOpenHelper?.getWritableDatabase();

        return true
    }


    // Implements the provider's insert method
    override public fun insert(uri : Uri, values : ContentValues) : Uri? {
        Log.d("insert in database: ", getTable(uri)!! + "")
        val id = db?.insert(getTable(uri), null, values)
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        Log.d("reading from database: ", getTable(uri) + "");
        return db?.query(getTable(uri), projection, selection, selectionArgs, null, null, sortOrder);
    }
    /**
    * selection: String?  whereClause
     *  selectionArgs: Array<String>? whereArgs
    **/
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        Log.d("update in database: ", getTable(uri)!! + "")
        return db?.update(getTable(uri), values, selection, selectionArgs)!!
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        Log.d("delete in database: ", getTable(uri)!! + "")
        return db?.delete(getTable(uri), selection, selectionArgs)!!
    }

    override fun getType(uri: Uri): String? {
        return uri.toString()
    }

    private fun getTable(uri : Uri) : String? {
        var table: String? = null
        when(sURIMatcher.match(uri)) {
            URI_TABLE_TASKS -> {
                Log.d(URI_TABLE_TASKS.toString(), TABLE_TASKS)
                table = TABLE_TASKS
            }
        }
        return table
    }

}