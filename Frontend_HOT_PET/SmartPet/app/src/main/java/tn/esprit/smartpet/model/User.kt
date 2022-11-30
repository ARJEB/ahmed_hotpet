package tn.esprit.smartpet.model
import com.google.gson.annotations.SerializedName


// 1. Class 3andha nafs l attributs li fi json
data class User(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("username")
    var name: String? = null,

    )