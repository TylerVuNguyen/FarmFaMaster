package com.appveg.farmfamily.ui.send

import java.io.Serializable

class BatchCustom : Serializable{
    var batchId: Int? = null
    var batchImage: Int? = 0
    var batchName: String? = ""
    var totalQuantity: String? = ""
    var gardenId: Int? = null

    constructor(
        batchId: Int?,
        batchImage: Int?,
        batchName: String?,
        totalQuantity: String?,
        gardenId: Int?
    ) {
        this.batchId = batchId
        this.batchImage = batchImage
        this.batchName = batchName
        this.totalQuantity = totalQuantity
        this.gardenId = gardenId
    }
}