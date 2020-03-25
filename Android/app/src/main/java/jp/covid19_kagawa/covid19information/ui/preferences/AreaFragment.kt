package jp.covid19_kagawa.covid19information.ui.preferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import jp.covid19_kagawa.covid19information.R
import jp.covid19_kagawa.covid19information.actioncreator.AreaActionCreator
import jp.covid19_kagawa.covid19information.adapter.AreaAdapter
import jp.covid19_kagawa.covid19information.observe
import jp.covid19_kagawa.covid19information.store.AreaStore
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AreaFragment : Fragment() {
    private val store: AreaStore by viewModel()
    private val actionCreator: AreaActionCreator by inject()
    private val adapter = AreaAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = layoutInflater.inflate(R.layout.fragment_area, container, false)
        val view = root.findViewById<RecyclerView>(R.id.area_list)

        store.loadedAreaList.observe(this) {
            adapter.run {
                items.clear()
                items.addAll(it)
                notifyDataSetChanged()
            }
        }
        adapter.onItemClicked = {
            Toast.makeText(
                context, "Sucess", Toast.LENGTH_SHORT
            ).show()
        }
        view.adapter = adapter
        actionCreator.getAreaNames()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.onItemClicked = {
            findNavController().navigate(
                AreaFragmentDirections.actionAreaToPref(
                    it
                )
            )
        }
    }

}