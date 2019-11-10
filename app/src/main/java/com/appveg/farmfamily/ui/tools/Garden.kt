package com.appveg.farmfamily.ui.tools

class Garden {
    private var id: Int = 0

    private var name: String = ""

    private var tab_index: Int = 0


    private var isActive: Boolean = false


    private var listGarden: ArrayList<Garden>? = null

    constructor()
    constructor(id: Int, name: String, isActive: Boolean) {
        this.id = id
        this.name = name
        this.isActive = isActive
        listGarden = arrayListOf()
    }

    constructor(id: Int, name: String, tab_index: Int) {
        this.id = id
        this.name = name
        this.tab_index = tab_index
        listGarden = arrayListOf()
    }


    //getter setter
    public var HandlelistDevice: ArrayList<Garden>?
        get() {
            return listGarden
        }
        set(value) {
            this.listGarden = value
        }

    //truy xuat DB lay ra danh sach cac home
    public var HandelId: Int
        get() {
            return this.id
        }
        set(value) {
            this.id = value
        }
    public var HandelName: String
        get() {
            return this.name
        }
        set(value) {
            this.name = value
        }
    public var Handeltab_index: Int
        get() {
            return this.tab_index
        }
        set(value) {
            this.tab_index = value
        }

    public var HandelisActive: Boolean
        get() {
            return this.isActive
        }
        set(value) {
            this.isActive = value
        }
}