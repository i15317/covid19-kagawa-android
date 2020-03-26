package jp.covid19_kagawa.covid19information


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import jp.covid19_kagawa.covid19information.actioncreator.*
import jp.covid19_kagawa.covid19information.data.api.TokyoAPi
import jp.covid19_kagawa.covid19information.data.repository.DatabaseRepository
import jp.covid19_kagawa.covid19information.data.repository.PreferenceRepository
import jp.covid19_kagawa.covid19information.data.repository.TokyoRepository
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.repository.*
import jp.covid19_kagawa.covid19information.room.database.JapanTopDatabase
import jp.covid19_kagawa.covid19information.room.database.PrefectureDatabase
import jp.covid19_kagawa.covid19information.store.*
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
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
        private const val PREFERENCES_FILE_KEY = "jp.covid19_kagawa.covid19information_preferences"
    }

    private val appModule = module {
        single { Dispatcher() }
    }

    private fun provideSettingsPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences(Companion.PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)

    private val preferenceModule = module {
        single { provideSettingsPreferences(androidApplication()) }
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
        single { TokyoRepository(get()) }
        single { ChartRepository(get()) }
        single { InfectionRepository(get()) }
        single { InspectionRepository(get()) }
        single { ContactRepository(get()) }
        single { EntranceRepository(get()) }
        single { InspectionDetailRepository(get()) }
        single { NewsRepository(get()) }
        single { GuideRepository() }
        single { DatabaseRepository() }
        single { PreferenceRepository(get()) }
    }

    private val uiModule = module {
        viewModel { ChartStore(get()) }
        factory {
            ChartActionCreator(
                get(),
                get()
            )
        }
        viewModel { InfectionStore(get()) }
        factory {
            InfectionActionCreator(get(), get(), get())
        }

        viewModel { InspectionStore(get()) }
        factory {
            InspectionActionCreator(get(), get())
        }

        viewModel { InspectionDetailStore(get()) }
        factory {
            InspectionDetailActionCreator(get(), get())
        }


        viewModel { ContactStore(get()) }
        factory {
            ContactActionCreator(get(), get())
        }

        viewModel { EntranceStore(get()) }
        factory {
            EntranceActionCreator(get(), get())
        }
        viewModel {
            GuideStore(get())
        }
        factory { GuideActionCreator(get(), get()) }

        factory { PrefectureActionCreator(get(), get(), get()) }
        viewModel { PrefectureStore(get()) }
        factory { AreaActionCreator(get(), get(), get()) }
        viewModel { AreaStore(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        initRoomDatabase()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            // Contextを宣言し、
            androidContext(this@App)
            // 先ほど宣言したmoduleを指定します。
            modules(listOf(appModule, preferenceModule, uiModule, networkModule))
        }
    }

    //データベースの初期化
    private fun initRoomDatabase() {
        JapanTopDatabase.init(this)
        PrefectureDatabase.init(this)
    }
}