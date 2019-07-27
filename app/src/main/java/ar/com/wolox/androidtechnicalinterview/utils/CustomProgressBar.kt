package ar.com.wolox.androidtechnicalinterview.utils

import android.support.v4.app.FragmentActivity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import ar.com.wolox.androidtechnicalinterview.R

class CustomProgressBar(private val context: FragmentActivity) {

    // Attributes
    private var blockUI = false
    private var childView: View
    private var isLoading = false
    private var viewGroup: ViewGroup? = null

    init {
        viewGroup = context.findViewById(android.R.id.content)
        childView = context.layoutInflater.inflate(R.layout.view_custom_progress_bar, null)
    }

    // Public methods
    fun hide() {
        if (blockUI) {
            unblockUI()
        }
        isLoading = false
        context.runOnUiThread { viewGroup!!.removeView(childView) }
    }

    fun show(blockUI: Boolean?) {
        this.blockUI = blockUI!!
        show()
    }

    fun show() {
        hide()
        if (blockUI) blockUI() else unblockUI()
        isLoading = true
        viewGroup!!.addView(childView)
    }

    // Private methods
    private fun blockUI() {
        context.window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun unblockUI() {
        context.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

}