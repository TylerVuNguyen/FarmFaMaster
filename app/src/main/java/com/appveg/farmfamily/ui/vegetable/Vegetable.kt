package com.appveg.farmfamily.ui.vegetable

class Vegetable {

    var vegID: Int? = 0
    var vegName: String? = ""
    var vegImg: String? = null
    var vegImgBlob: ByteArray? = null
    var createdBy: String? = "admin"
    var createdDate: String? = null
    var updatedBy: String? = ""
    var updatedDate: String? = null
    var deletedBy: String? = ""
    var deletedDate: String? = ""
    var deletedFlag: Int = 1
    constructor(
        vegID: Int?,
        vegName: String?,
        vegImgBlob: ByteArray,
        createdBy: String?,
        createdDate: String?
    ) {
        this.vegID = vegID
        this.vegName = vegName
        this.vegImgBlob = vegImgBlob
        this.createdBy = createdBy
        this.createdDate = createdDate
    }
    constructor(
        vegID: Int?,
        vegName: String?,
        vegImg: String?,
        vegImgBlob: ByteArray,
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
        this.vegImg = vegImg
        this.vegImgBlob = vegImgBlob
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
        vegImg: String?,
        createdBy: String?,
        createdDate: String?
    ) {
        this.vegID = vegID
        this.vegName = vegName
        this.vegImg = vegImg
        this.createdBy = createdBy
        this.createdDate = createdDate
    }
    constructor(
        vegID: Int?,
        vegName: String?,
        vegImg: ByteArray,
        updatedDate: String?
    ) {
        this.vegID = vegID
        this.vegName = vegName
        this.vegImg = vegImg.toString()
        this.updatedDate = updatedDate
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


    //get ALl Room
    constructor(vegID: Int?, vegName: String?, vegImgBlob: ByteArray) {
        this.vegID = vegID
        this.vegName = vegName
        this.vegImgBlob = vegImgBlob
    }


}