package com.benidict.data.adapter

import android.content.SharedPreferences
import com.f2prateek.rx.preferences2.Preference
import com.google.gson.Gson

class GsonPreferenceAdapter<T> constructor(private val gson: Gson, private val clazz: Class<T>) :
        Preference.Converter<T> {

        override fun deserialize(serialized: String): T  = gson.fromJson(serialized, clazz)

        override fun serialize(value: T): String = gson.toJson(value, clazz)

}
