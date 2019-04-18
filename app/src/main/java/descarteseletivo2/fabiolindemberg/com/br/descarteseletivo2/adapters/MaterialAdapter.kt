package descarteseletivo2.fabiolindemberg.com.br.descarteseletivo2.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import descarteseletivo2.fabiolindemberg.com.br.descarteseletivo2.R
import descarteseletivo2.fabiolindemberg.com.br.descarteseletivo2.model.Material
import kotlinx.android.synthetic.main.material_item.view.*

class MaterialAdapter(var materiais: ArrayList<Material>) : RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
        val linearLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.material_item,parent, false) as LinearLayout
        return MaterialViewHolder(linearLayout)
    }

    override fun getItemCount(): Int {
        return this.materiais.size
    }

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
        holder.layout.tvMaterial.text = this.materiais[position].descricao
        holder.layout.tvDescricao.text = this.materiais[position].detalhes
        holder.layout.ivMaterial.setImageBitmap(this.materiais[position].image)
    }

    class MaterialViewHolder(var layout: LinearLayout) : RecyclerView.ViewHolder(layout) {

    }


}