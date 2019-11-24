package com.appveg.farmfamily.ui.garden

class GardenCustom {
    var gardenId: Int? = null
    var gardenImage: Int? = 0
    var gardenName: String? = ""

    constructor(gardenId: Int?, gardenImage: Int?, gardenName: String?) {
        this.gardenId = gardenId
        this.gardenImage = gardenImage
        this.gardenName = gardenName
    }
    constructor()
}