package com.babenkovladimir.composite_application_x.rx.rxjava_github

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.babenkovladimir.composite_application_x.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_java_git_hub.*


class RxJavaGitHubActivity : AppCompatActivity() {

    private val TAG = RxJavaGitHubActivity::class.java.simpleName
    private val adapter = GitHubRepoAdapter()
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java_git_hub)

        list_view_repos.adapter = adapter

        button_search.setOnClickListener {
            val username = edit_text_username.text.toString()
            if (!TextUtils.isEmpty(username)) {
                getStarredRepos(username)
            }
        }
    }

    override fun onDestroy() {
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
        super.onDestroy()
    }

    private fun getStarredRepos(username: String) {
        disposable = GitHubClient
            .getInstance()
            .getStarredRepos(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ gitHubRepos ->
                adapter.setGitHubRepos(gitHubRepos)
            }, {

            })
    }
}
