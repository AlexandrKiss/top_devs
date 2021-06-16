package ua.kiss.topdevs.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ua.kiss.topdevs.R
import ua.kiss.topdevs.databinding.UserListItemBinding
import ua.kiss.topdevs.models.ApiUser
import ua.kiss.topdevs.other.ClickCallback

class UserListAdapter: RecyclerView.Adapter<UserListAdapter.UserListHolder>() {
    private lateinit var userList: List<ApiUser>
    private lateinit var clickCallback: ClickCallback

    class UserListHolder(binding: UserListItemBinding): RecyclerView.ViewHolder(binding.root) {
        val root = binding.root
        val avatar = binding.userItemAvatar
        val name = binding.userItemName
        val date = binding.userItemDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserListItemBinding.inflate(inflater, parent, false)
        return UserListHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListHolder, position: Int) {
        val user = userList[position]
        val context = holder.root.context

        Glide.with(context).load(user.avatar).into(holder.avatar)
        holder.name.text = user.name
        holder.date.text = context.getString(R.string.user_list_item_date, user.getFormatDate())
        holder.root.setOnClickListener {
            clickCallback.onClick(user)
        }
    }

    override fun getItemCount(): Int = userList.size

    fun setList(userList: List<ApiUser>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    fun attachCallback(clickCallback: ClickCallback) {
        this.clickCallback = clickCallback
    }
}