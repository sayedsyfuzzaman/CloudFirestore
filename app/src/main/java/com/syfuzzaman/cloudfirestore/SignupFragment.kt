package com.syfuzzaman.cloudfirestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.syfuzzaman.cloudfirestore.databinding.FragmentLoginBinding
import com.syfuzzaman.cloudfirestore.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        val view = binding.root

        firebaseAuth = FirebaseAuth.getInstance()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignup.setOnClickListener {
            Signup()
        }

        binding.btnLogin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_loginFragment)
        }
    }

    private fun Signup(){

        var email = binding.inputEmail.text.toString()
        var password = binding.inputPassword.text.toString()
        var confirmPassword = binding.inputRetypePassword.text.toString()

        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()){
            Toast.makeText(context, "Email or password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        if(password != confirmPassword){
            Toast.makeText(context, "Password and Confirm password do not matched!", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("AAA Email:", email)
        Log.d("AAA Password:", password)
        Log.d("AAA ConfPass:", confirmPassword)

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(context, "Signup successful", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(context, "Sorry! Try again.", Toast.LENGTH_SHORT).show()
                }
            }
    }

}