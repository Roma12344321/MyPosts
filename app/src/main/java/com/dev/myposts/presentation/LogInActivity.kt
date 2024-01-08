package com.dev.myposts.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.dev.myposts.databinding.ActivityLogInBinding
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
        viewModel.isLoggedIn.observe(this) {
            if (it) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        binding.buttonLogIn.setOnClickListener {
            val name = binding.editTextUserName.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            viewModel.logIn(name,password)
        }
    }
}