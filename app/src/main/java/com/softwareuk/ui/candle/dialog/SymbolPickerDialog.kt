package com.softwareuk.ui.candle.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.CandleDataSet
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.softwareuk.R
import kotlinx.android.synthetic.main.dialog_actions_bottom_sheet.*

class SymbolPickerDialog : BottomSheetDialogFragment() {

    companion object {

        fun show(
            fragment: Fragment,
            title: String = "",
            actions: List<CandleDataSet>,
            onItemClicked: (CandleDataSet) -> Unit
        ) {
            if (fragment.isStateSaved) return
            SymbolPickerDialog()
                .apply {
                    this.title = title
                    this.actions = actions
                    this.onItemClicked = onItemClicked
                }
                .show(fragment.childFragmentManager, SymbolPickerDialog::class.java.simpleName)
        }
    }

    private var title: String? = null
    private var actions: List<CandleDataSet>? = null
    private var onItemClicked: ((CandleDataSet) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext())
        // dirty hack to prevent dialog restoring
        if (savedInstanceState != null) dismiss()
        dialog.setContentView(R.layout.dialog_actions_bottom_sheet)
        dialog.textView.text = title
        dialog.textView.isVisible = !title.isNullOrEmpty()
        dialog.recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = StockAdapter {
            onItemClicked?.invoke(it)
            dismiss()
        }
        actions?.let { adapter.setItems(it) }
        dialog.recyclerView.adapter = adapter
        return dialog
    }

    override fun onStop() {
        super.onStop()
        dismissAllowingStateLoss()
    }
}