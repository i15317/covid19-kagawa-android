package jp.covid19_kagawa.covid19information


import android.app.Application
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import jp.covid19_kagawa.covid19information.actioncreator.ChartActionCreator
import jp.covid19_kagawa.covid19information.actioncreator.InfectionActionCreator
import jp.covid19_kagawa.covid19information.store.ChartStore
import jp.covid19_kagawa.covid19information.repository.ChartRepository


import jp.covid19_kagawa.covid19information.data.api.TokyoAPi
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.repository.InfectionRepository
import jp.covid19_kagawa.covid19information.store.InfectionStore
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber


class App : Application() {

    companion object {
        private const val PREF_NAME = "PrefName"
    }

    private val appModule = module {
        single { Dispatcher() }
    }

    private val networkModule = module {
        single {
            Retrofit
                .Builder()
                .client(OkHttpClient.Builder().build())
                .baseUrl("https://raw.githubusercontent.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(TokyoAPi::class.java)
        }
//        single {
//            Retrofit
//                .Builder()
//                .client(OkHttpClient.Builder().build())
//                .baseUrl("https://raw.githubusercontent.com/")
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(MoshiConverterFactory.create())
//                .build()
//                .create(TokyoAPi::class.java)
//        }

        single {
            ChartRepository(
                get()
            )
        }
        single {
            InfectionRepository(
                get()
            )
        }

    }

    private val uiModule = module {
        factory {
            ChartActionCreator(
                get(),
                get()
            )
        }
        viewModel { ChartStore(get()) }

        factory {
            InfectionActionCreator(get(), get())
        }
        viewModel { InfectionStore(get()) }

    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            // Contextを宣言し、
            androidContext(this@App)
            // 先ほど宣言したmoduleを指定します。
            modules(listOf(appModule, uiModule, networkModule))
        }
    }
}