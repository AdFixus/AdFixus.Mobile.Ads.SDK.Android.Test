package com.adfixus.adfixusmobileframework.test

import android.app.Activity
import android.os.Build
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import com.adfixus.android.ams.ads.AdManagerViewInterface
import com.adfixus.android.ams.ads.ResponsiveAdManager
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.admanager.AdManagerAdView

class CarListAdapter(
    private val context: Activity,
    private val imageIds: Array<Int>,
    private val width: Int
) : ArrayAdapter<Int?>(
    context, R.layout.row_item, imageIds
), AdManagerViewInterface {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val row = inflater.inflate(R.layout.linear_layout, null, true)
        var tableLayout = row!!.findViewById<View>(R.id.ad_view_container_file) as LinearLayout

        val density = context.resources.displayMetrics.density
        val heightSp = Math.round(500 * density).toInt()

        if (position == 0)
        {
            var adTableRow = RelativeLayout(context)
            adTableRow.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
            )

            adTableRow.layoutParams.height = heightSp
            tableLayout.addView(adTableRow)

            val adUnitId = "/21842759191/carsales.ios/used/results"
            val initialAdSize = AdSize(300, 250)
            val adSizeBanner = AdSize(300, 250)
            val adSizeCct = AdSize(360, 500)
            val adSizes = arrayOf(adSizeBanner, adSizeCct)
            var targeting = mutableMapOf<String, String>()
            targeting["kw"] = "mobilefirst-mrec"
            targeting["cct"] = "contentcard,gc,carsalescard"

            val adView = AdManagerAdView(context)
            adView.adUnitId = adUnitId
            adView.setAdSizes(*adSizes)

            adView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            // Create an ad request.
            val adRequest = AdManagerAdRequest.Builder()
            adRequest.addCustomTargeting("cct", "mrec")

            // Start loading the ad in the background.
            adView.loadAd(adRequest.build())
            adTableRow.addView(adView)

        }
        else if (position % 2 == 0)
        {
            var adTableRow = RelativeLayout(context)
            adTableRow.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
            )

            adTableRow.layoutParams.height = heightSp
            tableLayout.addView(adTableRow)

            val adUnitId = "/21842759191/carsales.ios/used/results"
            val initialAdSize = AdSize(300, 250)
            val adSizeBanner = AdSize(300, 250)
            val adSizeCct = AdSize(360, 500)
            val adSizes = arrayOf(adSizeBanner, adSizeCct)
            var targeting = mutableMapOf<String, String>()
            //targeting["kw"] = "mobilefirst-mrec"
            targeting["cct"] = "contentcard,gc,carsalescard"

            ResponsiveAdManager.loadResponsiveAd(context,
                eventHandler = this,
                containerView = adTableRow,
                initialSize = initialAdSize,
                adSizes = adSizes,
                adUnitID = adUnitId,
                customTargeting = targeting,
                publisherProvidedID = "",
                parentHeight = 500,
                parentWidth = width
            )
        }
        else {
            var carTableRow = LinearLayout(context)
            carTableRow.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
            )
            carTableRow.layoutParams.height = heightSp

            var contentImage = inflater.inflate(R.layout.row_item, null, true)
            val imageFlag = contentImage!!.findViewById<View>(R.id.imageViewFlag) as ImageView
            imageFlag.setImageResource(imageIds[position])
            carTableRow.addView(contentImage)
            tableLayout.addView(carTableRow)
        }

        return tableLayout
    }

    override fun onAdClicked() {
        // Code to be executed when the user clicks on an ad.
    }

    override fun onAdClosed() {
        // Code to be executed when the user is about to return
        // to the app after tapping on an ad.
    }

    override  fun onAdFailedToLoad(adError: LoadAdError) {
        // Code to be executed when an ad request fails.
    }

    override  fun onAdImpression() {
        // Code to be executed when an impression is recorded
        // for an ad.
    }

    override  fun onAdLoaded() {
        // Code to be executed when an ad has finished loading
        //var t = Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    override  fun onAdOpened() {
        // Code to be executed when an ad opens an overlay that
        // covers the screen.
    }
}