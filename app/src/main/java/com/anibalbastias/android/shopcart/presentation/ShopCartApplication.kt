package com.anibalbastias.android.shopcart.presentation

import android.content.Context
import androidx.multidex.MultiDexApplication
import androidx.fragment.app.Fragment
import com.anibalbastias.android.shopcart.base.view.BaseModuleFragment
import com.anibalbastias.android.shopcart.presentation.component.ApplicationComponent
import com.anibalbastias.android.shopcart.presentation.component.DaggerApplicationComponent
import com.anibalbastias.android.shopcart.presentation.module.ApplicationModule
import io.realm.Realm
import io.realm.RealmConfiguration

var context: ShopCartApplication? = null
fun getAppContext(): ShopCartApplication {
    return context!!
}

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