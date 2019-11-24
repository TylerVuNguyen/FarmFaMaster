package com.appveg.farmfamily.ui.device

class Device {
    var deviceID : Int? = 0
    var deviceName : String? = ""
    var deviceCode : String? =""
    var deviceImg : Int? = 0
    var deviceStatus :String?=""
    var createdBy: String? = "admin"
    var createdDate: String? = null
    var updatedBy: String? = ""
    var updatedDate: String? = null
    var deletedBy: String? = ""
    var deletedDate: String? =""
    var deletedFlag:Int = 1

    constructor(
        deviceID: Int?,
        deviceName: String?,
        deviceCode: String?,
        deviceImg :Int?,
        deviceStatus: String?,
        createdBy: String?,
        createdDate: String?,
        updatedBy: String?,
        updatedDate: String?,
        deletedBy: String?,
        deletedDate: String?,
        deletedFlag: Int
    ) {
        this.deviceID = deviceID
        this.deviceName = deviceName
        this.deviceCode = deviceCode
        this.deviceImg = deviceImg
        this.deviceStatus = deviceStatus
        this.createdBy = createdBy
        this.createdDate = createdDate
        this.updatedBy = updatedBy
        this.updatedDate = updatedDate
        this.deletedBy = deletedBy
        this.deletedDate = deletedDate
        this.deletedFlag = deletedFlag
    }

    constructor()
    constructor(name: String) {
        this.deviceName = name
        //data mac dinh la icon = 1: nguoi dung muon thay doi thi thay doi tren usng dung
//        this.isActive = true
        this.deviceName = null
    }

    constructor(deviceID: Int?, deviceName: String?) {
        this.deviceID = deviceID
        this.deviceName = deviceName
//        this.isActive = true
    }

    constructor(deviceName: String?, deviceImg: Int?) {
        this.deviceName = deviceName
        this.deviceImg = deviceImg
//        this.isActive = true
    }


    //get ALl Room
    constructor(deviceID: Int?, deviceName: String?, deviceImg: Int?) {
        this.deviceID = deviceID
        this.deviceName = deviceName
        this.deviceImg = deviceImg
    }

    public var getDeviceName: String?
        get() {
            return this.deviceName
        }
        set(value) {
            this.deviceName = value
        }


    public var getDeviceID: Int?
        get() {
            return this.deviceID
        }
        set(value) {
            this.deviceID = value
        }
    public var getDeviceImg: Int?
        get() {
            return this.getDeviceImg
        }
        set(value) {
            this.deviceImg = value
        }

    override fun toString(): String {
        return "id: ${this.deviceID} name: ${this.deviceName} image: ${this.deviceImg} }"
    }

}