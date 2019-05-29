package com.example.memories.AfterLogin

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.content.DialogInterface
import android.util.Log
import com.example.memories.R


class AddAlbumDialog : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(context)
        builder.setTitle("Create a New Album")
        builder.setView(View.inflate(context, R.layout.add_album_dialog, null))
        builder.setPositiveButton("Create") { dialog, which ->


        }
        builder.setNegativeButton("No") { dialog, which ->

        }

        builder.setCancelable(true)
        return super.onCreateDialog(savedInstanceState)
    }
}

interface IAlbumDialog{
    fun getDetials( title: String,  message: String)
}

