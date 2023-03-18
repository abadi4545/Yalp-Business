package com.arkam.yelp.main

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.arkam.yelp.adapter.MyFragmentPagerAdapter
import com.arkam.yelp.fragment.MapFragment
import com.arkam.yelp.fragment.OverviewFragment
import com.arkam.yelp.fragment.ReviewsFragment
import com.arkam.yelp.model.YelpReview
import com.arkam.yelp.R
import com.arkam.yelp.util.Constant
import kotlinx.android.synthetic.main.fragment_restaurant_overview.*
import kotlinx.android.synthetic.main.fragment_restaurant_reviews.*
import kotlinx.android.synthetic.main.activity_restaurant_details.*
import java.util.*

private const val BASE_URL = "https://api.yelp.com/v3/"

private const val TAG = "RestaurantDetailActivity"
class RestaurantDetailActivity : AppCompatActivity() {

    lateinit var id: String
    lateinit var name:String
    lateinit var resUrl:String

    var lat:Float = 0.0f
    var lon:Float = 0.0f

    lateinit var pagerAdapter: MyFragmentPagerAdapter
    val photos = mutableListOf<String>()
    val reviews = mutableListOf<YelpReview>()

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_details)

        id = intent.getSerializableExtra(RESTAURANT_ID) as String
        name = intent.getSerializableExtra(RESTAURANT_NAME) as String
        lat = intent.getSerializableExtra(RESTAURANT_LAT) as Float
        lon = intent.getSerializableExtra(RESTAURANT_LON) as Float

        setTitle(name)

        val viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        pagerAdapter = MyFragmentPagerAdapter(supportFragmentManager, this)
        viewPager.adapter = pagerAdapter

        pagerAdapter.addFragment(OverviewFragment(), "Business Details")
        pagerAdapter.addFragment(MapFragment(), "Map Location")
        pagerAdapter.addFragment(ReviewsFragment(), "Reviews")

        pagerAdapter.notifyDataSetChanged()

        val tabLayout = findViewById<View>(R.id.sliding_tabs) as TabLayout
        tabLayout.setupWithViewPager(viewPager)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.i("TAG", "onCreateOptionsMenu")
        val inflater = menuInflater
        inflater.inflate(R.menu.detil_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_facebook) {

            if (!resUrl.isEmpty()){

                // Do something
                val url = Intent(Intent.ACTION_SEND)
                url.setPackage(Constant.FACEBOOK_PACKAGE_NAME)
                url.putExtra(Intent.EXTRA_TEXT, resUrl)
                url.setType("text/plain")

                try {
                    startActivity(url)
                }catch (e:Exception){
                    Toast.makeText(this@RestaurantDetailActivity, "facebook not installed", Toast.LENGTH_LONG).show()
                }
            }
            return true
        }
        if (id == R.id.action_twitter){

            if (!resUrl.isEmpty()){

                // Do something
                val url = Intent(Intent.ACTION_SEND)
                url.setPackage(Constant.TWITTER_PACKAGE_NAME)
                url.putExtra(Intent.EXTRA_TEXT, resUrl)
                url.setType("text/plain")

                try {
                    startActivity(url)
                }catch (e:Exception){
                    Toast.makeText(this@RestaurantDetailActivity, "twitter not installed", Toast.LENGTH_LONG).show()
                }
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
