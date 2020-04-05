package jp.covid19_kagawa.covid19information

enum class Prefecture(val prefCode: Int) {
    HOKKAIDO(1) {
        override fun prefecturePageLink(): String = "https://stopcovid19.hokkaido.dev/"
        override fun prefectureGuideLink(): String =
            "http://www.pref.hokkaido.lg.jp/hf/kth/kak/singatakoronahaien.htm#%E9%81%93%E6%B0%91%E3%81%B8"

        override fun prefectureCallLink(): String =
            "http://www.pref.hokkaido.lg.jp/hf/kth/kak/singatakoronahaien.htm#%E9%81%93%E6%B0%91%E3%81%B8"
    },
    AOMORI(2) {
        override fun prefecturePageLink(): String = "https://covid19.codeforaomori.org/"
        override fun prefectureGuideLink(): String =
            "https://www.pref.aomori.lg.jp/welfare/health/wuhan-novel-coronavirus2020.html"

        override fun prefectureCallLink(): String =
            "https://www.pref.aomori.lg.jp/welfare/health/wuhan-novel-coronavirus2020.html"
    },
    IWATE(3) {
        override fun prefecturePageLink(): String = "https://iwate.stopcovid19.jp/"
        override fun prefectureGuideLink(): String =
            "https://www.pref.iwate.jp/kurashikankyou/iryou/covid19/index.html#voice"

        override fun prefectureCallLink(): String =
            "https://www.pref.iwate.jp/kurashikankyou/iryou/covid19/index.html#voice"
    },
    MIYAGI(4) {
        override fun prefecturePageLink(): String = "https://miyagi.stopcovid19.jp/"
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    AKITA(5) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String =
            "https://www.pref.akita.lg.jp/pages/archive/16317#kennminnnominasamae"

        override fun prefectureCallLink(): String =
            "https://www.pref.akita.lg.jp/pages/archive/16317#soudannmadoguchi"
    },
    YAMAGATA(6) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String =
            "https://www.pref.yamagata.jp/ou/bosai/020072/kochibou/coronavirus/coronavirus.html"

        override fun prefectureCallLink(): String =
            "https://www.pref.yamagata.jp/ou/bosai/020072/kochibou/coronavirus/coronavirus.html"
    },
    FUKUSHIMA(7) {
        override fun prefecturePageLink(): String = "https://fukushima-covid19.firebaseapp.com/"
        override fun prefectureGuideLink(): String =
            "https://www.pref.fukushima.lg.jp/sec/21045c/coronavirus-taiou.html"

        override fun prefectureCallLink(): String =
            "https://www.pref.fukushima.lg.jp/sec/21045c/coronavirus-soudanmadoguchi.html"
    },
    IBARAKI(8) {
        override fun prefecturePageLink(): String = "https://covid19-ibaraki.netlify.com/"
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    TOCHIGI(9) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    GUMMA(10) {
        override fun prefecturePageLink(): String = "https://stopcovid19-gunma.netlify.com/"
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    SAITAMA(11) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    CHIBA(12) {
        override fun prefecturePageLink(): String = "https://stopcovid19.code4chiba.org/"
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    TOKYO(13) {
        override fun prefecturePageLink(): String = "https://stopcovid19.metro.tokyo.lg.jp/"
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    KANAGAWA(14) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    NIIGATA(15) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    TOYAMA(16) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    ISHIKAWA(17) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    FUKUI(18) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    YAMANASHI(19) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    NAGANO(20) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    GIFU(21) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    SHIZUOKA(22) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    AICHI(23) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    MIE(24) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    SHIGA(25) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    KYOTO(26) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    OSAKA(27) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    HYOGO(28) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    NARA(29) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    WAKAYAMA(30) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    TOTTORI(31) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    SHIMANE(32) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    OKAYAMA(33) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    HIROSHIMA(34) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    YAMAGUCHI(35) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    TOKUSHIMA(36) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    KAGAWA(37) {
        override fun prefecturePageLink(): String = "https://covid19-kagawa.jp/"
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    EHIME(38) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    KOCHI(39) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    FUKUOKA(40) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    SAGA(41) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    NAGASAKI(42) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    KUMAMOTO(43) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    OITA(44) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    MIYAZAKI(45) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    KAGOSHIMA(46) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    OKINAWA(47) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureGuideLink(): String = ""
        override fun prefectureCallLink(): String = ""
    },
    DEFAULT(0) {
        override fun prefecturePageLink(): String = ""
        override fun prefectureCallLink(): String = ""
        override fun prefectureGuideLink(): String = ""
    };

    abstract fun prefecturePageLink(): String
    abstract fun prefectureGuideLink(): String
    abstract fun prefectureCallLink(): String
    //abstract fun prefectureCallNum: String
}