package com.dev.myposts.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dev.myposts.databinding.FragmentCreatePostBinding
import com.dev.myposts.presentation.activity.PostApp
import com.dev.myposts.presentation.viewModel.CreatePostViewModel
import com.dev.myposts.presentation.viewModel.LogInViewModel
import com.dev.myposts.presentation.viewModel.MainViewModel
import com.dev.myposts.presentation.viewModel.ViewModelFactory
import java.lang.RuntimeException
import javax.inject.Inject

class CreatePostFragment : Fragment() {

    private var _binding: FragmentCreatePostBinding? = null
    private val binding: FragmentCreatePostBinding
        get() = _binding ?: throw RuntimeException("FragmentCreatePostBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[CreatePostViewModel::class.java]
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
        _binding = FragmentCreatePostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isPostCreated.observe(viewLifecycleOwner) {
            if (it) {
                activity?.supportFragmentManager?.popBackStack()
            }
        }
        binding.buttonCreate.setOnClickListener {
            viewModel.createPost(
                binding.editTextTitle.text.toString(),
                binding.editTextContent.text.toString()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): Fragment {
            return CreatePostFragment()
        }
    }
}