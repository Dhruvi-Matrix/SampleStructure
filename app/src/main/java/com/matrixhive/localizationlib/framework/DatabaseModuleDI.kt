package com.matrixhive.localizationlib.framework

import androidx.room.Room
import com.matrixhive.localizationlib.LocalizationApplication
import com.matrixhive.localizationlib.data.database.DatabaseClass
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val databaseModule = module {

    single {
        Room.databaseBuilder(
            (androidApplication() as LocalizationApplication),
            DatabaseClass::class.java,
            "LocalizationDatabase.db"
        ).build()
    }

    //Todo call abstract class method like this
    /*single { (get() as ImageDatabase).getImageDao() }
    single { (get() as ImageDatabase).getCollectionDao() }*/

}
