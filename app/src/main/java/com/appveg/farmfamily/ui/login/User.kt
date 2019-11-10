package com.appveg.farmfamily.ui.login

class User {
    var id: Int? = null
    var userName: String? = ""
    var fullName: String? = ""
    var email: String? = ""
    var password: String? = ""
    var gender: String? = ""
    var status: Int = 1
    var roleId: Int = 1
    var createdBy: String? = ""
    var createdDate: String? = null
    var updatedBy: String? = ""
    var updatedDate: String? = null
    var deletedBy: String? = ""
    var deletedDate: String? = null
    var deletedFlag: Int = 1

    constructor(
        id: Int,
        userName: String?,
        fullName: String?,
        email: String?,
        password: String?,
        gender: String?,
        status: Int,
        roleId: Int,
        createdBy: String?,
        createdDate: String?,
        updatedBy: String?,
        updatedDate: String?,
        deletedBy: String?,
        deletedDate: String?,
        deletedFlag: Int
    ) {
        this.id = id
        this.userName = userName
        this.fullName = fullName
        this.email = email
        this.password = password
        this.gender = gender
        this.status = status
        this.roleId = roleId
        this.createdBy = createdBy
        this.createdDate = createdDate
        this.updatedBy = updatedBy
        this.updatedDate = updatedDate
        this.deletedBy = deletedBy
        this.deletedDate = deletedDate
        this.deletedFlag = deletedFlag
    }

    constructor(
        id: Int?,
        userName: String?,
        fullName: String?,
        email: String?,
        password: String?,
        gender: String?,
        status: Int,
        createdBy: String?,
        createdDate: String?
    ) {
        this.id = id
        this.userName = userName
        this.fullName = fullName
        this.email = email
        this.password = password
        this.gender = gender
        this.status = status
        this.createdBy = createdBy
        this.createdDate = createdDate
    }
}