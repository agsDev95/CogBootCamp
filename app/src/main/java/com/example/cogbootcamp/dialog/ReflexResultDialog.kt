package com.example.cogbootcamp.dialog

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.cogbootcamp.R
import com.example.cogbootcamp.databinding.LayoutReflexResultsDialogBinding
import com.example.cogbootcamp.view.ReflexLeaderboardActivity

class ReflexResultDialog(private val responseTime: Float): DialogFragment() {
    private lateinit var binding: LayoutReflexResultsDialogBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LayoutReflexResultsDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        setResultResponseTime(responseTime)
        setOnClickListeners()
    }

    private fun setResultResponseTime(responseTime: Float) {
        val resultString = responseTime.toString() + "s"
        setResultTimeText(resultString)
    }

    private fun setResultTimeText(resultString: String) {
        val spannable = SpannableString(resultString)

        // highlight the decimal
        spannable.setSpan(
            ForegroundColorSpan(dialog?.context!!.getColor(R.color.theme_primary)),
            1,
            2,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // highlight seconds unit
        spannable.setSpan(
            ForegroundColorSpan(dialog?.context!!.getColor(R.color.theme_primary)),
            5,
            6,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.responseTimeResult.text = spannable

    }

    private fun setOnClickListeners() {
        binding.saveButton.setOnClickListener {
            if(binding.initialsEntry.text.toString() != "") {
                goToResultsLeaderBoard(binding.initialsEntry.text.toString())
                dismiss()
            }
        }
    }

    private fun goToResultsLeaderBoard(userInitials: String) {
        val intent =  Intent(dialog?.context, ReflexLeaderboardActivity::class.java)
        intent.putExtra("USER_INITIALS", userInitials)
        intent.putExtra("USER_TIME", responseTime)
        startActivity(intent)
    }
}