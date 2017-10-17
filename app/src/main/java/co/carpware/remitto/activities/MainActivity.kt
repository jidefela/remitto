package co.carpware.remitto.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Menu
import android.view.MenuItem
import co.carpware.remitto.R
import co.carpware.remitto.fragment.HistoryFragment
import co.carpware.remitto.fragment.ProfileFragment
import co.carpware.remitto.fragment.TransferFragment
import co.carpware.bookshelf.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.partial_toolbar.*

/**
 * Created by jide on 03/07/17.
 */

class MainActivity : BaseActivity(){
    
    val tab_icons = listOf(R.drawable.ic_account_circle, R.drawable.ic_send, R.drawable.ic_time)
    val tab_texts = arrayOf(R.string.profile, R.string.transfer, R.string.history)
    val page_fragments = listOf(ProfileFragment(), TransferFragment(), HistoryFragment())
    val pager_adapter = ViewPagerAdapter(supportFragmentManager, page_fragments)
    val menus = arrayOf(R.id.menu_edit, R.id.menu_help, R.id.menu_refresh)
    var menu: Menu? = null

    val onTabSelectedListener = object: TabLayout.OnTabSelectedListener{

        override fun onTabSelected(tab: TabLayout.Tab) {
            view_pager.currentItem = tab.position
            text_toolbar_title.text = getString(tab_texts[tab.position])
            changeMenuItem(tab.position)
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {

        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        text_toolbar_title.text = getString(tab_texts.first())

        view_pager.adapter = pager_adapter
        tab_layout.setupWithViewPager(view_pager)

        tab_layout.getTabAt(0)?.setIcon(tab_icons.first())
        tab_layout.getTabAt(1)?.setIcon(tab_icons[1])
        tab_layout.getTabAt(2)?.setIcon(tab_icons[2])
        tab_layout.addOnTabSelectedListener(onTabSelectedListener)
    }

    private fun changeMenuItem(position: Int){
        for((index, value) in menus.withIndex()){
            if(index == position){
                menu!!.findItem(value).setVisible(true)
            }else{
                menu!!.findItem(value).setVisible(false)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        this.menu = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_help -> {
                showHelpDialog()
            }
            R.id.menu_edit -> {
               startActivity(Intent(this, EditProfileActivity::class.java))
            }
        }
        return true
    }


}