package com.astra.koksharov.organizerdb

import android.net.Uri
import android.content.UriMatcher

public val VERSION = 1

//region DBs
public val DB_NAME = "organizer.db"
//endregion

//region Tables
public val TABLE_TASKS = "tasks" // Table's name
//endregion

//region Fields
public val FIELD_TASK_ID = "_id" // The columns in the table
public val FIELD_TASK_NAME = "name"
public val FIELD_TASK_TYPE = "type"
public val FIELD_TASK_DATE_BEGIN = "date_begin"
public val FIELD_TASK_DATE_END = "date_end"
//endregion

//region Projections
public val PROJECTION_TASKS = arrayOf(FIELD_TASK_ID, FIELD_TASK_NAME, FIELD_TASK_TYPE, FIELD_TASK_DATE_BEGIN, FIELD_TASK_DATE_END)
//endregion


//region Auth
public val AUTHORITY = "com.astra.koksharov.organizerdb.OrganizerContentProvider"
//endregion

//region Tags
public val PROVIDER_TASKS = Uri.parse("content://$AUTHORITY/$TABLE_TASKS")
//endregion

public val URI_TABLE_TASKS = 1
//endregion

//region SQL CREATE QUERY
// A string that defines the SQL statement for creating a table
public val SQL_CREATE_MAIN : String =
    "CREATE TABLE ${TABLE_TASKS} " +
            "( ${FIELD_TASK_ID} INTEGER PRIMARY KEY, " +
            "${FIELD_TASK_NAME} TEXT, " +
            "${FIELD_TASK_TYPE} INTEGER, " +
            "${FIELD_TASK_DATE_BEGIN} TEXT, " +
            "${FIELD_TASK_DATE_END} TEXT )";
//endregion

