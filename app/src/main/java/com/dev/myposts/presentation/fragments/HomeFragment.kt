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
import com.dev.myposts.databinding.FragmentHomeBinding
import com.dev.myposts.presentation.activity.LogInActivity
import com.dev.myposts.presentation.adapter.PostAdapter
import com.dev.myposts.presentation.activity.PostApp
import com.dev.myposts.presentation.viewModel.MainViewModel
import com.dev.myposts.presentation.viewModel.ViewModelFactory
import java.lang.RuntimeException
import javax.inject.Inject

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding : FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as PostApp).component
    }
    private val adapter = PostAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPosts.adapter = adapter
        viewModel.showProgressBar.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBarLoadingPost.visibility = View.VISIBLE
            }
            else {
                binding.progressBarLoadingPost.visibility = View.GONE
            }
        }
        viewModel.posts.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.textViewEmpty.visibility = View.VISIBLE
            }
            else {
                binding.textViewEmpty.visibility = View.GONE
            }
            adapter.submitList(it)
        }
        viewModel.isLoggedOut.observe(viewLifecycleOwner) {
            if (it) {
                val intent = Intent(requireContext(), LogInActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }
        binding.textViewLogOut.setOnClickListener {
            viewModel.logOut()
        }
        binding.textViewCreatePost.setOnClickListener {
            launchCreatePostFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllPosts()
    }

    private fun launchCreatePostFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, CreatePostFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() : Fragment {
            return HomeFragment()
        }
    }
}