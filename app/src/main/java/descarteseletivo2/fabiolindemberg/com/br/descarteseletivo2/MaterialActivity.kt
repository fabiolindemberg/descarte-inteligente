package descarteseletivo2.fabiolindemberg.com.br.descarteseletivo2

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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

        val iconePlatico = BitmapFactory.decodeResource(this.resources, R.drawable.plastic)
        val iconePapel = BitmapFactory.decodeResource(this.resources, R.drawable.paper_bin)
        val iconeMetal = BitmapFactory.decodeResource(this.resources, R.drawable.metal)
        val iconeVidro = BitmapFactory.decodeResource(this.resources, R.drawable.glass_bin)
        val iconePilha = BitmapFactory.decodeResource(this.resources, R.drawable.battery)
        val iconeEletronico = BitmapFactory.decodeResource(this.resources, R.drawable.ewaste)

        var materiais = ArrayList<Material>()
        materiais.add(Material(6, "Eletrônicos", "Computadores, monitores, tvs, celulares, aparelhos de som, etc...", iconeEletronico))
        materiais.add(Material(3, "Metal", "Latas de alumínio, panelas, tampinhas de garrafas, etc...", iconeMetal))
        materiais.add(Material(2, "Papel", "Papelão, embalagens de papel, livros velhos, etc...", iconePapel))
        materiais.add(Material(5, "Pilha/Bateria", "Pilhas, baterias de celulares e notbooks", iconePilha))
        materiais.add(Material(1, "Plástico", "Sacos, descartáveis, garrafas pet, plásticos em geral", iconePlatico))
        materiais.add(Material(4, "Vidro", "Vidros em geral", iconeVidro))
        return materiais
    }
}
