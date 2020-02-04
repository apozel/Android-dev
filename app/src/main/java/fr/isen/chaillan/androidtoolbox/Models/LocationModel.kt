package fr.isen.chaillan.androidtoolbox.Models

class LocationModel {
    var city: String? = null
    var state: String? = null
    var country: String? = null
    //var postcode: Int? = null
    var street: StreetModel? = null
}

class StreetModel {
    var name: String? = null
    //var number: Int? = null
}