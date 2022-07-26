package com.jaehyeon.compose.googlelogintest.utils

sealed class ActionState {

    object ActionLogin: ActionState()
    object ActionLogout: ActionState()
    object ActionRevoke: ActionState()

}
