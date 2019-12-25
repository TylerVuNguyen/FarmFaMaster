package com.appveg.farmfamily.ui.device

class DeviceDetail {
    var deviceDetailID : Int? = 0
    var deviceDetailCode : String? =""
    var deviceDetailImg : ByteArray? = null
    var deviceID : Int? = 0
    var deviceDetailStatus :String?=""
    var createdBy: String? = "admin"
    var createdDate: String? = null
    var updatedBy: String? = ""
    var updatedDate: String? = null
    var deletedBy: String? = ""
    var deletedDate: String? =""
    var deletedFlag:Int = 1



    constructor()
    constructor(
        deviceDetailID: Int?,
        deviceDetailCode: String?,
        deviceDetailImg: ByteArray?,
        deviceID: Int?,
        deviceDetailStatus: String?,
        createdBy: String?,
        createdDate: String?,
        updatedBy: String?,
        updatedDate: String?,
        deletedBy: String?,
        deletedDate: String?,
        deletedFlag: Int
    ) {
        this.deviceDetailID = deviceDetailID
        this.deviceDetailCode = deviceDetailCode
        this.deviceDetailImg = deviceDetailImg
        this.deviceID = deviceID
        this.deviceDetailStatus = deviceDetailStatus
        this.createdBy = createdBy
        this.createdDate = createdDate
        this.updatedBy = updatedBy
        this.updatedDate = updatedDate
        this.deletedBy = deletedBy
        this.deletedDate = deletedDate
        this.deletedFlag = deletedFlag
    }
    constructor(
        deviceDetailID: Int?,
        deviceDetailCode: String?,
        deviceDetailImg: ByteArray?,
        deviceDetailStatus: String?
    ) {
        this.deviceDetailID = deviceDetailID
        this.deviceDetailCode = deviceDetailCode
        this.deviceDetailImg = deviceDetailImg
        this.deviceDetailStatus = deviceDetailStatus
    }

    constructor(
        deviceDetailID: Int?,
        deviceDetailCode: String?,
        deviceDetailImg: ByteArray?,
        deviceID: Int?,
        deviceDetailStatus: String?,
        createdBy: String?,
        createdDate: String?
    ) {
        this.deviceDetailID = deviceDetailID
        this.deviceDetailCode = deviceDetailCode
        this.deviceDetailImg = deviceDetailImg
        this.deviceID = deviceID
        this.deviceDetailStatus = deviceDetailStatus
        this.createdBy = createdBy
        this.createdDate = createdDate
    }


}