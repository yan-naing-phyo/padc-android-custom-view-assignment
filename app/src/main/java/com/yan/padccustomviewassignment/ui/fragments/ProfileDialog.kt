package com.yan.padccustomviewassignment.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import com.yan.padccustomviewassignment.R
import com.yan.padccustomviewassignment.databinding.DialogProfileBinding

class ProfileDialog : BottomSheetDialogFragment(R.layout.dialog_profile),
    TabLayout.OnTabSelectedListener {
    companion object {
        const val TAG = "ProfileDialog"

        fun get(): ProfileDialog = ProfileDialog()
    }

    private var _binding: DialogProfileBinding? = null
    private val mBinding: DialogProfileBinding
        get() = checkNotNull(_binding) {
            "Cannot get binding because it is null!"
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DialogProfileBinding.bind(view)
        replaceFragment(R.id.fragmentContainer, TaskListFragment())
        setUpProfileTabLayout()
        setUpListeners()
    }

    private fun setUpProfileTabLayout() {
        mBinding.tabLayoutProfile.apply {
            addTab(newTab().setText(R.string.label_project_tasks))
            addTab(newTab().setText(R.string.label_contacts))
            addTab(newTab().setText(R.string.label_about_you))
        }
    }

    private fun setUpListeners() {
        mBinding.tabLayoutProfile.addOnTabSelectedListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when (tab?.position) {
            0 -> {
                replaceFragment(R.id.fragmentContainer, TaskListFragment())
            }
            1 -> {
                replaceFragment(R.id.fragmentContainer, ContactFragment())
            }
            2 -> {
                replaceFragment(R.id.fragmentContainer, AboutMeFragment())
            }
        }
    }

    private fun replaceFragment(containerId: Int, fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .commit()
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }
}