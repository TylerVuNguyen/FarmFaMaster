package com.appveg.farmfamily.ui.garden

class Garden {
    var gardenId: Int? = null
    var gardenImage:  ByteArray? = null
    var gardenName: String? = ""
    var createdBy: String? = "admin"
    var createdDate: String? = null
    var updatedBy: String? = ""
    var updatedDate: String? = null
    var deletedBy: String? = ""
    var deletedDate: String? = null
    var deletedFlag: Int = 1
    constructor()

    constructor(
        gardenId: Int?,
        gardenName: String?,
        gardenImage:  ByteArray?,
        createdBy: String?,
        createdDate: String?
    ) {
        this.gardenId = gardenId
        this.gardenImage = gardenImage
        this.gardenName = gardenName
        this.createdBy = createdBy
        this.createdDate = createdDate
    }
    constructor(
        gardenId: Int?,
        gardenName: String?,
        gardenImage:  ByteArray?,
        updatedDate: String? = null
    ) {
        this.gardenId = gardenId
        this.gardenImage = gardenImage
        this.gardenName = gardenName
        this.updatedDate = updatedDate
    }

    constructor(
        gardenId: Int?,
        gardenImage:  ByteArray?,
        gardenName: String?,
        createdBy: String?,
        createdDate: String?,
        updatedBy: String?,
        updatedDate: String?,
        deletedBy: String?,
        deletedDate: String?,
        deletedFlag: Int
    ) {
        this.gardenId = gardenId
        this.gardenImage = gardenImage
        this.gardenName = gardenName
        this.createdBy = createdBy
        this.createdDate = createdDate
        this.updatedBy = updatedBy
        this.updatedDate = updatedDate
        this.deletedBy = deletedBy
        this.deletedDate = deletedDate
        this.deletedFlag = deletedFlag
    }


    constructor(
        gardenId: Int?,
        gardenName: String?,
        gardenImage:  ByteArray?
    ) {
        this.gardenId = gardenId
        this.gardenName = gardenName
        this.gardenImage = gardenImage
    }
}