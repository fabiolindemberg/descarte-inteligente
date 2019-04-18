package descarteseletivo2.fabiolindemberg.com.br.descarteseletivo2.Activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import descarteseletivo2.fabiolindemberg.com.br.descarteseletivo2.R
import descarteseletivo2.fabiolindemberg.com.br.descarteseletivo2.extensions.dismissKeyboard
import descarteseletivo2.fabiolindemberg.com.br.descarteseletivo2.extensions.startMaterialActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager
    private val TAG = "Mensagem"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase Auth
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        btnNovoUsuario.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, UsuarioActivity::class.java)

            if(!etEmail.text.isEmpty()){
                intent.putExtra("email", etEmail.text.toString())
            }

            startActivityForResult(intent, NOVO_USUARIO_RESULT_CODE)
        })

        btnLogin.setOnClickListener(View.OnClickListener {

            this.dismissKeyboard()

            if (etEmail.text.isEmpty() || etSenha.text.isEmpty()) {
                Toast.makeText(this, R.string.msg_login_info_obrigatorio, Toast.LENGTH_LONG).show()
            }else {
                progressBar_cyclic.visibility = View.VISIBLE

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        etEmail.text.toString(),
                        etSenha.text.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful) { 
                        this.startMaterialActivity()
                    } else {
                        val tag = resources.getString(R.string.msg_erro_login)
                        val msg = resources.getString(R.string.msg_login_invalido)
                        Toast.makeText(this, "${tag} ${msg}", Toast.LENGTH_LONG).show()
                    }
                }
                progressBar_cyclic.visibility = View.INVISIBLE
            }
        })

        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create()

        buttonFacebookLogin.setReadPermissions("email", "public_profile")
        buttonFacebookLogin.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d(TAG, "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d(TAG, "facebook:onCancel")
                // ...
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "facebook:onError", error)
                // ...
            }
        })
        // ...


        btnGoogleLogin.setOnClickListener(View.OnClickListener {
            Log.e("Erro:", "Login através do Google não implementado!")
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == NOVO_USUARIO_RESULT_CODE){
            if(resultCode == Activity.RESULT_OK){
                if(data != null){
                    if(FirebaseAuth.getInstance().currentUser == null) {
                        //data.getCharExtra("email", "")
                        etEmail.setText(data.getStringExtra("email"))
                        etSenha.setText(data.getStringExtra("senha"))
                    }else{
                       this.startMaterialActivity()
                    }
                }
            }
        }else{
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)

    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }

                    // ...
                }
    }

    private fun updateUI(user: FirebaseUser?) {
        this.startMaterialActivity()
        /*
        hideProgressDialog()
        if (user != null) {
            status.text = getString(R.string.facebook_status_fmt, user.displayName)
            detail.text = getString(R.string.firebase_status_fmt, user.uid)

            buttonFacebookLogin.visibility = View.GONE
            buttonFacebookSignout.visibility = View.VISIBLE
        } else {
            status.setText(R.string.signed_out)
            detail.text = null

            buttonFacebookLogin.visibility = View.VISIBLE
            buttonFacebookSignout.visibility = View.GONE
        }
        */
    }

    companion object {
        val NOVO_USUARIO_RESULT_CODE = 101
    }
}
