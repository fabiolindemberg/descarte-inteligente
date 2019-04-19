package descarteseletivo2.fabiolindemberg.com.br.descarteseletivo2.extensions

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import descarteseletivo2.fabiolindemberg.com.br.descarteseletivo2.Activities.MaterialActivity
import descarteseletivo2.fabiolindemberg.com.br.descarteseletivo2.Activities.UsuarioActivity

val AppCompatActivity.NOVO_USUARIO_RESULT_CODE: Int get() = 101

fun AppCompatActivity.startMaterialActivity(){
    val intent = Intent(this, MaterialActivity::class.java)
    startActivity(intent)
}

fun AppCompatActivity.startUsuarioActivity(userEmail: String){
    val intent = Intent(this, UsuarioActivity::class.java)

    if(userEmail.isEmpty()){
        intent.putExtra("email", userEmail)
    }

    startActivityForResult(intent, NOVO_USUARIO_RESULT_CODE)
}
