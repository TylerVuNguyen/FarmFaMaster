package com.appveg.farmfamily.ui.param

class Param {

    var paramId : Int? = -1

    var tempToNight : String? =null
    var tempFromNight : String? = null

    var tempToDay: String? = null
    var tempFromDay : String? = null

    var phTo : String? = null
    var phFrom : String? = null

    var ppmTo :String? = null
    var ppmFrom :String? = null

    var createdBy: String? = "admin"
    var createdDate: String? = null
    var updatedBy: String? = ""
    var updatedDate: String? = null
    var deletedBy: String? = ""
    var deletedDate: String? = ""
    var deletedFlag: Int = 1


    constructor(
        paramId : Int?,
        tempToNight : String?,
        tempFromNight : String?,
        tempToDay: String?,
        tempFromDay : String?,
        phTo : String?,
        phFrom : String?,
        ppmTo :String?,
        ppmFrom :String?,
        createdBy: String?,
        createdDate: String?,
        updatedBy: String?,
        updatedDate: String?,
        deletedBy: String?,
        deletedDate: String?,
        deletedFlag: Int

    ){
        this.paramId = paramId
        this.tempToNight = tempToNight
        this.tempFromNight = tempFromNight
        this.tempToDay = tempToDay
        this.tempFromDay = tempFromDay
        this.phTo = phTo
        this.phFrom = phFrom
        this.ppmTo = ppmTo
        this.ppmFrom = ppmFrom
        this.createdBy = createdBy
        this.createdDate = createdDate
        this.updatedBy = updatedBy
        this.updatedDate = updatedDate
        this.deletedBy = deletedBy
        this.deletedDate = deletedDate
        this.deletedFlag = deletedFlag
    }

    constructor(
        paramId : Int?,
        tempToNight : String?,
        tempFromNight : String?,
        tempToDay: String?,
        tempFromDay : String?,
        phTo : String?,
        phFrom : String?,
        ppmTo :String?,
        ppmFrom :String?,
        createdBy: String?,
        createdDate: String?

    ){
        this.paramId = paramId
        this.tempToNight = tempToNight
        this.tempFromNight = tempFromNight
        this.tempToDay = tempToDay
        this.tempFromDay = tempFromDay
        this.phTo = phTo
        this.phFrom = phFrom
        this.ppmTo = ppmTo
        this.ppmFrom = ppmFrom
        this.createdBy = createdBy
        this.createdDate = createdDate
    }

    constructor(
        paramId : Int?,
        tempToNight : String?,
        tempFromNight : String?,
        tempToDay: String?,
        tempFromDay : String?,
        phTo : String?,
        phFrom : String?,
        ppmTo :String?,
        ppmFrom :String?,
        updatedDate: String?

    ){
        this.paramId = paramId
        this.tempToNight = tempToNight
        this.tempFromNight = tempFromNight
        this.tempToDay = tempToDay
        this.tempFromDay = tempFromDay
        this.phTo = phTo
        this.phFrom = phFrom
        this.ppmTo = ppmTo
        this.ppmFrom = ppmFrom
        this.updatedDate = updatedDate
    }

    constructor(
        tempToNight : String?,
        tempFromNight : String?,
        tempToDay: String?,
        tempFromDay : String?,
        phTo : String?,
        phFrom : String?,
        ppmTo :String?,
        ppmFrom :String?,
        createdBy: String?,
        createdDate: String?

    ){
        this.tempToNight = tempToNight
        this.tempFromNight = tempFromNight
        this.tempToDay = tempToDay
        this.tempFromDay = tempFromDay
        this.phTo = phTo
        this.phFrom = phFrom
        this.ppmTo = ppmTo
        this.ppmFrom = ppmFrom
        this.createdBy = createdBy
        this.createdDate = createdDate
    }

    constructor(
        paramId : Int?,
        tempToNight : String?,
        tempFromNight : String?,
        tempToDay: String?,
        tempFromDay : String?,
        createdBy: String?,
        createdDate: String?

    ){
        this.paramId = paramId
        this.tempToNight = tempToNight
        this.tempFromNight = tempFromNight
        this.tempToDay = tempToDay
        this.tempFromDay = tempFromDay
        this.createdBy = createdBy
        this.createdDate = createdDate
    }

    constructor(
        paramId: Int?,
        tempToNight : String?,
        tempFromNight : String?,
        tempToDay: String?,
        tempFromDay : String?,
        phTo : String?,
        phFrom : String?,
        ppmTo :String?,
        ppmFrom :String?

    ){
        this.paramId = paramId
        this.tempToNight = tempToNight
        this.tempFromNight = tempFromNight
        this.tempToDay = tempToDay
        this.tempFromDay = tempFromDay
        this.phTo = phTo
        this.phFrom = phFrom
        this.ppmTo = ppmTo
        this.ppmFrom = ppmFrom
    }

    constructor()


}