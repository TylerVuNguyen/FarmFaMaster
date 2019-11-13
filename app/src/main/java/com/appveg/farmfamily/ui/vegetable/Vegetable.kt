package com.appveg.farmfamily.ui.vegetable

class Vegetable {

    var vegID : Int? = 0
    var vegName : String? = ""
           var vegCode : String? =""
           var vegImg : String?=null
           var createdBy: String? = ""
           var createdDate: String? = null
           var updatedBy: String? = ""
           var updatedDate: String? = null
           var deletedBy: String? = ""
           var deletedDate: String? =""
           var deletedFlag:Int = 1

    constructor(
        vegID: Int?,
        vegName: String?,
        vegCode: String?,
        vegImg: String?,
        createdBy: String?,
        createdDate: String?,
        updatedBy: String?,
        updatedDate: String?,
        deletedBy: String?,
        deletedDate: String?,
        deletedFlag: Int
    ) {
        this.vegID = vegID
        this.vegName = vegName
        this.vegCode = vegCode
        this.vegImg = vegImg
        this.createdBy = createdBy
        this.createdDate = createdDate
        this.updatedBy = updatedBy
        this.updatedDate = updatedDate
        this.deletedBy = deletedBy
        this.deletedDate = deletedDate
        this.deletedFlag = deletedFlag
    }

    constructor(
        vegID: Int?,
        vegName: String?,
        vegCode: String?,
        vegImg: String?,
        createdBy: String?,
        createdDate: String?
    ) {
        this.vegID = vegID
        this.vegName = vegName
        this.vegCode = vegCode
        this.vegImg = vegImg
        this.createdBy = createdBy
        this.createdDate = createdDate
    }

    constructor()
    constructor(name: String) {
        this.vegName = name
        //data mac dinh la icon = 1: nguoi dung muon thay doi thi thay doi tren usng dung
//        this.isActive = true
        this.vegImg = null
    }

    constructor(vegID: Int?, vegName: String?) {
        this.vegID = vegID
        this.vegName = vegName
//        this.isActive = true
    }

    constructor(vegName: String?, vegImg: String?) {
        this.vegName = vegName
        this.vegImg = vegImg
//        this.isActive = true
    }


    //get ALl Room
    constructor(vegID: Int?, vegName: String?, vegImg: String?) {
        this.vegID = vegID
        this.vegName = vegName
        this.vegImg = vegImg
    }

    public var HandleName: String?
        get() {
            return this.vegName
        }
        set(value) {
            this.vegName = value
        }


    public var HandleID: Int?
        get() {
            return this.vegID
        }
        set(value) {
            this.vegID = value
        }
    public var HandleImageVeg: String?
        get() {
            return this.vegImg
        }
        set(value) {
            this.vegImg = value
        }

    override fun toString(): String {
        return "id: ${this.vegID} name: ${this.vegName} image: ${this.vegImg} }"
    }


}