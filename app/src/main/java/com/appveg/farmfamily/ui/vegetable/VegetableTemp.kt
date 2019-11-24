package com.appveg.farmfamily.ui.vegetable

class VegetableTemp {
    var vegId: Int? = -1
    var vegName: String? = ""
    var vegQty: Int? = null

    constructor(vegName: String?, vegQty: Int?) {
        this.vegName = vegName
        this.vegQty = vegQty
    }

    constructor()

    constructor(vegId: Int?, vegName: String?, vegQty: Int?) {
        this.vegId = vegId
        this.vegName = vegName
        this.vegQty = vegQty
    }

}