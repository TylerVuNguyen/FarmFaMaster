package com.appveg.farmfamily.ui.home

class DetailGardenFirebase {
    var timestamp : Long? = null
    var Rain : String? = null
    var Temperature : String? = ""
    var Humidity : String? =""

    constructor()

    constructor(timestamp: Long?,Rain: String?, Temperature: String?, humidity: String?) {
        this.timestamp = timestamp
        this.Rain = Rain
        this.Temperature = Temperature
        this.Humidity = Humidity
    }

}