package com.syfuzzaman.cloudfirestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.syfuzzaman.cloudfirestore.databinding.FragmentGetStartedBinding


class GetStartedFragment : Fragment() {

    private lateinit var binding : FragmentGetStartedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetStartedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGetStarted.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigateToLoginFragment)
        }

    }

}