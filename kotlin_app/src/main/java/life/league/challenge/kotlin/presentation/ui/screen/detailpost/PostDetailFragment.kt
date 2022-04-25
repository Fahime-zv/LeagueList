package life.league.challenge.kotlin.presentation.ui.screen.detailpost

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.fragment.navArgs
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.presentation.architucture.BaseFragmentVM
import life.league.challenge.kotlin.presentation.util.ImageLoaderUtils
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PostDetailFragment : BaseFragmentVM<PostDetailViewModel>() {

    override val viewModel: PostDetailViewModel by viewModel {
        parametersOf(args.post)
    }

    // Args
    private val args: PostDetailFragmentArgs by navArgs()

    //Ui
    private lateinit var avatarImageView: AppCompatImageView
    private lateinit var infoTextView: AppCompatTextView
    private lateinit var companyTextView: AppCompatTextView
    private lateinit var addressTextView: AppCompatTextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_detail_fragment, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        avatarImageView = view.findViewById<AppCompatImageView?>(R.id.avatar_imageView)
        infoTextView = view.findViewById<AppCompatTextView?>(R.id.info_TextView)
        companyTextView = view.findViewById<AppCompatTextView?>(R.id.company_TextView)
        addressTextView = view.findViewById<AppCompatTextView?>(R.id.address_TextView)

        viewModel.getUserLiveData().observe(viewLifecycleOwner) {
            ImageLoaderUtils.with(requireContext()).placeholder(R.drawable.noimage)
                .load(args.post.user.avatar.large)
                .into(avatarImageView)
            infoTextView.text =
                " Name: ${args.post.user.name} Email: ${args.post.user.email}  Phone${args.post.user.phone}"
            companyTextView.text =
                "${args.post.user.company.name} ${args.post.user.company.bs} ${args.post.user.company.catchPhrase}"

            addressTextView.text =
                "${args.post.user.address.zipcode} ${args.post.user.address.suite} ${args.post.user.address.street} ${args.post.user.address.city}"
        }


    }


}