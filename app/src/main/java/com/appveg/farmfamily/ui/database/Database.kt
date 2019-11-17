package com.appveg.farmfamily.ui.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.appveg.farmfamily.ui.login.User
import com.appveg.farmfamily.ui.send.Batch
import com.appveg.farmfamily.ui.send.BatchQtyDetail
import com.appveg.farmfamily.ui.vegetable.Vegetable
import com.appveg.farmfamily.ui.vegetable.VegetableTemp

class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "smartfarm"
        /*table*/
        private val TABLE_USERS = "users"
        private val TABLE_ROLES = "roles"
        private val TABLE_BATCH = "batch"
        private val TABLE_BATCH_DETAIL = "bacth_quantity_detail"
        /*users*/
        private val COLUMN_USER_ID = "user_id"
        private val COLUMN_USER_EMAIL = "email"
        private val COLUMN_USER_NAME = "user_name"
        private val COLUMN_USER_FULL_NAME = "full_name"
        private val COLUMN_USER_PASSWORD = "password"
        private val COLUMN_USER_GENDER = "gender"
        private val COLUMN_USER_STATUS = "status"

        /*common*/
        private val COLUMN_CREATEDBY = "created_by"
        private val COLUMN_CREATEDDATE = "created_date"

        /*batch*/
        private val COLUMN_BATCH_ID = "batch_id"
        private val COLUMN_BATCH_IMAGE = "batch_image"
        private val COLUMN_BATCH_NAME = "batch_name"
        private val COLUMN_BATCH_END_DATE = "the_end_date"
        private val COLUMN_BATCH_TOTAL_QTY = "total_quantity"
        private val COLUMN_BATCH_GARDEN_ID = "garden_id"

        /*batch*/
        private val COLUMN_BATCH_DETAIL_ID = "qty_detail_id"
        private val COLUMN_BATCH_QTY_ID = "qty_id"
        private val COLUMN_BATCH_VEG_NAME = "vegetable_name"
        private val COLUMN_BATCH_VEG_QTY = "vegetable_quantity"

        /*vegeble table*/
        private val TABLE_VEGETABLE = "vegetable"
        private val VEG_ID = "id"
        private val VEG_NAME = "name"
        private val VEG_IMG = "img"//luu link icon

    }
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS)
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_ROLES)
        onCreate(db)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        /*TABLE PROJECT*/
        val CREATE_USERS_TABLE = ("CREATE TABLE " + TABLE_USERS + " (user_id INTEGER PRIMARY KEY AUTOINCREMENT,user_name VARCHAR(50),full_name VARCHAR(50)," +
                "email VARCHAR(50),password VARCHAR(50),gender VARCHAR(10),status INTEGER,role_id INTEGER,created_by VARCHAR(50)," +
                "created_date VARCHAR(50),updated_by VARCHAR(50),updated_date VARCHAR(50),deleted_by VARCHAR(50),deleted_date VARCHAR(50),deleted_flag INTEGER)")
        val CREATE_ROLES_TABLE = ("CREATE TABLE " + TABLE_ROLES + " (role_id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(50),code VARCHAR(50),created_by VARCHAR(50)," +
                "created_date VARCHAR(50),updated_by VARCHAR(50),updated_date VARCHAR(50),deleted_by VARCHAR(50),deleted_date VARCHAR(50),deleted_flag INTEGER)")
        val CREATE_BATCH_TABLE  = ("CREATE TABLE " + TABLE_BATCH + " (batch_id INTEGER PRIMARY KEY AUTOINCREMENT,batch_image VARCHAR(100),batch_name VARCHAR(100),the_end_date VARCHAR(15)," +
                "total_quantity VARCHAR(100),garden_id INTEGER,created_by VARCHAR(50),created_date VARCHAR(50),updated_by VARCHAR(50),updated_date VARCHAR(50),deleted_by VARCHAR(50)," +
                "deleted_date VARCHAR(50),deleted_flag INTEGER)")
        val CREATE_QUANTITY_DETAIL_TABLE  = ("CREATE TABLE " + TABLE_BATCH_DETAIL + "(qty_detail_id INTEGER PRIMARY KEY AUTOINCREMENT,vegetable_name VARCHAR(100)," +
                "vegetable_quantity VARCHAR(100),qty_id INTEGER,created_by VARCHAR(50),created_date VARCHAR(50),updated_by VARCHAR(50),updated_date VARCHAR(50),deleted_by VARCHAR(50)," +
                "deleted_date VARCHAR(50),deleted_flag INTEGER)")

        val CREATE_VEGETABLE_TABLE = ("CREATE TABLE " + TABLE_VEGETABLE + "(veg_id INTEGER PRIMARY KEY AUTOINCREMENT,veg_name VARCHAR(100)," +
                "veg_code VARCHAR(50), veg_image VARCHAR(100),created_by VARCHAR(50),created_date VARCHAR(50),updated_by VARCHAR(50),updated_date VARCHAR(50),deleted_by VARCHAR(50)," +
                "deleted_date VARCHAR(50),deleted_flag INTEGER)")


        /*INSERT DATA*/
        val INSERT_ROLES_ITEM = ("INSERT INTO roles VALUES(null,'admin','admin','vu',null,null,null,null,null,1)")
        val INSERT_USERS_ITEM = ("INSERT INTO users VALUES(null,'admin','NGUYEN HOANG VU','hvu3011@gmail.com','admin','1','active',1,'vu',null,null,null,null,null,1)")
        db?.execSQL(CREATE_USERS_TABLE)
        db?.execSQL(CREATE_ROLES_TABLE)
        db?.execSQL(INSERT_ROLES_ITEM)
        db?.execSQL(INSERT_USERS_ITEM)
        db?.execSQL(CREATE_BATCH_TABLE)
        db?.execSQL(CREATE_QUANTITY_DETAIL_TABLE)
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
                    createdBy = cursor.getString(cursor.getColumnIndex(COLUMN_CREATEDBY)),
                    createdDate = cursor.getString(cursor.getColumnIndex(COLUMN_CREATEDDATE))
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
        values.put(COLUMN_CREATEDBY,user.fullName)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)
        values.put(COLUMN_USER_GENDER,user.gender)
        values.put(COLUMN_CREATEDBY, user.createdBy)
        values.put(COLUMN_CREATEDDATE,user.createdDate)

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
        values.put(COLUMN_CREATEDBY,user.fullName)
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
    /**
     * This method to insert data
     *
     * @param email
     * @param password
     * @return true/false
     */
    fun addBatch(batch: Batch):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_BATCH_ID, batch.batchId)
        contentValues.put(COLUMN_BATCH_IMAGE, batch.batchImage)
        contentValues.put(COLUMN_BATCH_NAME,batch.batchName)
        contentValues.put(COLUMN_BATCH_END_DATE,batch.theEndDate)
        contentValues.put(COLUMN_BATCH_TOTAL_QTY,batch.totalQuantity)
        contentValues.put(COLUMN_BATCH_GARDEN_ID,batch.gardenId)
        contentValues.put(COLUMN_CREATEDBY, batch.createdBy)
        contentValues.put(COLUMN_CREATEDDATE,batch.createdDate)

        // Inserting Row
        val success = db.insert(TABLE_BATCH, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    /**
     * This method to insert data for batch detail
     *
     * @param email
     * @param password
     * @return true/false
     */
    fun addBatchDetail(batchQtyDetail: BatchQtyDetail):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_BATCH_DETAIL_ID, batchQtyDetail.qtyDetailId)
        contentValues.put(COLUMN_BATCH_QTY_ID, batchQtyDetail.qtyId)
        contentValues.put(COLUMN_BATCH_VEG_NAME,batchQtyDetail.vegetableName)
        contentValues.put(COLUMN_BATCH_VEG_QTY,batchQtyDetail.vegetableQuantity)
        contentValues.put(COLUMN_CREATEDBY, batchQtyDetail.createdBy)
        contentValues.put(COLUMN_CREATEDDATE,batchQtyDetail.createdDate)

        // Inserting Row
        val success = db.insert(TABLE_BATCH_DETAIL, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    /*----------------------------------------------VEGETABLE-------------------------------------------------*/
    //method for saving records in database
//them rau
    fun addVeg(veg: Vegetable): Boolean {
        //1: Them Phong vao DB
        val value = ContentValues()
        value.put(VEG_NAME, veg.HandleName)
        value.put(VEG_IMG, veg.HandleImageVeg)
//        if (veg.handleIsActive) {
//            value.put(ROOM_ISACTIVE, 1)
//        } else {
//            value.put(ROOM_ISACTIVE, 0)
//        }

        val db = this.writableDatabase
        //so dong thanh cong
        val _success = db.insert(TABLE_VEGETABLE, null, value)
        db.close()
        Log.v("room_db", "number row add veg success: $_success")
        return (Integer.parseInt("$_success") != -1)
    }
}