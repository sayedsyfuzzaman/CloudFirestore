package com.syfuzzaman.cloudfirestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.syfuzzaman.cloudfirestore.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseFirestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root

        loadData()

        binding.btnSave.setOnClickListener {
            saveData()
            loadData()
        }

        binding.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            Toast.makeText(this, "User signed out", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            finishAffinity()
        }
        setContentView(view)
    }

    private fun loadData(){
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()
        val uId = firebaseAuth.currentUser?.uid

        val documentReference:DocumentReference = firebaseFirestore.collection("users").document(uId!!)
        documentReference.get()
            .addOnSuccessListener { document ->
                if (document != null) {

                    val name = document.get("name").toString()
                    val phone = document.get("phone").toString()
                    val designation = document.get("designation").toString()
                    val company = document.get("company").toString()

                    binding.inputName.setText(name)
                    binding.inputPhone.setText(phone)
                    binding.inputDesignation.setText(designation)
                    binding.inputCompany.setText(company)

                    Log.d("Data", "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d("Data", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Data", "get failed with ", exception)
            }
    }

    private fun saveData(){
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()
        val uId = firebaseAuth.currentUser?.uid

        val documentReference:DocumentReference =firebaseFirestore.collection("users").document(uId!!)

        var user : HashMap<String, String>
                = HashMap<String, String> ()

        user.put("name", binding.inputName.text.toString())
        user.put("phone", binding.inputPhone.text.toString())
        user.put("designation", binding.inputDesignation.text.toString())
        user.put("company", binding.inputCompany.text.toString())

        documentReference.set(user).addOnCompleteListener {
            Toast.makeText(this, "Information saved successfully.", Toast.LENGTH_SHORT).show()
        }
    }
}