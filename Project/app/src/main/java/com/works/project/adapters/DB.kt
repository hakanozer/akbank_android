package com.works.project.adapters

import android.content.ContentValues
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


    fun addLike( pid: Long ) : Long {
        val db = this.writableDatabase
        var returnID:Long = -1
        val values = ContentValues()
        values.put("pid", pid)
        returnID = db.insert("likes", null, values)
        db.close()
        return returnID
    }

    fun removeLike (pid: Long) : Int {
        val db = this.writableDatabase
        var status = db.delete("likes", "pid = $pid", null)
        db.close()
        return status
    }

    fun singleLike( pid: Long ) : Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("select * from likes where pid = $pid", null)
        val status = cursor.moveToNext()
        db.close()
        return status
    }

    fun allLikes () : List<Long> {
        val db = this.readableDatabase
        val arr = mutableListOf<Long>()
        val cursor = db.query("likes", null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val pid = cursor.getLong(1)
            arr.add(pid)
        }
        db.close()
        return arr
    }


}