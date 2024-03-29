package com.lynn.foodies.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import coil.transform.CircleCropTransformation
import com.lynn.foodies.R
import com.lynn.foodies.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
        observeEditMode()
        observeProfileData()
    }

    private fun observeProfileData() {
        viewModel.profileData.observe(viewLifecycleOwner) {
            binding.ivProfile.load(it.profileImg) {
                crossfade(true)
                error(R.drawable.ic_tab_profile)
                transformations(CircleCropTransformation())
            }
            binding.nameEditText.setText(it.name)
            binding.usernameEditText.setText(it.username)
            binding.emailEditText.setText(it.email)
        }
    }

    private fun observeEditMode() {
        viewModel.isEditMode.observe(viewLifecycleOwner) {
            if (it == true){
                binding.btnEdit.text = getString(R.string.text_save)
                binding.emailEditText.isEnabled = it
                binding.nameEditText.isEnabled = it
                binding.usernameEditText.isEnabled = it
            }else{
                binding.btnEdit.text = getString(R.string.text_edit)
                binding.emailEditText.isEnabled = it
                binding.nameEditText.isEnabled = it
                binding.usernameEditText.isEnabled = it
            }
        }

    }

    private fun setClickListener() {
        binding.btnEdit.setOnClickListener {
            viewModel.changeEditMode()
        }

    }


}