package descarteseletivo2.fabiolindemberg.com.br.descarteseletivo2

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNovoUsuario.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, UsuarioActivity::class.java)

            if(!etEmail.text.isEmpty()){
                intent.putExtra("email", etEmail.text.toString())
            }

            startActivityForResult(intent, NOVO_USUARIO_RESULT_CODE)
        })

        btnLogin.setOnClickListener(View.OnClickListener {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    etEmail.text.toString(),
                    etSenha.text.toString()).addOnCompleteListener({ task ->
                if(task.isSuccessful){
                    startMaterialActivity()
                }else{
                    Toast.makeText(this, "Erro ao fazer login: ${task.exception.toString()}", Toast.LENGTH_LONG).show()
                }
            })
        })

        btnFacebookLoggin.setOnClickListener(View.OnClickListener {
            Log.e("Erro:", "Login através do Facebook não implementado!")
        })

        btnGoogleLogin.setOnClickListener(View.OnClickListener {
            Log.e("Erro:", "Login através do Google não implementado!")
        })

    }

    fun startMaterialActivity(){
        val intent = Intent(this, MaterialActivity::class.java)
        startActivity(intent)
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
                       startMaterialActivity()
                    }
                }
            }
        }
    }

    companion object {
        val NOVO_USUARIO_RESULT_CODE = 101
    }
}
