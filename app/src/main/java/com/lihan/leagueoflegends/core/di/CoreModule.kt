package com.lihan.leagueoflegends.core.di

import androidx.room.Room
import coil.ImageLoader
import com.lihan.leagueoflegends.core.data.sharedpreferences.DefaultSharedPreferencesManager
import com.lihan.leagueoflegends.core.data.local.ChampionDao
import com.lihan.leagueoflegends.core.data.local.ChampionDetailDao
import com.lihan.leagueoflegends.core.data.local.ChampionRoomDatabase
import com.lihan.leagueoflegends.core.domain.network.HttpClientFactory
import com.lihan.leagueoflegends.core.domain.sharedpreferences.SharedPreferencesManager
import com.lihan.leagueoflegends.core.data.repository.VersionRepositoryImpl
import com.lihan.leagueoflegends.core.domain.repository.VersionRepository
import io.ktor.client.HttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val coreModule = module {
    single{
        HttpClientFactory().build()
    }.bind<HttpClient>()

    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = ChampionRoomDatabase::class.java,
            name = "champions.db"
        ).build()
    }.bind<ChampionRoomDatabase>()

    single {
        get<ChampionRoomDatabase>().championDao
    }.bind<ChampionDao>()

    single {
        get<ChampionRoomDatabase>().championDetailDao
    }.bind<ChampionDetailDao>()

    single {
        DefaultSharedPreferencesManager(
            context = androidContext()
        )
    }.bind<SharedPreferencesManager>()

    single {
        VersionRepositoryImpl(
            httpClient = get(),
            sharedPreferencesManager = get()
        )
    }.bind<VersionRepository>()
}