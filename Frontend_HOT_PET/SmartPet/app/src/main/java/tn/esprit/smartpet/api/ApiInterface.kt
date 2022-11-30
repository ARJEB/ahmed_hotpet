package tn.esprit.smartpet.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import tn.esprit.smartpet.model.User


// 2.Création d’une interface qui regroupe les différents endpoints avec une méthode "create"
// qui crée une instance
interface ApiInterface {

    // L'entete de chaque fonction

    // Sign in
    @POST("/User/login")   // endpoint=2ème partie de l'URL
    fun login(@Body user: User):Call<User>

    // Sign up with 3 arguments
    @POST("/User/register")
    fun signup(@Body user: User):Call<User>


    // EditProfil_first_time up with 3 arguments
    @POST("/User/EditProfile/:id")
    fun EditProfile(@Body user: User):Call<User>

    /*@POST("/user/patchOnce")
    fun updatePassword (@Body map: HashMap<String ,String>):Call<User>*/



    // 3. Builder + Converter
    companion object {

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://172.29.192.1:3000")
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}