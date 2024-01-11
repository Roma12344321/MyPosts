package com.dev.myposts.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.dev.myposts.databinding.ActivityLogInBinding
import com.dev.myposts.presentation.viewModel.MainViewModel
import com.dev.myposts.presentation.viewModel.ViewModelFactory
import javax.inject.Inject

class LogInActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding by lazy {
        ActivityLogInBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
    }
    private val component by lazy {
        (application as PostApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        setContentView(binding.root)
        viewModel.reLogin()
        viewModel.isLoggedIn.observe(this) {
            if (it) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        binding.buttonLogIn.setOnClickListener {
            val name = binding.editTextUserName.text.toString()
            val password = binding.editTextPassword.text.toString()
            viewModel.logIn(name,password)
        }
        binding.textViewSighUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}