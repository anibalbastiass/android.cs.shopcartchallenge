package com.anibalbastias.android.shopcart.presentation

import android.content.Context
import androidx.multidex.MultiDexApplication
import androidx.fragment.app.Fragment
import com.anibalbastias.android.shopcart.BuildConfig
import com.anibalbastias.android.shopcart.base.view.BaseModuleFragment
import com.anibalbastias.android.shopcart.presentation.component.ApplicationComponent
import com.anibalbastias.android.shopcart.presentation.component.DaggerApplicationComponent
import com.anibalbastias.android.shopcart.presentation.module.ApplicationModule
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import io.realm.RealmConfiguration

var context: ShopCartApplication? = null
fun getAppContext(): ShopCartApplication {
    return context!!
}

/**
 * Created by anibalbastias on 2019-11-25.
 */

class ShopCartApplication : MultiDexApplication() {

    companion object {
        lateinit var appContext: Context
        var applicationComponent: ApplicationComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        appComponent().inject(this)
        context = this
        appContext = this
        setRealm()
        setDebugStetho()
    }

    private fun setDebugStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                    .build())
        }
    }

    private fun setRealm() {
        // Initialize Realm (just once per application)
        Realm.init(this)
        RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
    }
}

private fun buildDagger(context: Context): ApplicationComponent {
    if (ShopCartApplication.applicationComponent == null) {
        ShopCartApplication.applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(context as ShopCartApplication))
            .build()
    }
    return ShopCartApplication.applicationComponent!!
}

fun Context.appComponent(): ApplicationComponent {
    return buildDagger(this.applicationContext)
}

fun Fragment.appComponent(): ApplicationComponent {
    return buildDagger(this.context!!.applicationContext)
}

fun BaseModuleFragment.appComponent(): ApplicationComponent {
    return buildDagger(this.context!!.applicationContext)
}