package com.appveg.farmfamily.ui.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.appveg.farmfamily.R
import com.appveg.farmfamily.ui.garden.Garden
import com.appveg.farmfamily.ui.login.User
import com.appveg.farmfamily.ui.send.Batch
import com.appveg.farmfamily.ui.send.BatchCustom
import com.appveg.farmfamily.ui.send.BatchQtyDetail
import com.appveg.farmfamily.ui.vegetable.Vegetable

class Database(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "smartfarm"
        /*table*/
        private val TABLE_USERS = "users"
        private val TABLE_ROLES = "roles"
        private val TABLE_BATCH = "batch"
        private val TABLE_BATCH_DETAIL = "bacth_quantity_detail"
        private val TABLE_VEGETABLE = "vegetable"
        private val TABLE_GARDEN = "garden"

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
        private val COLUMN_UPDATED_BY = "updated_by"
        private val COLUMN_UPDATED_DATE = "updated_date"

        /*batch*/
        private val COLUMN_BATCH_ID = "batch_id"
        private val COLUMN_BATCH_IMAGE = "batch_image"
        private val COLUMN_BATCH_NAME = "batch_name"
        private val COLUMN_BATCH_END_DATE = "the_end_date"
        private val COLUMN_BATCH_TOTAL_QTY = "total_quantity"
        private val COLUMN_BATCH_GARDEN_ID = "garden_id"

        /*batch detail*/
        private val COLUMN_BATCH_DETAIL_ID = "qty_detail_id"
        private val COLUMN_BATCH_QTY_ID = "qty_id"
        private val COLUMN_BATCH_VEG_NAME = "vegetable_name"
        private val COLUMN_BATCH_VEG_QTY = "vegetable_quantity"

        /*vegetable table*/
        private val COLUMN_VEG_ID = "veg_id"
        private val COLUMN_VEG_NAME = "veg_name"
        private val COLUMN_VEG_CODE = "veg_code"
        private val COLUMN_VEG_IMG_BLOB = "veg_image_blob"

        /*garden*/
        private val COLUMN_GARDEN_ID = "garden_id"
        private val COLUMN_GARDEN_NAME = "garden_name"
        private val COLUMN_GARDEN_IMAGE = "garden_image"

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS)
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_ROLES)
        onCreate(db)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        /*TABLE PROJECT*/
        val CREATE_USERS_TABLE =
            ("CREATE TABLE " + TABLE_USERS + " (user_id INTEGER PRIMARY KEY AUTOINCREMENT,user_name VARCHAR(50),full_name VARCHAR(50)," +
                    "email VARCHAR(50),password VARCHAR(50),gender VARCHAR(10),status INTEGER,role_id INTEGER,created_by VARCHAR(50)," +
                    "created_date VARCHAR(50),updated_by VARCHAR(50),updated_date VARCHAR(50),deleted_by VARCHAR(50),deleted_date VARCHAR(50),deleted_flag INTEGER)")
        val CREATE_ROLES_TABLE =
            ("CREATE TABLE " + TABLE_ROLES + " (role_id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(50),code VARCHAR(50),created_by VARCHAR(50)," +
                    "created_date VARCHAR(50),updated_by VARCHAR(50),updated_date VARCHAR(50),deleted_by VARCHAR(50),deleted_date VARCHAR(50),deleted_flag INTEGER)")
        val CREATE_BATCH_TABLE =
            ("CREATE TABLE " + TABLE_BATCH + " (batch_id INTEGER PRIMARY KEY AUTOINCREMENT,batch_image VARCHAR(100),batch_name VARCHAR(100),the_end_date VARCHAR(15)," +
                    "total_quantity VARCHAR(100),garden_id INTEGER,created_by VARCHAR(50),created_date VARCHAR(50),updated_by VARCHAR(50),updated_date VARCHAR(50),deleted_by VARCHAR(50)," +
                    "deleted_date VARCHAR(50),deleted_flag INTEGER)")
        val CREATE_QUANTITY_DETAIL_TABLE =
            ("CREATE TABLE " + TABLE_BATCH_DETAIL + "(qty_detail_id INTEGER PRIMARY KEY AUTOINCREMENT,vegetable_name VARCHAR(100)," +
                    "vegetable_quantity VARCHAR(100),qty_id INTEGER,created_by VARCHAR(50),created_date VARCHAR(50),updated_by VARCHAR(50),updated_date VARCHAR(50),deleted_by VARCHAR(50)," +
                    "deleted_date VARCHAR(50),deleted_flag INTEGER)")

        val CREATE_VEGETABLE_TABLE =
            ("CREATE TABLE " + TABLE_VEGETABLE + "(veg_id INTEGER PRIMARY KEY AUTOINCREMENT,veg_name VARCHAR(100)," +
                    "veg_image_blob BLOB,created_by VARCHAR(50),created_date VARCHAR(50),updated_by VARCHAR(50),updated_date VARCHAR(50),deleted_by VARCHAR(50)," +
                    "deleted_date VARCHAR(50),deleted_flag INTEGER)")

        val CREATE_GARDEN_TABLE =
            ("CREATE TABLE " + TABLE_GARDEN + "(garden_id INTEGER PRIMARY KEY AUTOINCREMENT,garden_name VARCHAR(100)," +
                    "garden_image BLOB,created_by VARCHAR(50),created_date VARCHAR(50),updated_by VARCHAR(50),updated_date VARCHAR(50),deleted_by VARCHAR(50)," +
                    "deleted_date VARCHAR(50),deleted_flag INTEGER)")


        /*INSERT DATA*/
        val INSERT_GARDEN_ITEM =
            ("INSERT INTO garden VALUES(null,'Khu vườn 1','R.drawable.kv2','admin',null,null,null,null,null,1)")
        val INSERT_GARDEN1_ITEM =
            ("INSERT INTO garden VALUES(null,'Khu vườn 2','R.drawable.kv2','admin',null,null,null,null,null,1)")
        val INSERT_ROLES_ITEM =
            ("INSERT INTO roles VALUES(null,'admin','admin','vu',null,null,null,null,null,1)")
        val INSERT_USERS_ITEM =
            ("INSERT INTO users VALUES(null,'admin','NGUYEN HOANG VU','hvu3011@gmail.com','admin','1','active',1,'vu',null,null,null,null,null,1)")
        db?.execSQL(CREATE_USERS_TABLE)
        db?.execSQL(CREATE_ROLES_TABLE)
        db?.execSQL(CREATE_GARDEN_TABLE)
        db?.execSQL(INSERT_ROLES_ITEM)
        db?.execSQL(INSERT_USERS_ITEM)
