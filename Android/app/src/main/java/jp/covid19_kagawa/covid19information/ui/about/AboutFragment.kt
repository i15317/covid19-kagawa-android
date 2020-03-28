package jp.covid19_kagawa.covid19information.ui.about

import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.GroupieViewHolder
import dev.chrisbanes.insetter.doOnApplyWindowInsets
import jp.covid19_kagawa.covid19information.R
import jp.covid19_kagawa.covid19information.databinding.FragmentAboutBinding
import jp.covid19_kagawa.covid19information.factory.AboutHeaderItem
import jp.covid19_kagawa.covid19information.ui.about.AboutFragmentDirections.Companion.actionAboutToChrome

class AboutFragment : Fragment(R.layout.fragment_about) {


    companion object {
        const val TWITTER_URL = "https://twitter.com/cdd_pj"
        const val WEB_URL = "https://caffeine-driven.com"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAboutBinding.bind(view)
        val groupAdapter = GroupAdapter<GroupieViewHolder<*>>()

        binding.aboutRecycler.run {
            adapter = groupAdapter
            doOnApplyWindowInsets { recyclerView, insets, initialState ->

                recyclerView.updatePadding(
                    bottom = insets.systemWindowInsetBottom + initialState.paddings.bottom
                )
            }
            groupAdapter.update(
                listOf(
                    AboutHeaderItem(
                        onClickTwitter = {
                            findNavController().navigate(actionAboutToChrome(TWITTER_URL))
                        },
                        onClickWebPage = {
                            findNavController().navigate(actionAboutToChrome(WEB_URL))
                        }
                    )
                )
            )
            binding.progressBar.hide()
        }
    }
}