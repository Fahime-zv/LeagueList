package life.league.challenge.kotlin.presentation.ui.screen.postlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.presentation.architucture.BaseFragmentVMState
import life.league.challenge.kotlin.presentation.architucture.ViewState
import life.league.challenge.kotlin.presentation.ui.common.RecyclerViewDecorations
import life.league.challenge.kotlin.presentation.ui.screen.postlist.sub.PostAdapter
import life.league.challenge.kotlin.presentation.ui.screen.splash.SplashFragmentDirections
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostListFragment : BaseFragmentVMState<PostListViewModel>() {

    override val viewModel: PostListViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private lateinit var loading: ProgressBar
    private lateinit var error: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        loading = view.findViewById(R.id.loading_progressbar)

        error = view.findViewById<TextView?>(R.id.error_textView).apply {
            isVisible = false
        }

        recyclerView = view.findViewById<RecyclerView?>(R.id.postList_recyclerView).apply {

            isVisible = false

            postAdapter = PostAdapter { post ->
                navigate(PostListFragmentDirections.actionPostListFragmentToDetailPostFragment(post))

            }

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = postAdapter
            addItemDecoration(
                RecyclerViewDecorations.NoLastItemDividerDecorator(
                    requireContext(),
                    LinearLayout.VERTICAL
                )
            )
        }


        viewModel.getPostListLiveData().observe(viewLifecycleOwner) { list ->
            postAdapter.bind(list)
        }

    }

    override fun onLoading() {
        loading.isVisible = true
        error.isVisible = false

    }

    override fun onData() {
        recyclerView.isVisible = true
        loading.isVisible = false
        error.isVisible = false


    }

    override fun onNetworkError() {
        recyclerView.isVisible = false
        loading.isVisible = false
        error.isVisible = true


    }

}