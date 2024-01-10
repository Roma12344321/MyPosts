package com.dev.myposts.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dev.myposts.databinding.ActivityRegisterBinding
import com.dev.myposts.presentation.viewModel.MainViewModel
import com.dev.myposts.presentation.viewModel.ViewModelFactory
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as PostApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        setContentView(binding.root)
        viewModel.isRegistered.observe(this) {
            if (it) {
                Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        binding.buttonRegister.setOnClickListener {
            viewModel.register(
                binding.editTextUserName.text.toString(),
                binding.editTextEmail.text.toString(),
                binding.editTextPassword.text.toString()
            )
        }
    }
}