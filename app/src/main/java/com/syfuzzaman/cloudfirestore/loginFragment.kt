package com.syfuzzaman.cloudfirestore

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.Navigation
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
        binding.btnSignup.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }

    private fun login(){
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()

        if (email.isBlank() || password.isBlank()){
            Toast.makeText(context, "Email or password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), HomeActivity::class.java)
                requireActivity().startActivity(intent)
                finishAffinity(requireActivity())
            }else{
                Toast.makeText(context, "Authentication failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }


}