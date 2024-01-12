package com.dev.myposts.presentation.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dev.myposts.R
import com.dev.myposts.databinding.FragmentLogInBinding
import com.dev.myposts.presentation.activity.MainActivity
import com.dev.myposts.presentation.activity.PostApp
import com.dev.myposts.presentation.viewModel.LogInViewModel
import com.dev.myposts.presentation.viewModel.ViewModelFactory
import java.lang.RuntimeException
import javax.inject.Inject

class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding: FragmentLogInBinding
        get() = _binding ?: throw RuntimeException("FragmentLogInBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[LogInViewModel::class.java]
    }
    private val component by lazy {
        (requireActivity().application as PostApp).component
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.reLogin()
        viewModel.isLoggedIn.observe(viewLifecycleOwner) {
            if (it) {
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }
        binding.buttonLogIn.setOnClickListener {
            val name = binding.editTextUserName.text.toString()
            val password = binding.editTextPassword.text.toString()
            viewModel.logIn(name, password)
        }
        binding.textViewSighUp.setOnClickListener {
            launchRegisterFragment()
        }
    }

    private fun launchRegisterFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.logIn_container,RegisterFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}