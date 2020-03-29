package jp.covid19_kagawa.covid19information


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import jp.covid19_kagawa.covid19information.actioncreator.*
import jp.covid19_kagawa.covid19information.data.api.TokyoAPi
import jp.covid19_kagawa.covid19information.data.api.aomori.AomoriAPi
import jp.covid19_kagawa.covid19information.data.api.chiba.ChibaAPi
import jp.covid19_kagawa.covid19information.data.api.gumma.GummaAPi
import jp.covid19_kagawa.covid19information.data.api.ibaraki.IbarakiAPi
import jp.covid19_kagawa.covid19information.data.api.iwate.IwateAPi
import jp.covid19_kagawa.covid19information.data.api.kagawa.KagawaAPi
import jp.covid19_kagawa.covid19information.data.api.miyagi.MiyagiAPi
import jp.covid19_kagawa.covid19information.data.repository.*
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

        single {
            Retrofit
                .Builder()
                .client(OkHttpClient.Builder().build())
                .baseUrl("https://raw.githubusercontent.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(KagawaAPi::class.java)
        }
        single {
            Retrofit
                .Builder()
                .client(OkHttpClient.Builder().build())
                .baseUrl("https://raw.githubusercontent.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(AomoriAPi::class.java)
        }
        single {
            Retrofit
                .Builder()
                .client(OkHttpClient.Builder().build())
                .baseUrl("https://raw.githubusercontent.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(IwateAPi::class.java)
        }
        single {
            Retrofit
                .Builder()
                .client(OkHttpClient.Builder().build())
                .baseUrl("https://raw.githubusercontent.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(MiyagiAPi::class.java)
        }
        single {
            Retrofit
                .Builder()
                .client(OkHttpClient.Builder().build())
                .baseUrl("https://raw.githubusercontent.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(IbarakiAPi::class.java)
        }
        single {
            Retrofit
                .Builder()
                .client(OkHttpClient.Builder().build())
                .baseUrl("https://raw.githubusercontent.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(GummaAPi::class.java)
        }
        single {
            Retrofit
                .Builder()
                .client(OkHttpClient.Builder().build())
                .baseUrl("https://raw.githubusercontent.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(ChibaAPi::class.java)
        }
        single { KagawaRepository(get()) }
        single { AomoriRepository(get()) }
        single { IwateRepository(get()) }
        single { MiyagiRepository(get()) }
        single { GummaRepository(get()) }
        single { IbarakiRepository(get()) }
        single { ChibaRepository(get()) }
        single { TokyoRepository(get()) }
        single { ChartRepository(get(), get(), get(), get(), get(), get(), get(), get()) }
        single { InfectionRepository(get(), get(), get(), get(), get(), get(), get(), get()) }
        single { InspectionRepository(get(), get(), get(), get(), get(), get(), get(), get()) }
        single { ContactRepository(get(), get(), get(), get(), get(), get(), get(), get()) }
        single { EntranceRepository(get(), get(), get(), get(), get(), get(), get(), get()) }
        single {
            InspectionDetailRepository(
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get()
            )
        }
        single { NewsRepository(get(), get(), get(), get(), get(), get(), get(), get()) }
        single { GuideRepository() }
        single { DatabaseRepository() }
        single { PreferenceRepository(get()) }
    }

    private val uiModule = module {
        viewModel { ChartStore(get()) }
        factory {
            ChartActionCreator(
                get(), get(), get()
            )
        }
        viewModel { InfectionStore(get()) }
        factory {
            InfectionActionCreator(get(), get(), get())
        }
        viewModel { NewsStore(get()) }
        factory {
            NewsActionCreator(get(), get(), get())
        }

        viewModel { InspectionStore(get()) }
        factory {
            InspectionActionCreator(get(), get(), get())
        }

        viewModel { InspectionDetailStore(get()) }
        factory {
            InspectionDetailActionCreator(get(), get(), get())
        }


        viewModel { ContactStore(get()) }
        factory {
            ContactActionCreator(get(), get(), get())
        }

        viewModel { EntranceStore(get()) }
        factory {
            EntranceActionCreator(get(), get(), get())
        }
        viewModel {
            GuideStore(get())
        }
        factory { GuideActionCreator(get(), get(), get()) }

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