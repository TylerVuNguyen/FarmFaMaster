package com.appveg.farmfamily.ui.home

class DetailGardenFirebase {
    var timestamp : Long? = null
    var Temperature : String? = ""
    var Humidity : String? =""

    constructor()

    constructor(timestamp: Long?, Temperature: String?, humidity: String?) {
        this.timestamp = timestamp
        this.Temperature = Temperature
        this.Humidity = Humidity
    }

}