package com.syfuzzaman.cloudfirestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.finishAffinity
import com.google.firebase.auth.FirebaseAuth
import com.syfuzzaman.cloudfirestore.databinding.FragmentGetStartedBinding
import com.syfuzzaman.cloudfirestore.databinding.FragmentLoginBinding


class loginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        firebaseAuth = FirebaseAuth.getInstance()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login(){
        val email = binding.inputEmail.toString()
        val password = binding.inputPassword.toString()

        if (email.isBlank() || password.isBlank()){
            Toast.makeText(context, "Email or password cannot be empty", Toast.LENGTH_SHORT).show()
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "Authentication failed!", Toast.LENGTH_SHORT).show()

            }
        }
    }


}