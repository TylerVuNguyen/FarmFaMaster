package com.appveg.farmfamily.ui.vegetable

class Vegetable {

    var vegID: Int? = 0
    var vegName: String? = ""
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
        this.createdBy = createdBy
        this.createdDate = createdDate
    }
    constructor(
        vegID: Int?,
        vegName: String?,
        vegImgBlob: ByteArray,
        updatedDate: String?
    ) {
        this.vegID = vegID
        this.vegName = vegName
        this.vegImgBlob = vegImgBlob
        this.updatedDate = updatedDate
    }

    constructor()

    //get ALl Room
    constructor(vegID: Int?, vegName: String?, vegImgBlob: ByteArray) {
        this.vegID = vegID
        this.vegName = vegName
        this.vegImgBlob = vegImgBlob
    }


}