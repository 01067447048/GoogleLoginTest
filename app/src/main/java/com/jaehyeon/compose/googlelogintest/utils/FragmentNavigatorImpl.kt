package com.jaehyeon.compose.googlelogintest.utils

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.jaehyeon.compose.googlelogintest.MainFragment
import com.jaehyeon.compose.googlelogintest.R
import com.jaehyeon.compose.googlelogintest.SubFragment

/**
 * Created by Jaehyeon on 2022/07/26.
 */
class FragmentNavigatorImpl(private val activity: FragmentActivity): FragmentNavigator {

    private val mainFragment = MainFragment.newInstance()
    private val subFragment = SubFragment.newInstance()

    override fun navigateTo(screen: FragmentType) {
        when (screen) {
            FragmentType.MAIN -> {
                activity.supportFragmentManager.commit {
                    replace(R.id.fragment_container, mainFragment)
                }
            }

            FragmentType.SUB -> {
                activity.supportFragmentManager.commit {
                    replace(R.id.fragment_container, subFragment)
                }
            }
        }
    }

    override fun init() {
        activity.supportFragmentManager.commit {
            replace(R.id.fragment_container, mainFragment)
        }
    }
}