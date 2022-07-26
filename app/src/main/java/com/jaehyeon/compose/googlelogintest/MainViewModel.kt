package com.jaehyeon.compose.googlelogintest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaehyeon.compose.googlelogintest.utils.ActionState
import com.jaehyeon.compose.googlelogintest.utils.Event

/**
 * Created by Jaehyeon on 2022/07/26.
 */
class MainViewModel: ViewModel() {

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> get() = _id

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _action = MutableLiveData<Event<ActionState>>()
    val action: LiveData<Event<ActionState>> get() = _action

    fun setId(id : String) {
        _id.value = id
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun onAction() {
        _action.value = Event(ActionState.ActionLogin)
    }

    fun onActionLogout() {
        _action.value = Event(ActionState.ActionLogout)
    }

    fun onActionRevoke() {
        _action.value = Event(ActionState.ActionRevoke)
    }

}