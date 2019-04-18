package descarteseletivo2.fabiolindemberg.com.br.descarteseletivo2.extensions

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import descarteseletivo2.fabiolindemberg.com.br.descarteseletivo2.Activities.MaterialActivity

fun AppCompatActivity.startMaterialActivity(){
    val intent = Intent(this, MaterialActivity::class.java)
    startActivity(intent)
}
