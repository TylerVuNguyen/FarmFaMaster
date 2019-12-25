package com.appveg.farmfamily.ui.device

class Device {
    var deviceID : Int? = 0
    var deviceName : String? = ""
    var deviceImg : ByteArray? = null
    var deviceNum :  String? = ""
    var deviceCategoryId:  Int? = 0


    constructor(
        deviceID: Int?,
        deviceName: String?,
        deviceImg: ByteArray?,
        deviceNum: String?,
        deviceCategoryId: Int?
    ) {
        this.deviceID = deviceID
        this.deviceName = deviceName
        this.deviceImg = deviceImg
        this.deviceNum = deviceNum
        this.deviceCategoryId = deviceCategoryId
    }

    constructor(
        deviceID: Int?,
        deviceName: String?,
        deviceImg: ByteArray?,
        deviceCategoryId: Int?
    ) {
        this.deviceID = deviceID
        this.deviceName = deviceName
        this.deviceImg = deviceImg
        this.deviceCategoryId = deviceCategoryId
    }

    constructor(
    deviceID: Int?,
    deviceName: String?,
    deviceImg: ByteArray?,
    deviceNum: String?
    ) {
        this.deviceID = deviceID
        this.deviceName = deviceName
        this.deviceImg = deviceImg
        this.deviceNum = deviceNum
    }



    constructor()
}