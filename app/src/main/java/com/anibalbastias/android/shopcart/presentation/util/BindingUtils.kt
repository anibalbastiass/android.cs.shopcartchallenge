package com.anibalbastias.android.shopcart.presentation.util

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.anibalbastias.android.shopcart.presentation.util.adapter.base.BaseBindClickHandler
import com.anibalbastias.android.shopcart.presentation.util.adapter.base.SingleLayoutBindRecyclerAdapter
import com.anibalbastias.android.shopcart.presentation.util.widget.WrapContentLinearLayoutManager

@BindingAdapter("setImageUrl")
fun ImageView.setImageUrl(imageUrl: String?) {
    imageUrl?.let {
        loadImageWithoutPlaceholder(imageUrl)
    }
}

@BindingAdapter(value = ["loadAdapterData", "loadAdapterLayout", "loadAdapterListener"], requireAll = false)
fun <T> RecyclerView.loadAdapterData(list: MutableList<T>?, layout: Int?, callback: BaseBindClickHandler<T>? = null) {
    context?.run {
        layout?.let { layoutId ->
            layoutManager = WrapContentLinearLayoutManager(context)
            adapter = SingleLayoutBindRecyclerAdapter(layoutId, list, callback)
            runLayoutAnimation()
        }
    }
}