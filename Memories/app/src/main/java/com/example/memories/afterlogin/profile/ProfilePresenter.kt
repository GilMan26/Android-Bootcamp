package com.example.memories.afterlogin.profile

import android.util.Log
import com.example.memories.repository.DataManager
import com.example.memories.repository.LoginHelper
import com.example.memories.repository.User

class ProfilePresenter(var iProfileView: IProfileContract.IProfileView): IProfileContract.IProfilePresenter{

    override fun changeProfile() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getDetials() {
        iProfileView.showProgress()
        DataManager.getUser(object : DataManager.IUserDataCallback{

            override fun onSuccess(user: User) {
                iProfileView.inflateData(user.name, user.url)
                iProfileView.hideProgress()
            }


            override fun onFailure(ack: String) {
                Log.d("profile", "fail")
            }

        })
    }

    override fun logout() {
        LoginHelper.signOut(object : LoginHelper.SignOutListener{
            override fun onSignout() {
                Log.d("profile", "signout success")
                iProfileView.logout()
            }
        })
    }
}