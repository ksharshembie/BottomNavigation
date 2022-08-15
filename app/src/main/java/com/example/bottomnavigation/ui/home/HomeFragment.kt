package com.example.bottomnavigation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bottomnavigation.R
import com.example.bottomnavigation.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigate()
        parentFragmentManager.setFragmentResultListener(
            "key_bundle",
            viewLifecycleOwner
        ){ requestkey, bundle ->
            val text = bundle.getString("key")
            binding.textOtherfragment.text = text
        }
    }

    private fun navigate(){
        binding.btnButton.setOnClickListener {
            findNavController().navigate(R.id.secondFragment)
        }

        //findNavController().navigate(R.layout.fragment_second)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}