package com.appveg.farmfamily.ui.share

class SubstanceDetail {
    var substanceMassDetailId: Int? = null
    var substanceMassDetailName: String = ""
    var substanceMassDetailImage: String = ""
    var substanceMassDetailNum: String = ""
    var substanceMassId: Int = 0
    var gardenId: Int = 0
    var createdBy: String? = ""
    var createdDate: String? = null
    var updatedBy: String? = ""
    var updatedDate: String? = null
    var deletedBy: String? = ""
    var deletedDate: String? = null
    var deletedFlag: Int = 1

    constructor(
        substanceMassDetailId: Int?,
        substanceMassDetailName: String,
        substanceMassDetailImage: String,
        substanceMassDetailNum: String,
        substanceMassId: Int,
        gardenId: Int,
        createdBy: String?,
        createdDate: String?,
        updatedBy: String?,
        updatedDate: String?,
        deletedBy: String?,
        deletedDate: String?,
        deletedFlag: Int
    ) {
        this.substanceMassDetailId = substanceMassDetailId
        this.substanceMassDetailName = substanceMassDetailName
        this.substanceMassDetailImage = substanceMassDetailImage
        this.substanceMassDetailNum = substanceMassDetailNum
        this.substanceMassId = substanceMassId
        this.gardenId = gardenId
        this.createdBy = createdBy
        this.createdDate = createdDate
        this.updatedBy = updatedBy
        this.updatedDate = updatedDate
        this.deletedBy = deletedBy
        this.deletedDate = deletedDate
        this.deletedFlag = deletedFlag
    }
    constructor(
        substanceMassDetailId: Int?,
        substanceMassDetailName: String,
        substanceMassDetailImage: String,
        substanceMassDetailNum: String,
        substanceMassId: Int,
        gardenId: Int,
        createdBy: String?,
        createdDate: String?
    ) {
        this.substanceMassDetailId = substanceMassDetailId
        this.substanceMassDetailName = substanceMassDetailName
        this.substanceMassDetailImage = substanceMassDetailImage
        this.substanceMassDetailNum = substanceMassDetailNum
        this.substanceMassId = substanceMassId
        this.gardenId = gardenId
        this.createdBy = createdBy
        this.createdDate = createdDate

    }
    constructor()
}