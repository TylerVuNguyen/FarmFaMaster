package com.appveg.farmfamily.ui.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.appveg.farmfamily.ui.login.User

class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "smartfarm"
        /*table*/
        private val TABLE_USERS = "users"
        private val TABLE_ROLES = "roles"
        /*users*/
        private val COLUMN_USER_ID = "user_id"
        private val COLUMN_USER_EMAIL = "email"
        private val COLUMN_USER_NAME = "user_name"
        private val COLUMN_USER_FULL_NAME = "full_name"
        private val COLUMN_USER_PASSWORD = "password"
        private val COLUMN_USER_GENDER = "gender"
        private val COLUMN_USER_STATUS = "status"
        private val COLUMN_USER_CREATEDBY = "created_by"
        private val COLUMN_USER_CREATEDDATE = "created_date"

    }
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS)
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_ROLES)
        onCreate(db)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_USERS_TABLE = ("CREATE TABLE " + TABLE_USERS + " (user_id INTEGER PRIMARY KEY AUTOINCREMENT,user_name VARCHAR(50),full_name VARCHAR(50)," +
                "email VARCHAR(50),password VARCHAR(50),gender VARCHAR(10),status INTEGER,role_id INTEGER,created_by VARCHAR(50)," +
                "created_date VARCHAR(50),updated_by VARCHAR(50),updated_date VARCHAR(50),deleted_by VARCHAR(50),deleted_date VARCHAR(50),deleted_flag INTEGER)")
        val CREATE_ROLES_TABLE = ("CREATE TABLE " + TABLE_ROLES + " (role_id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(50),code VARCHAR(50),created_by VARCHAR(50)," +
                "created_date VARCHAR(50),updated_by VARCHAR(50),updated_date VARCHAR(50),deleted_by VARCHAR(50),deleted_date VARCHAR(50),deleted_flag INTEGER)")
        val INSERT_ROLES_ITEM = ("INSERT INTO roles VALUES(null,'admin','admin','vu',null,null,null,null,null,1)")
        val INSERT_USERS_ITEM = ("INSERT INTO users VALUES(null,'admin','NGUYEN HOANG VU','hvu3011@gmail.com','admin','1','active',1,'vu',null,null,null,null,null,1)")
        db?.execSQL(CREATE_USERS_TABLE)
        db?.execSQL(CREATE_ROLES_TABLE)
        db?.execSQL(INSERT_ROLES_ITEM)
        db?.execSQL(INSERT_USERS_ITEM)
    }

    //exec query
    fun QueryData(sql : String) : Unit {
        var database :SQLiteDatabase = writableDatabase
        database.execSQL(sql)
    }
    // get data
    fun getData(sql : String) : Cursor {
        var database: SQLiteDatabase = readableDatabase
        return database.rawQuery(sql, null)
    }
    /*----------------------------------------------LOGIN-------------------------------------------------*/
    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    fun getAllUser(): List<User> {

        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID, COLUMN_USER_EMAIL, COLUMN_USER_NAME, COLUMN_USER_FULL_NAME, COLUMN_USER_PASSWORD)

        // sorting orders
        val sortOrder = "$COLUMN_USER_NAME ASC"
        val userList = ArrayList<User>()

        val db = this.readableDatabase

        // query the user table
        val cursor = db.query(TABLE_USERS, //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder)         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val user = User(id = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)).toInt(),
                    userName = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)),
                    fullName = cursor.getString(cursor.getColumnIndex(COLUMN_USER_FULL_NAME)),
                    email = cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)),
                    password = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)),
                    gender = cursor.getString(cursor.getColumnIndex(COLUMN_USER_GENDER)),
                    status = cursor.getString(cursor.getColumnIndex(COLUMN_USER_STATUS)).toInt(),
                    createdBy = cursor.getString(cursor.getColumnIndex(COLUMN_USER_CREATEDBY)),
                    createdDate = cursor.getString(cursor.getColumnIndex(COLUMN_USER_CREATEDDATE))
                )
                userList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return userList
    }
    /**
     * This method is to create user record
     *
     * @param user
     */
    fun addUser(user: User) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.userName)
        values.put(COLUMN_USER_CREATEDBY,user.fullName)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)
        values.put(COLUMN_USER_GENDER,user.gender)
        values.put(COLUMN_USER_CREATEDBY, user.createdBy)
        values.put(COLUMN_USER_CREATEDDATE,user.createdDate)

        // Inserting Row
        db.insert(TABLE_USERS, null, values)
        db.close()
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    fun updateUser(user: User) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.userName)
        values.put(COLUMN_USER_CREATEDBY,user.fullName)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)
        values.put(COLUMN_USER_GENDER,user.gender)

        // updating row
        db.update(TABLE_USERS, values, "$COLUMN_USER_ID = ?",
            arrayOf(user.id.toString()))
        db.close()
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    fun deleteUser(user: User) {

        val db = this.writableDatabase
        // delete user record by id
        db.delete(TABLE_USERS, "$COLUMN_USER_ID = ?",
            arrayOf(user.id.toString()))
        db.close()

    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    fun checkUser(email: String): Boolean {

        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase

        // selection criteria
        val selection = "$COLUMN_USER_EMAIL = ?"

        // selection argument
        val selectionArgs = arrayOf(email)

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        val cursor = db.query(TABLE_USERS, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order


        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0) {
            return true
        }

        return false
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    fun checkUser(email: String, password: String): Boolean {

        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)

        val db = this.readableDatabase

        // selection criteria
        val selection = "$COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ?"

        // selection arguments
        val selectionArgs = arrayOf(email, password)

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        val cursor = db.query(TABLE_USERS, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null) //The sort order

        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0){
            return true
        }
        return false

    }

    /*----------------------------------------------QUANTITY-------------------------------------------------*/
    //method for saving records in database
}