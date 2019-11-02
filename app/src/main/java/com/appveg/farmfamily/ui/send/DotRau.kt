package com.appveg.farmfamily.ui.send

class DotRau {

    var dotRau_id : Int? =0

    var dotRau_name : String? = null


    var dotRau_photo : Int?=0


    var dotRau_trangthai: Int? =0


    var dotRau_SL : Int? = 0

    fun dotRau_SL():Int{
        return this.dotRau_SL()
    }

    fun dotRau_trangthai():Int{
        return this.dotRau_trangthai()
    }


    fun dotRau_photo(): Int {
        return this.dotRau_photo()
    }

    fun rau_photo(image_drawable: Int) {
        this.dotRau_photo = image_drawable
    }



    fun getNames(): String {
        return dotRau_name.toString()
    }

    fun setNames(dotRau_name: String) {
        this.dotRau_name = dotRau_name
    }

    constructor()

    constructor(dotRau_name:String,  dotRau_photo:Int?, dotRau_trangthai:Int?,dotRau_SL:Int?){
        this.dotRau_name = dotRau_name
        this.dotRau_photo= dotRau_photo
        this.dotRau_trangthai = dotRau_trangthai
        this.dotRau_SL = dotRau_SL
    }





}