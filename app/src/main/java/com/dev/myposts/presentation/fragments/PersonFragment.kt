package com.dev.myposts.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dev.myposts.R
import com.dev.myposts.databinding.FragmentPersonBinding
import com.dev.myposts.presentation.activity.PostApp
import com.dev.myposts.presentation.adapter.PostAdapter
import com.dev.myposts.presentation.viewModel.MainViewModel
import com.dev.myposts.presentation.viewModel.ViewModelFactory
import java.lang.RuntimeException
import javax.inject.Inject

class PersonFragment : Fragment() {

    private var _binding : FragmentPersonBinding? = null
    private val binding : FragmentPersonBinding
        get() = _binding ?: throw RuntimeException("FragmentPersonBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
    }
    private val component by lazy {
        (requireActivity().application as PostApp).component
    }

    private val adapter by lazy {
        PostAdapter()
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
        _binding = FragmentPersonBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMyPosts.adapter = adapter
        viewModel.posts.observe(viewLifecycleOwner) {
            adapter.submitList(it.filter { it.isItMy })
        }

        viewModel.getAllPosts()
    }


    companion object {
        fun newInstance() : Fragment {
            return PersonFragment()
        }
    }
}