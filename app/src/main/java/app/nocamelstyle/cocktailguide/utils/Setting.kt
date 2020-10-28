package app.nocamelstyle.cocktailguide.utils

import android.content.Context
import androidx.core.content.edit

class Setting(ctx: Context) {

    private val pref by lazy { ctx.getSharedPreferences("setting", Context.MODE_PRIVATE)!! }

    var isOnboardingShowed
        get() = pref.getBoolean("is_onboarding_showed", false)
        set(value) = pref.edit { putBoolean("is_onboarding_showed", value) }

}