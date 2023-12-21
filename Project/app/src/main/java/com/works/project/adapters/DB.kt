package com.works.project.adapters

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DB ( val context: Context) : SQLiteOpenHelper( context, "project.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val likesSql = "CREATE TABLE \"likes\" (\n" +
                "\t\"lid\"\tINTEGER,\n" +
                "\t\"pid\"\tINTEGER UNIQUE,\n" +
                "\tPRIMARY KEY(\"lid\" AUTOINCREMENT)\n" +
                ");"

        if (db == null) {
            Toast.makeText( context, "SQL File Permisson Fail!", Toast.LENGTH_SHORT).show()
        }
        db?.let {
            it.execSQL(likesSql)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val likesSql = "drop table if exists likes"
        db?.execSQL(likesSql)
        onCreate(db)
    }


}