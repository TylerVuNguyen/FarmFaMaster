package com.appveg.farmfamily.ui.send

class Rau  {

    var rau_id : Int? =0

    var rau_name : String? = null


    var rau_photo: Int? =0


    fun rau_photo(): Int {
        return this.rau_photo()
    }

    fun rau_photo(image_drawable: Int) {
        this.rau_photo = image_drawable
    }



    fun getNames(): String {
        return rau_name.toString()
    }

    fun setNames(rau_name: String) {
        this.rau_name = rau_name
    }

    constructor()

   constructor(rau_photo:Int,  rau_name:String){
        this.rau_photo=rau_photo
        this.rau_name= rau_name
    }


    constructor(rau_id:Int,rau_name: String,rau_photo: Int ) {
        this.rau_id = rau_id
        this.rau_name = rau_name
        this.rau_photo = rau_photo
    }
}