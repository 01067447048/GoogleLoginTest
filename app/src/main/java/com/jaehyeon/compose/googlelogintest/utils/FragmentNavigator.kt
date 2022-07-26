package com.jaehyeon.compose.googlelogintest.utils

/**
 * Created by Jaehyeon on 2022/07/26.
 */
interface FragmentNavigator {
    fun navigateTo(screen: FragmentType)
    fun init()
}