//        db?.execSQL(INSERT_GARDEN_ITEM)
//        db?.execSQL(INSERT_GARDEN1_ITEM)
        db?.execSQL(CREATE_BATCH_TABLE)
        db?.execSQL(CREATE_QUANTITY_DETAIL_TABLE)
        db?.execSQL(CREATE_VEGETABLE_TABLE)

    }

    //exec query
    fun QueryData(sql: String): Unit {
        var database: SQLiteDatabase = writableDatabase
        database.execSQL(sql)
    }

    // get data
    fun getData(sql: String): Cursor {
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
        val columns = arrayOf(
            COLUMN_USER_ID,
            COLUMN_USER_EMAIL,
            COLUMN_USER_NAME,
            COLUMN_USER_FULL_NAME,
            COLUMN_USER_PASSWORD
        )

        // sorting orders
        val sortOrder = "$COLUMN_USER_NAME ASC"
        val userList = ArrayList<User>()

        val db = this.readableDatabase

        // query the user table
        val cursor = db.query(
            TABLE_USERS, //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder
        )         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val user = User(
                    id = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)).toInt(),
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
        values.put(COLUMN_CREATEDBY, user.fullName)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)
        values.put(COLUMN_USER_GENDER, user.gender)
        values.put(COLUMN_CREATEDBY, user.createdBy)
        values.put(COLUMN_CREATEDDATE, user.createdDate)

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
        values.put(COLUMN_CREATEDBY, user.fullName)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)
        values.put(COLUMN_USER_GENDER, user.gender)

        // updating row
        db.update(
            TABLE_USERS, values, "$COLUMN_USER_ID = ?",
            arrayOf(user.id.toString())
        )
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
        db.delete(
            TABLE_USERS, "$COLUMN_USER_ID = ?",
            arrayOf(user.id.toString())
        )
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
        val cursor = db.query(
            TABLE_USERS, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null
        )  //The sort order


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
        val cursor = db.query(
            TABLE_USERS, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null
        ) //The sort order

        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0) {
            return true
        }
        return false

    }

    /*----------------------------------------------QUANTITY-------------------------------------------------*/
    /**
     * This method to insert data
     *
     * @param batch
     * @return Long
     */
    fun addBatch(batch: Batch): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_BATCH_ID, batch.batchId)
        contentValues.put(COLUMN_BATCH_IMAGE, batch.batchImage)
        contentValues.put(COLUMN_BATCH_NAME, batch.batchName)
        contentValues.put(COLUMN_BATCH_END_DATE, batch.theEndDate)
        contentValues.put(COLUMN_BATCH_TOTAL_QTY, batch.totalQuantity)
        contentValues.put(COLUMN_BATCH_GARDEN_ID, batch.gardenId)
        contentValues.put(COLUMN_CREATEDBY, batch.createdBy)
        contentValues.put(COLUMN_CREATEDDATE, batch.createdDate)

        // Inserting Row
        val success = db.insert(TABLE_BATCH, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    /**
     * This method to insert data for batch detail
     *
     * @param batchQtyDetail
     * @return Long
     */
    fun addBatchDetail(batchQtyDetail: BatchQtyDetail): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_BATCH_DETAIL_ID, batchQtyDetail.qtyDetailId)
        contentValues.put(COLUMN_BATCH_QTY_ID, batchQtyDetail.qtyId)
        contentValues.put(COLUMN_BATCH_VEG_NAME, batchQtyDetail.vegetableName)
        contentValues.put(COLUMN_BATCH_VEG_QTY, batchQtyDetail.vegetableQuantity)
        contentValues.put(COLUMN_CREATEDBY, batchQtyDetail.createdBy)
        contentValues.put(COLUMN_CREATEDDATE, batchQtyDetail.createdDate)

        // Inserting Row
        val success = db.insert(TABLE_BATCH_DETAIL, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }


    /**
     * This method to find by id
     *
     * @param batchQtyDetail
     * @return Arraylist
     */
    fun viewBatchByGardenId(garden_id : Int):ArrayList<BatchCustom>{
        val batchList:ArrayList<BatchCustom> = ArrayList()
        val selectQuery = "SELECT  * FROM $TABLE_BATCH WHERE $COLUMN_BATCH_GARDEN_ID=$garden_id"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery,null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var batch_id: Int
        var batch_name: String
        var batch_image : Int
        var garden_id : Int
        if (cursor.moveToFirst()) {
            do {
                batch_id = cursor.getInt(cursor.getColumnIndex(COLUMN_BATCH_ID))
                batch_name = cursor.getString(cursor.getColumnIndex(COLUMN_BATCH_NAME))
                batch_image = R.drawable.kv2
                garden_id = cursor.getInt(cursor.getColumnIndex(COLUMN_BATCH_GARDEN_ID))
                val batch = BatchCustom(batch_id, batch_image ,batch_name,garden_id)
                batchList.add(batch)
            } while (cursor.moveToNext())
        }
        return batchList
    }
    /**
     * This method to delete data
     *
     * @param batch_id
     * @return Int
     */
    fun deleteBatch(batch_id: Int):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_BATCH_ID, batch_id) // EmpModelClass UserId
        // Deleting Row
        val success = db.delete(TABLE_BATCH,"batch_id="+batch_id,null)
        deleteBatchDetail(batch_id)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    /**
     * This method to delete data
     *
     * @param batch_id
     * @return Int
     */
    fun deleteBatchDetail(batch_id: Int):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_BATCH_ID, batch_id) // EmpModelClass UserId
        // Deleting Row
        val success = db.delete(TABLE_BATCH_DETAIL,"qty_id="+batch_id,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    /**
     * This method to delete batch by batch detail id
     *
     * @param batch_id
     * @return Int
     */
    fun deleteBatchDetailByBatchDetailId(batchQtyDetailId: Int):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_BATCH_DETAIL_ID, batchQtyDetailId) // EmpModelClass UserId
        // Deleting Row
        val success = db.delete(TABLE_BATCH_DETAIL,"qty_detail_id="+batchQtyDetailId,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    /**
     * This method to update data
     *
     * @param batchQtyDetail
     * @return true/false
     */
    fun updateBatch(batch: Batch):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_BATCH_ID, batch.batchId)
        contentValues.put(COLUMN_BATCH_IMAGE, batch.batchImage)
        contentValues.put(COLUMN_BATCH_NAME, batch.batchName)
        contentValues.put(COLUMN_BATCH_END_DATE, batch.theEndDate)
        contentValues.put(COLUMN_BATCH_TOTAL_QTY, batch.totalQuantity)
        contentValues.put(COLUMN_BATCH_GARDEN_ID, batch.gardenId)
        contentValues.put(COLUMN_CREATEDBY, batch.createdBy)
        contentValues.put(COLUMN_CREATEDDATE, batch.createdDate)
        contentValues.put(COLUMN_UPDATED_BY, batch.updatedBy)
        contentValues.put(COLUMN_UPDATED_DATE,batch.updatedDate)

        // Updating Row
        val success = db.update(TABLE_BATCH, contentValues,"batch_id="+batch.batchId,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    /**
     * This method to update data
     *
     * @param batchQtyDetail
     * @return true/false
     */
    fun updateBatchDetail(batchQtyDetail: BatchQtyDetail):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_BATCH_DETAIL_ID, batchQtyDetail.qtyDetailId)
        contentValues.put(COLUMN_BATCH_QTY_ID, batchQtyDetail.qtyId)
        contentValues.put(COLUMN_BATCH_VEG_NAME, batchQtyDetail.vegetableName)
        contentValues.put(COLUMN_BATCH_VEG_QTY, batchQtyDetail.vegetableQuantity)
        contentValues.put(COLUMN_CREATEDBY, batchQtyDetail.createdBy)
        contentValues.put(COLUMN_CREATEDDATE, batchQtyDetail.createdDate)
        contentValues.put(COLUMN_UPDATED_BY, batchQtyDetail.updatedBy)


        // Updating Row
        val success = db.update(TABLE_BATCH_DETAIL, contentValues,"qty_detail_id="+batchQtyDetail.qtyDetailId,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    /**
     * This method to find batch by id
     *
     * @param batch_id
     * @return Batch
     */
    fun findBatchById(batch_id : Int):Batch{
        val selectQuery = "SELECT  * FROM $TABLE_BATCH WHERE $COLUMN_BATCH_ID = $batch_id"
        val db = this.readableDatabase
        var batch:Batch = Batch()
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery,null)
        }catch (e: SQLiteException) {
            Log.d("AAA",e.message)
        }
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    var batch_id = cursor.getInt(cursor.getColumnIndex(COLUMN_BATCH_ID))
                    var batch_name = cursor.getString(cursor.getColumnIndex(COLUMN_BATCH_NAME))
                    var created_date = cursor.getString(cursor.getColumnIndex(COLUMN_CREATEDDATE))
                    var the_end_date = cursor.getString(cursor.getColumnIndex(COLUMN_BATCH_END_DATE))
                    var total_quantity = cursor.getString(cursor.getColumnIndex(COLUMN_BATCH_TOTAL_QTY))
                    var updated_date = cursor.getString(cursor.getColumnIndex(COLUMN_UPDATED_DATE))
                    batch = Batch(batch_id,batch_name,the_end_date,total_quantity,created_date,"admin",updated_date)
                } while (cursor.moveToNext())
            }
        }
        return batch
    }
    /**
     * This method to find all batch detail by id
     *
     * @param batch_id
     * @return ArrayList
     */
    fun findAllBatchDetailById(batch_id : Int):ArrayList<BatchQtyDetail>{
        var batchDetailList:ArrayList<BatchQtyDetail> = ArrayList()
        val selectQuery = "SELECT  * FROM $TABLE_BATCH_DETAIL WHERE  $COLUMN_BATCH_QTY_ID = $batch_id"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery,null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var qty_detail_id: Int
        var vegetable_name: String
        var vegetable_quantity : String
        if (cursor.moveToFirst()) {
            do {
                qty_detail_id = cursor.getInt(cursor.getColumnIndex(COLUMN_BATCH_DETAIL_ID))
                vegetable_name = cursor.getString(cursor.getColumnIndex(COLUMN_BATCH_VEG_NAME))
                vegetable_quantity = cursor.getString(cursor.getColumnIndex(COLUMN_BATCH_VEG_QTY))
                val batchdetail = BatchQtyDetail(qty_detail_id,vegetable_name,vegetable_quantity,"admin")
                batchDetailList.add(batchdetail)
            } while (cursor.moveToNext())
        }
        return batchDetailList
    }

    /*----------------------------------------------GARDEN-------------------------------------------------*/
    /**
     * This method to find all garden
     *
     * @param batchQtyDetail
     * @return true/false
     */
    fun findAllGarden():ArrayList<Garden>{
        val gardenList:ArrayList<Garden> = ArrayList()
        val selectQuery = "SELECT  * FROM $TABLE_GARDEN"
        val db = this.readableDatabase
        var garden: Garden = Garden()
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery,null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var garden_id: Int
        var garden_name: String
        var garden_image : ByteArray
        if (cursor.moveToFirst()) {
            do {
                garden_id = cursor.getInt(cursor.getColumnIndex(COLUMN_GARDEN_ID))
                garden_name = cursor.getString(cursor.getColumnIndex(COLUMN_GARDEN_NAME))
                garden_image = cursor.getBlob(cursor.getColumnIndex(COLUMN_GARDEN_IMAGE))
                garden = Garden(garden_id,garden_name,garden_image)
                gardenList.add(garden)
            } while (cursor.moveToNext())
        }
        return gardenList
    }

    /**
     * This method to insert data vegetable
     *
     * @param batchQtyDetail
     * @return true/false
     */
    fun addGardenImageDefault(garden: Garden) : Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_GARDEN_NAME, garden.gardenName)
        contentValues.put(COLUMN_GARDEN_IMAGE, garden.gardenImage)
        contentValues.put(COLUMN_CREATEDBY, garden.createdBy)
        contentValues.put(COLUMN_CREATEDDATE, garden.createdDate)

        // Inserting Row
        val success = db.insert(TABLE_GARDEN, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    /**
     * This method to update data garden
     *
     * @param garden
     * @return int
     */
    fun updateGardenImageDefault(garden: Garden) : Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_GARDEN_ID,garden.gardenId)
        contentValues.put(COLUMN_GARDEN_NAME, garden.gardenName)
        contentValues.put(COLUMN_GARDEN_IMAGE, garden.gardenImage)
        contentValues.put(COLUMN_UPDATED_DATE, garden.updatedDate)

        // Inserting Row
        val success = db.update(TABLE_GARDEN, contentValues,"garden_id="+garden.gardenId,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    /**
     * This method to delete data
     *
     * @param batch_id
     * @return Int
     */
    fun deleteGarden(garden_id: Int):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_GARDEN_ID, garden_id)
        // Deleting Row
        val success = db.delete(TABLE_GARDEN,"garden_id="+garden_id,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    /**
     * This method to find garden by id
     *
     * @param batch_id
     * @return Batch
     */
    fun findGardenById(garden_id : Int):Garden{
        val selectQuery = "SELECT  * FROM $TABLE_GARDEN WHERE $COLUMN_GARDEN_ID = $garden_id"
        val db = this.readableDatabase
        var garden: Garden = Garden()
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery,null)
        }catch (e: SQLiteException) {
            Log.d("AAA",e.message)
        }
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    var garden_id = cursor.getInt(cursor.getColumnIndex(COLUMN_GARDEN_ID))
                    var garden_name = cursor.getString(cursor.getColumnIndex(COLUMN_GARDEN_NAME))
                    var garden_image = cursor.getBlob(cursor.getColumnIndex(COLUMN_GARDEN_IMAGE))
                    garden = Garden(garden_id,garden_name,garden_image)
                } while (cursor.moveToNext())
            }
        }
        cursor?.close()
        return garden
    }


    /*----------------------------------------------VEGETABLE-------------------------------------------------*/
    /**
     * This method to insert data vegetable
     *
     * @param batchQtyDetail
     * @return true/false
     */
    fun addVegImageDefault(veg: Vegetable) : Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_VEG_NAME, veg.vegName)
        contentValues.put(COLUMN_VEG_IMG_BLOB, veg.vegImgBlob)
        contentValues.put(COLUMN_CREATEDBY, veg.createdBy)
        contentValues.put(COLUMN_CREATEDDATE, veg.createdDate)

        // Inserting Row
        val success = db.insert(TABLE_VEGETABLE, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    /**
     * This method to update data vegetable
     *
     * @param batchQtyDetail
     * @return true/false
     */
    fun updateVegImageDefault(veg: Vegetable) : Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_VEG_ID,veg.vegID)
        contentValues.put(COLUMN_VEG_NAME, veg.vegName)
        contentValues.put(COLUMN_VEG_IMG_BLOB, veg.vegImgBlob)
        contentValues.put(COLUMN_UPDATED_DATE, veg.updatedDate)

        // Inserting Row
        val success = db.update(TABLE_VEGETABLE, contentValues,"veg_id="+veg.vegID,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    /**
     * This method to insert data vegetable
     *
     * @return ArrayList
     */
    fun findAllVegetable():ArrayList<Vegetable>{
        val vegList:ArrayList<Vegetable> = ArrayList()
        val selectQuery = "SELECT  * FROM $TABLE_VEGETABLE"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery,null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var veg_id: Int
        var veg_name: String
        var veg_image : ByteArray
        if (cursor.moveToFirst()) {
            do {
                veg_id = cursor.getInt(cursor.getColumnIndex(COLUMN_VEG_ID))
                veg_name = cursor.getString(cursor.getColumnIndex(COLUMN_VEG_NAME))
                veg_image = cursor.getBlob(cursor.getColumnIndex(COLUMN_VEG_IMG_BLOB))
                val vegetable = Vegetable(veg_id,veg_name,veg_image)
                vegList.add(vegetable)
            } while (cursor.moveToNext())
        }
        cursor?.close()
        return vegList
    }

    /**
     * This method to delete data
     *
     * @param batch_id
     * @return Int
     */
    fun deleteVeg(veg_id: Int):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_VEG_ID, veg_id) // EmpModelClass UserId
        // Deleting Row
        val success = db.delete(TABLE_VEGETABLE,"veg_id="+veg_id,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    /**
     * This method to find vegetable by id
     *
     * @param batch_id
     * @return Batch
     */
    fun findVegetableById(veg_id : Int):Vegetable{
        val selectQuery = "SELECT  * FROM $TABLE_VEGETABLE WHERE $COLUMN_VEG_ID = $veg_id"
        val db = this.readableDatabase
        var vegetable:Vegetable = Vegetable()
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery,null)
        }catch (e: SQLiteException) {
            Log.d("AAA",e.message)
        }
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    var veg_id = cursor.getInt(cursor.getColumnIndex(COLUMN_VEG_ID))
                    var veg_name = cursor.getString(cursor.getColumnIndex(COLUMN_VEG_NAME))
                    var veg_image = cursor.getBlob(cursor.getColumnIndex(COLUMN_VEG_IMG_BLOB))
                    vegetable = Vegetable(veg_id,veg_name,veg_image)
                } while (cursor.moveToNext())
            }
        }
        cursor?.close()
        return vegetable
    }
}