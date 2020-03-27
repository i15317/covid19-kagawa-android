package jp.covid19_kagawa.covid19information.ui.preferences

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import jp.covid19_kagawa.covid19information.actioncreator.PrefectureActionCreator
import jp.covid19_kagawa.covid19information.adapter.PrefAdapter
import jp.covid19_kagawa.covid19information.databinding.FragmentPrefBinding
import jp.covid19_kagawa.covid19information.observe
import jp.covid19_kagawa.covid19information.store.PrefectureStore
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class PrefectureFragment : Fragment() {
    private val store: PrefectureStore by viewModel()
    private val actionCreator: PrefectureActionCreator by inject()
    private val adapter = PrefAdapter()
    private val args: PrefectureFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPrefBinding.inflate(inflater)
        binding.prefList.adapter = adapter
        val root = binding.root

        store.loadedAreaList.observe(this) {
            adapter.run {
                items.clear()
                items.addAll(it)
                notifyDataSetChanged()
            }
        }
        adapter.onItemClicked = {
            if (it != store.emptyEntity)
                AlertDialog.Builder(context)
                    .setTitle("都道府県の変更")
                    .setMessage("都道府県を「" + it.prefName + "」に変更します")
                    .setPositiveButton(
                        "OK"
                    ) { dialog, which ->
                        actionCreator.updateCurrentPrefecture(it)
                        findNavController().navigate(
                            PrefectureFragmentDirections.actionPrefToArea()
                        )
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
        }

        actionCreator.getPrefNames(args.content.classCode)
        return root
    }
}