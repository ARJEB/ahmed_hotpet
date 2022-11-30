package tn.esprit.smartpet.view

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.smartpet.R
import java.time.LocalDate
import java.time.Period


class ProfileFragment : Fragment() {

    private lateinit var profilePic: ImageView
    private lateinit var txtEmail: TextView
    private lateinit var btn_edit: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootView : View = inflater.inflate(R.layout.fragment_profile, container, false)

        /*profilePic = rootView.findViewById<ImageView>(R.id.profilePic)
        profilePic.isEnabled = false
        profilePic.setImageURI(Uri.parse(IMAGE))*/ /*setImageURI(Uri.parse(intent.extras!!.getString(IMAGE)))*/

        /*txtEmail = rootView.findViewById(R.id.txtEmail)
        txtEmail.isEnabled = false
        txtEmail.text = mSharedPref.getString(EMAIL, "").toString()*/

        /*mSharedPref = getSharedPreferences(PREF_NAME, MODE_PRIVATE)*/

        btn_edit = rootView.findViewById(R.id.btn_edit)
        btn_edit.setOnClickListener {
            activity?.let {
                val intent = Intent(it, InformationActivity::class.java)
                it.startActivity(intent)
            }
        }

        return rootView
    }


    companion object {
        @JvmStatic
        fun newInstance(age: String) = ProfileFragment().apply {
            arguments = Bundle().apply {
                putString(AGE, age)
            }
        }
    }

}