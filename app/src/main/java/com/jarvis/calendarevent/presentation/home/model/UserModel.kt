package com.jarvis.calendarevent.presentation.home.model

import java.io.Serializable

class UserModel : Serializable {

    private val myStrName = "Bob"

    override fun toString(): String {
        return super.toString() + "myStrName = " + myStrName
    }
}