package br.com.viewsystem_sample.featureA.navigation

import android.content.Context
import android.content.Intent

interface FeatureAEntryPoint {

    fun createIntent(context: Context): Intent

}
