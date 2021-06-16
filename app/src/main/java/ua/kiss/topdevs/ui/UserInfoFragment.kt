package ua.kiss.topdevs.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import ua.kiss.topdevs.R
import ua.kiss.topdevs.databinding.UserInfoFragmentBinding
import ua.kiss.topdevs.models.User
import ua.kiss.topdevs.utils.Status

class UserInfoFragment: BaseFragment() {
    private val binding by lazy { UserInfoFragmentBinding.inflate(layoutInflater) }
    private val args: UserInfoFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View { return binding.root }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userId = args.userId
        viewModel.getUser(userId)
        setObserverForUser()
    }

    private fun setObserverForUser() {
        viewModel.getUserLiveData().observe(this, { res ->
            when (res.status) {
                Status.LOADING -> {}
                Status.SUCCESS -> res.data?.let { setUI(it) }
                Status.ERROR -> res.message?.let { message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setUI(user: User) {
        Glide.with(requireContext()).load(user.avatar).into(binding.userInfoImage)
        binding.userInfoName.text = user.name
        binding.userInfoDate.text = requireActivity()
                .getString(R.string.user_list_item_date, user.getFormatDate())
    }
}