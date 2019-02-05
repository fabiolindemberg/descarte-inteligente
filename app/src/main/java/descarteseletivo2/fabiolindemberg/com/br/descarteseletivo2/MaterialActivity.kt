package descarteseletivo2.fabiolindemberg.com.br.descarteseletivo2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_material.*

class MaterialActivity : AppCompatActivity() {

    override fun isInMultiWindowMode(): Boolean {
        return super.isInMultiWindowMode()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)

        buildRvMaterial()
    }

    fun buildRvMaterial(){
        rvMaterial.layoutManager = LinearLayoutManager(this)
        rvMaterial.adapter = MaterialAdapter(carregarMateriais())
    }

    fun carregarMateriais(): ArrayList<Material> {
        var materiais = ArrayList<Material>()
        materiais.add(Material(1, "Plástico", "Sacos, descartáveis, garrafas pet, plásticos em geral"))
        materiais.add(Material(2, "Papel", "Papelão, embalagens de papel, livros velhos, etc..."))
        materiais.add(Material(3, "Metal", "Latas de alumínio, panelas, tampinhas de garrafas, etc..."))
        materiais.add(Material(4, "Vidro", "Vidros em geral"))
        materiais.add(Material(5, "Pilha/Bateria", "Pilhas, baterias de celulares e notbooks"))
        materiais.add(Material(6, "Eletrônicos", "Computadores, monitores, tvs, celulares, aparelhos de som, etc..."))
        return materiais
    }
}
