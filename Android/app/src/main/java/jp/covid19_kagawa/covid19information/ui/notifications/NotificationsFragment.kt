package jp.covid19_kagawa.covid19information.ui.notifications

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import jp.covid19_kagawa.covid19information.CustomViewPager
import jp.covid19_kagawa.covid19information.R
import jp.covid19_kagawa.covid19information.ui.dashboard.DashboardFragment
import jp.covid19_kagawa.covid19information.ui.inspection.InspectionFragment
import jp.covid19_kagawa.covid19information.ui.inspection_detail.InspectionDetailFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)
private const val COUNT_FRAGMENTS = 3

class NotificationsFragment : Fragment() {

    private lateinit var viewPager: CustomViewPager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        //val textView: TextView = root.findViewById(R.id.text_notifications)

        viewPager = root.findViewById(R.id.view_pager)
        viewPager.adapter = object :
            FragmentStatePagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(i: Int): Fragment {
                Toast.makeText(context, "鍵アイコンでスワイプを無効にします", Toast.LENGTH_SHORT).show()
                when (i) {
                    0 -> {
                        return InspectionFragment()
                    }
                    1 -> {
                        return InspectionDetailFragment()
                    }
                    else -> {
                        return DashboardFragment.newInstance()
                    }
                }

            }

            override fun getCount(): Int {
                return COUNT_FRAGMENTS
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
                Toast.makeText(context, "スワイプ機能がロックされました", Toast.LENGTH_SHORT).show()
            } else {
                viewPager.setPagingEnabled(true)
                item.icon = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_lock_open_black_24dp,
                    null
                )
                Toast.makeText(context, "スワイプ機能が解除されました", Toast.LENGTH_SHORT).show()

            }
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }

}
