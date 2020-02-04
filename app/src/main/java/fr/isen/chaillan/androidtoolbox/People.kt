package fr.isen.chaillan.androidtoolbox

class People(firstName: String,lastName: String,age: Int) {

    var firstName: String
    var lastName: String
    var age: Int



    init {
        this.firstName = firstName
        this.lastName = lastName
        this.age = age

    }

    override fun toString(): String {
        return "People(firstName='$firstName', lastName='$lastName', age=$age)"
    }


}