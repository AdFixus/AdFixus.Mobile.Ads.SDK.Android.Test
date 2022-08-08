/*
 * Copyright (C) 2013 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.adfixus.adfixusmobileframework.test

import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*

/**
 * Main Activity. Inflates main activity xml and child fragments.
 */
class MyActivity : AppCompatActivity() {

  private lateinit var listView: ListView
  private var initialLayoutComplete = false

  private val imageids = arrayOf<Int>(
    R.drawable.kia,
    R.drawable.hyundai,
    R.drawable.mitsubishi,
    R.drawable.toyotta,
    R.drawable.tcorolla,
    R.drawable.kia,
    R.drawable.hyundai,
    R.drawable.mitsubishi,
    R.drawable.toyotta,
    R.drawable.tcorolla,
    R.drawable.kia,
    R.drawable.hyundai,
    R.drawable.mitsubishi,
    R.drawable.toyotta,
    R.drawable.tcorolla,
    R.drawable.kia,
    R.drawable.hyundai,
    R.drawable.mitsubishi,
    R.drawable.toyotta,
    R.drawable.tcorolla,
  )

  @RequiresApi(Build.VERSION_CODES.R)
  @SuppressLint("ResourceType")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_my)

    // Initialize the Mobile Ads SDK with an empty completion listener.
    MobileAds.initialize(this) {}

    // Set your test devices. Check your logcat output for the hashed device ID to
    // get test ads on a physical device. e.g.
    // "Use RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("ABCDEF012345"))
    // to get test ads on this device."
    MobileAds.setRequestConfiguration(
      RequestConfiguration.Builder()
        .setTestDeviceIds(listOf("ABCDEF012345"))
        .build()
    )

    listView = findViewById<ListView>(R.id.list_view)

    listView.viewTreeObserver.addOnGlobalLayoutListener {
      if (!initialLayoutComplete) {
        initialLayoutComplete = true


        val displaySize = Point()
        this.windowManager?.defaultDisplay?.getRealSize(displaySize)
        val density = this.resources.displayMetrics.density
        val heightSp = Math.round(500 * density).toInt()
        val widthSp = Math.round(displaySize.x / density).toInt()


        var arrayAdapter = CarListAdapter(this, imageids, widthSp)
        listView.adapter = arrayAdapter
      }
    }
  }

  /** Called when leaving the activity */
  public override fun onPause() {
    super.onPause()
  }

  /** Called when returning to the activity */
  public override fun onResume() {
    super.onResume()
  }

  /** Called before the activity is destroyed */
  public override fun onDestroy() {
    super.onDestroy()
  }
}