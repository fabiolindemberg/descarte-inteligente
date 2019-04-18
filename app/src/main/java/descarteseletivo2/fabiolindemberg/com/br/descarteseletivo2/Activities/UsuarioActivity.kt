package descarteseletivo2.fabiolindemberg.com.br.descarteseletivo2.Activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import descarteseletivo2.fabiolindemberg.com.br.descarteseletivo2.R
import kotlinx.android.synthetic.main.activity_usuario.*

class UsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        btnCancelar.setOnClickListener({view ->
            finish()
        })

        btnEnviar.setOnClickListener({view ->

            if(!etSenha.text.toString().equals(etConfirmacaoSenha.text.toString())){
                Toast.makeText(this, "A senhas digitadas são diferentes!", Toast.LENGTH_LONG).show()
            }else if(etEmail.text.isEmpty()){
                Toast.makeText(this, "E-mail não preenchido!", Toast.LENGTH_LONG).show()
            }else if(etSenha.text.isEmpty()){
                Toast.makeText(this, "Senha não preenchida!", Toast.LENGTH_LONG).show()
            }else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        etEmail.text.toString(),
                        etSenha.text.toString()).addOnCompleteListener({ task ->
                            if(task.isSuccessful){
                                val data = Intent()
                                data.putExtra("email", etEmail.text.toString())
                                data.putExtra("senha", etSenha.text.toString())
                                setResult(Activity.RESULT_OK, data)
                                Toast.makeText(this, "Usuário adicionado com sucesso", Toast.LENGTH_LONG).show()
                                finish()
                            }else{
                                Toast.makeText(this, "Não foi possivel criar o usuário!", Toast.LENGTH_LONG).show()
                                Log.e("Erro ao criar usuário:", task.exception.toString())
                            }
                        })
            }
        })
    }
}
