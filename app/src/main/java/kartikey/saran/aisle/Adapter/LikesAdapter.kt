package kartikey.saran.aisle.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.content.Context;
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation
import kartikey.saran.aisle.Model.ProfileLike
import kartikey.saran.aisle.R

class LikesAdapter(private val mList: List<ProfileLike>): RecyclerView.Adapter<LikesAdapter.ViewHolder>() {

    lateinit var context : Context

    class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val imageView: ImageView = itemView.findViewById(R.id.iV_img)
        val textView: TextView = itemView.findViewById(R.id.tV_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_likes, parent, false)
        this.context = parent.context

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        Glide.with(context)
            .load(ItemsViewModel.avatar)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(holder.imageView)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.first_name
    }
}