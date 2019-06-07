package com.example.memories

import android.content.Context
import android.support.v4.app.Fragment
import java.lang.Exception

open class BaseFragment : Fragment() {

    open lateinit var fragmentTransactionHandler: FragmentTransactionHandler

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context == null)
            throw Exception("context is null")
        else
            fragmentTransactionHandler = context as FragmentTransactionHandler
    }
}