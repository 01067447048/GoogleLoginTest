package com.jaehyeon.compose.googlelogintest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.jaehyeon.compose.googlelogintest.databinding.ActivityMainBinding
import com.jaehyeon.compose.googlelogintest.utils.ActionState
import com.jaehyeon.compose.googlelogintest.utils.EventObserver
import com.jaehyeon.compose.googlelogintest.utils.FragmentNavigatorImpl
import com.jaehyeon.compose.googlelogintest.utils.FragmentType
import javax.security.auth.login.LoginException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private val gso by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestId()
            .build()
    }
    private val model: MainViewModel by viewModels()
    private val navi = FragmentNavigatorImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navi.init()
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                GoogleSignIn.getSignedInAccountFromIntent(result.data).apply {
                    addOnSuccessListener {
                        model.setId(it.id ?: "")
                        model.setEmail(it.email ?: "")
                        navi.navigateTo(FragmentType.SUB)
                    }

                    addOnCanceledListener {
                        Log.e(javaClass.simpleName, "onCreate: onCanceledListener", )
                    }

                    addOnFailureListener {
                        Log.e(javaClass.simpleName, "onCreate: ${it.localizedMessage}", )
                    }
                }
            }
        }

        model.action.observe(this, EventObserver { state ->
            when (state) {
                ActionState.ActionLogin -> {
                    Log.e(javaClass.simpleName, "onCreate: $state", )
                    GoogleSignIn.getClient(this, gso).also { client ->
                        activityResultLauncher.launch(client.signInIntent)
                    }
                }

                ActionState.ActionLogout -> {
                    GoogleSignIn.getClient(this, gso).signOut().apply {
                        addOnSuccessListener {
                            Toast.makeText(this@MainActivity, "logout", Toast.LENGTH_SHORT).show()
                            navi.navigateTo(FragmentType.MAIN)
                        }
                    }
                }

                ActionState.ActionRevoke -> {
                    GoogleSignIn.getClient(this, gso).revokeAccess().apply {
                        addOnSuccessListener {
                            Toast.makeText(this@MainActivity, "revoke", Toast.LENGTH_SHORT).show()
                            navi.navigateTo(FragmentType.MAIN)
                        }
                    }
                }

            }
        })
    }

    override fun onStart() {
        super.onStart()
        GoogleSignIn.getLastSignedInAccount(this)?.let {
            model.setId(it.id ?: "")
            model.setEmail(it.email ?: "")
            navi.navigateTo(FragmentType.SUB)
        }
    }

}