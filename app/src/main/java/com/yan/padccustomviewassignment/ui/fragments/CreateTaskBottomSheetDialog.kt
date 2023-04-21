package com.yan.padccustomviewassignment.ui.fragments

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yan.padccustomviewassignment.R
import com.yan.padccustomviewassignment.databinding.DialogCreateTaskBottomSheetBinding
import com.yan.padccustomviewassignment.ui.activities.MainActivity

class CreateTaskBottomSheetDialog :
    BottomSheetDialogFragment(R.layout.dialog_create_task_bottom_sheet) {
    companion object {
        const val TAG = "CreateTaskDialog"

        fun new() = CreateTaskBottomSheetDialog()
    }

    private var _binding: DialogCreateTaskBottomSheetBinding? = null
    private val mBinding: DialogCreateTaskBottomSheetBinding
        get() = checkNotNull(_binding) {
            "Cannot create binding because it is null!"
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DialogCreateTaskBottomSheetBinding.bind(view)
        mBinding.btnCreateTask.setOnClickListener {
            startActivity(MainActivity.newIntent(requireContext()))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}