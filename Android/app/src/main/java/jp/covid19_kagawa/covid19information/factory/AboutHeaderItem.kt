package jp.covid19_kagawa.covid19information.factory

import com.xwray.groupie.databinding.BindableItem
import jp.covid19_kagawa.covid19information.R
import jp.covid19_kagawa.covid19information.databinding.ItemAboutHeaderBinding

class AboutHeaderItem constructor(
    private val onClickTwitter: () -> Unit,
    private val onClickWebPage: () -> Unit
) : BindableItem<ItemAboutHeaderBinding>() {
    override fun getLayout(): Int = R.layout.item_about_header

    override fun bind(viewBinding: ItemAboutHeaderBinding, position: Int) {
        viewBinding.twitterButton.setOnClickListener {
            onClickTwitter()
        }
        viewBinding.webButton.setOnClickListener {
            onClickWebPage()
        }
    }

//    interface Factory {
//        fun create(
//            onClickTwitter: () -> Unit,
//            onClickWebpage: () -> Unit
//        ): AboutHeaderItem
//    }
}
