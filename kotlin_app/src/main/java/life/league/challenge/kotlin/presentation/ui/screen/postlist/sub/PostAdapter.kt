package life.league.challenge.kotlin.presentation.ui.screen.postlist.sub

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.core.entity.Post
import life.league.challenge.kotlin.presentation.OnPostClickListener
import life.league.challenge.kotlin.presentation.ui.common.animation.BounceClickEffectAnimator
import life.league.challenge.kotlin.presentation.util.ImageLoaderUtils

class PostAdapter(
    private val onItemClick: OnPostClickListener
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private val list = mutableListOf<Post>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        holder.bind(holder.itemView.context, position)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun bind(posts: List<Post>) {
        list.clear()
        list.addAll(posts)
        notifyDataSetChanged()

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private var imageView: ShapeableImageView = view.findViewById(R.id.avatar_imageView)
        private var userNameTextView = view.findViewById<AppCompatTextView>(R.id.username_TextView)
        private var titleTextView = view.findViewById<AppCompatTextView>(R.id.title_TextView)
        private var descriptionTextView =
            view.findViewById<AppCompatTextView>(R.id.description_TextView)
        // Animation

        init {
            itemView.setOnClickListener(this)
            // For  click animation
            BounceClickEffectAnimator(view)
        }

        override fun onClick(view: View?) {
            onItemClick(list[adapterPosition])
        }


        fun bind(context: Context, position: Int) {
            imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.defaultColor))
            ImageLoaderUtils.with(context).placeholder(R.drawable.noimage)
                .load(list[position].user.avatar)
                .into(imageView)

            titleTextView.text = list[position].title
            descriptionTextView.text = list[position].description
            userNameTextView.text = list[position].user.userName
        }
    }


}