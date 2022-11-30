package tn.esprit.smartpet.view

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.databinding.adapters.CalendarViewBindingAdapter.setDate
import com.google.android.material.snackbar.Snackbar
import tn.esprit.smartpet.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*

const val IMAGE = "IMAGE"
const val GENDER = "GENDER"
const val CATEGORIE = "CATEGORIE"
const val AGE = "AGE"

class InformationActivity : AppCompatActivity() {

    private lateinit var btn_skip: Button
    private lateinit var profilePic: ImageView
    private lateinit var rbMale: RadioButton
    private lateinit var rbFemale: RadioButton
    val categories = arrayOf("Cat", "Dog", "Horse", "Rabit", "Bird", "Hamster")
    private lateinit var datePicker: DatePicker
    private var msg: String? = null
    private lateinit var btn_confirm: Button

    private lateinit var selectedImageUri: Uri
    private val startForResultOpenGallery =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                selectedImageUri = result.data!!.data!!
                profilePic!!.setImageURI(selectedImageUri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        // Spinner " I am "
        val spinner = findViewById<Spinner>(R.id.spinner)
        val arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,categories)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) { val pet = categories[position]
                Toast.makeText(applicationContext,"You're a "+pet,Toast.LENGTH_SHORT).show() }
            override fun onNothingSelected(parent: AdapterView<*>?) { TODO("Not yet implemented") }
        }


        btn_skip = findViewById(R.id.btn_skip)
        profilePic = findViewById(R.id.addImage)
        rbMale = findViewById(R.id.rbMale)
        rbFemale = findViewById(R.id.rbFemale)
        datePicker = findViewById<DatePicker>(R.id.datePicker)
        btn_confirm = findViewById(R.id.btn_confirm)

        btn_skip.setOnClickListener {
            val main_intent = Intent(this, Home::class.java)
            startActivity(main_intent)
        }

        profilePic!!.setOnClickListener {
            openGallery()
        }

        // Birthday date
        val today = Calendar.getInstance()
        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)

        ) { view, year, month, day ->
            val month = month + 1
            msg = " $day/$month/$year"
            Toast.makeText(this@InformationActivity, msg, Toast.LENGTH_SHORT).show()
        }

        btn_confirm.setOnClickListener {
            clickConfirm()
        }
    }

    /*@RequiresApi(Build.VERSION_CODES.O)
    fun getAge(year: Int, month: Int, dayOfMonth: Int): Int {
        return Period.between(LocalDate.of(year, month, dayOfMonth), LocalDate.now()).years
    }*/

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        startForResultOpenGallery.launch(intent)
    }

    private fun clickConfirm(){
        if (validate()) {

            val mainIntent = Intent(this, Home::class.java).apply {
                /*putExtra(CATEGORIE,pet.toString() )*/
                putExtra(AGE, msg.toString())
                /*putExtra(IMAGE, selectedImageUri.toString())*/
                /*if (rbMale.isChecked)
                    putExtra(GENDER, rbMale.text.toString())
                if (rbFemale.isChecked)
                    putExtra(GENDER, rbFemale.text.toString())*/
            }
            startActivity(mainIntent)
            finish()
        }
    }

    private fun validate(): Boolean {
        /*txtLayoutFullName!!.error = null*/

        if (selectedImageUri == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Snackbar.make(
                    findViewById(R.id.constraint_layout),
                    "Please select a picture !",
                    Snackbar.LENGTH_SHORT
                ).setBackgroundTint(getColor(R.color.primaryLightColor)).show()
            }
            return false
        }
        return true
    }



    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}