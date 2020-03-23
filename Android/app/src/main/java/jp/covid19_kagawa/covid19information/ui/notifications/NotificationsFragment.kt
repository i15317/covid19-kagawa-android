package jp.covid19_kagawa.covid19information.ui.notifications

import android.os.Bundle
import android.view.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import jp.covid19_kagawa.covid19information.CustomViewPager
import jp.covid19_kagawa.covid19information.R
import jp.covid19_kagawa.covid19information.ui.dashboard.DashboardFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var viewPager: CustomViewPager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        //val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            //    textView.text = it
        })
        viewPager = root.findViewById(R.id.view_pager)
        viewPager.adapter = object :
            FragmentStatePagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(i: Int): Fragment {
                when (i) {
                    0 -> {
                        return DashboardFragment.newInstance()
                    }
                    else -> {
                        return DashboardFragment.newInstance()
                    }
                }
            }

            override fun getCount(): Int {
                return 2
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return context!!.resources.getString(TAB_TITLES[position])
            }

        }
        val tabs: TabLayout = root.findViewById(R.id.tab_layout)
        tabs.setupWithViewPager(viewPager)
        setHasOptionsMenu(true)
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.lock, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_lock -> {
            if (viewPager.getPagingEnabled()) {
                viewPager.setPagingEnabled(false)
                item.icon = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_lock_outline_black_24dp,
                    null
                )
            } else {
                viewPager.setPagingEnabled(true)
                item.icon = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_lock_open_black_24dp,
                    null
                )
            }
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }

}
