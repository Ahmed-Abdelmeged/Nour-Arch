package com.abdelmeged.ahmed.nourplayerarch.utils

import java.util.*

/**
 * Created by ahmed on 7/24/2017.
 */
class Constants {
    companion object {
        const val TABLE_NAME = "suras"
        const val BASE_URL = "https://drive.google.com/"

        /**
         * Key for get the Sura extra intent from the MainActivity
         */
        const val EXTRA_SURA_DOWNLOAD = "extra-sura-download-id"
        const val MESSAGE_PROGRESS = "message_progress"

        /**
         * To add support arabic as locale variable for the speech recognition
         */
        const val LOCALE_ARABIC = "ar"

        /**
         * Locations
         */
        @JvmField val PART = Arrays.asList("الجزء", "جذء", "الجذء", "جزء")
        @JvmField val AYA = Arrays.asList("ايه", "الايه")
        @JvmField val HZEB = Arrays.asList("حذب", "الحذب", "حزب", "الحزب")
        @JvmField val PAGE = Arrays.asList("الصفحة", "صفحة", "الصفحه", "الصفحه")
        @JvmField val QUARTER = Arrays.asList("ربع", "الربع")

        /**
         * Surahs
         */
        //1-29
        private val FTA = Arrays.asList("الفاتحه", "فاتحه", "الفاتحة", "فاتحة", "فتحه", "فتحة")
        private val BQR = Arrays.asList("البقره", "البقرة", "بقره", "بكره", "بقرة", "بكرة")
        private val IMR = Arrays.asList("ال عمران", "العمران", "عمران")
        private val NSA = Arrays.asList("النساء", "نساء")
        private val MDA = Arrays.asList("المائده", "مائده", "المائدة", "مائدة")
        private val ANM = Arrays.asList("انعام", "الأنعام", "الانعام", "أنعام", "انعم", "أنعم")
        private val ARF = Arrays.asList("الاعراف", "الأعراف", "اعراف", "الأعرف", "الاعرف", "أعراف")
        private val ANF = Arrays.asList("الأنفال", "الانفال", "انفال", "أنفال")
        private val TWB = Arrays.asList("التوبه", "توبه", "التوية", "توبة")
        private val YNS = Arrays.asList("يونس", "اليونس", "يونص", "اليونص")
        private val HUD = Arrays.asList("هود", "هوت", "الهود", "الهوت")
        private val YSF = Arrays.asList("يوسف", "اليوسف", "يوصف", "اليوصف")
        private val RAD = Arrays.asList("الرعد", "رعب", "الرعب", "رعد")
        private val IBR = Arrays.asList("ابراهيم", "أبراهيم", "إبراهيم", "إبرهيم", "ابرهيم", "أبراهيم")
        private val HJR = Arrays.asList("الحجر", "حجر")
        private val NHL = Arrays.asList("النحل", "نحل")
        private val ISR = Arrays.asList("الاسراء", "الأسراء", "الإسراء", "اسراء", "أسراء", "إسراء")
        private val KHF = Arrays.asList("الكهف", "كهف")
        private val MRY = Arrays.asList("مريم", "المريم")
        private val THA = Arrays.asList("طه", "طة", "الطة", "الطه")
        private val ANB = Arrays.asList("الانبياء", "الأنبياء", "الإنبياء", "انبياء", "أنبياء", "إنبياء")
        private val HAJ = Arrays.asList("حج", "الحج")
        private val MON = Arrays.asList("المؤمنون", "المؤمنين", "مؤمنين", "مؤمنون")
        private val NOR = Arrays.asList("النور", "نور")
        private val FUQ = Arrays.asList("فرقان", "الفرقان")
        private val SHU = Arrays.asList("الشعراء", "شعراء")
        private val NML = Arrays.asList("النمل", "نمل")
        private val QAS = Arrays.asList("القصص", "قصص")
        private val ANK = Arrays.asList("العنكبوت", "عنكبوت")


        //30-58
        private val ROM = Arrays.asList("الروم", "روم")
        private val LUQ = Arrays.asList("لقمان", "اللقمان", "لقمن")
        private val SJD = Arrays.asList("السجده", "سجدة", "السجدة", "سجده")
        private val SBA = Arrays.asList("الأحزاب", "الاحزاب", "الإحزاب", "احزاب", "أحزاب", "الأحذاب", "الاحذاب", "الإحذاب", "احذاب", "أحذاب", "إحذاب", "إحزاب")
        private val AZB = Arrays.asList("سيا", "سبأ", "السبا", "السبأ")
        private val FTR = Arrays.asList("فاطر", "الفاطر", "فطر", "الفطر")
        private val YSN = Arrays.asList("يس", "اليس", "يسين", "اليسين", "يسن", "اليسن")
        private val SFT = Arrays.asList("الصافات", "صافات", "صافت", "الصافت", "الصفات", "صفات")
        private val SAD = Arrays.asList("ص", "الص", "صاد", "الصاد", "صد", "صوت", "الصد")
        private val ZMR = Arrays.asList("الزمر", "زمر", "ذمر", "الذمر")
        private val GFR = Arrays.asList("غافر", "الغافر")
        private val FST = Arrays.asList("فصلت", "الفصلت")
        private val SRA = Arrays.asList("الشورى", "شورى")
        private val ZRF = Arrays.asList("الزخرف", "زخرف", "الذخرف", "ذخرف")
        private val DKN = Arrays.asList("الدخان", "دخان", "دخن", "الدخن")
        private val GTY = Arrays.asList("الجاثيه", "جاثيه", "جثيه", "الجثيه", "الجاسيه", "جاسيه")
        private val AGF = Arrays.asList("الاحقاف", "الأحقاف", "الإحقاف", "احقاف", "أحقاف", "إحقاف")
        private val MHD = Arrays.asList("محمد", "المحمد")
        private val FTH = Arrays.asList("الفتح", "فتح")
        private val JRT = Arrays.asList("الحجرات", "حجرات", "الحجرت", "حجرت")
        private val QAF = Arrays.asList("ق", "الق", "قف", "القاف", "قاف", "القف")
        private val ZRT = Arrays.asList("الذاريات", "ذاريات", "الذريات", "ذريات", "الزاريات", "زاريات", "الزريات", "زريات")
        private val TUR = Arrays.asList("الطور", "طور")
        private val NJM = Arrays.asList("النجم", "نجم")
        private val QMR = Arrays.asList("القمر", "قمر")
        private val RHM = Arrays.asList("الرحمن", "رحمن", "الرحمان", "رحمان")
        private val WQA = Arrays.asList("الواقعه", "واقعه", "الواقعة", "واقعة", "وقعه", "الوقعه", "الوقعة", "وقعة")
        private val HDY = Arrays.asList("الحديد", "حديد")
        private val MGD = Arrays.asList("المجادله", "مجادله", "مجدله", "المجدله")


        //59-86
        private val HSR = Arrays.asList("الحشر", "حشر")
        private val MUT = Arrays.asList("الممتحنه", "ممتحنه", "الممتحنة", "ممتحنة")
        private val SAF = Arrays.asList("الصق", "صف")
        private val JUM = Arrays.asList("الجمعه", "جمعه", "جمعة", "الجمعة")
        private val MNF = Arrays.asList("المنافقون", "منافقون", "منفقون", "المنفقون", "المنافقين", "منافقين")
        private val TGN = Arrays.asList("التغابن", "تغابن", "التغبن", "تغبن")
        private val TLQ = Arrays.asList("الطلاق", "طلاق")
        private val TRM = Arrays.asList("التحريم", "تحريم")
        private val MLK = Arrays.asList("الملك", "ملك")
        private val QLM = Arrays.asList("القلم", "قلم")
        private val HQA = Arrays.asList("الحاقه", "حاقه", "الحاقة", "حاقة", "حقه", "الحقه", "الحقة", "حقة")
        private val MRJ = Arrays.asList("المعارج", "معارج", "معرج", "المعرج")
        private val NOH = Arrays.asList("نوح", "النوح")
        private val JIN = Arrays.asList("الجن", "حن")
        private val MZL = Arrays.asList("المزمل", "مزمل", "مذمل", "المذمل")
        private val MDT = Arrays.asList("المدثر", "مدثر", "مدسر", "المدسر")
        private val QYM = Arrays.asList("القيامه", "قيامه", "قيامة", "القيامة", "القيمه", "قيمه", "القيمة", "قيمة")
        private val INS = Arrays.asList("الانسان", "الأنسان", "الإنسان", "انسان", "أنسان", "إنسان")
        private val MRS = Arrays.asList("المرسلات", "مرسلات", "المرسلت", "مؤسلت")
        private val NBA = Arrays.asList("النبأ", "نبأ", "نباء", "النباء")
        private val NZA = Arrays.asList("النازعات", "نازعات", "نزعت", "النزعت", "ناذعات", "الناذعات")
        private val ABS = Arrays.asList("عبس", "العبس")
        private val TKR = Arrays.asList("التكوير", "تكوير", "تقوير", "التقوير")
        private val INF = Arrays.asList("الانفطار", "الأنفطار", "الإنفطار", "انفطار", "أنفطار", "انفطر", "إنفطر", "أنفطر", "إنفطار")
        private val MTF = Arrays.asList("المطففين", "مطففين")
        private val SQA = Arrays.asList("الانشقاق", "الأنشقاق", "الإنشقاق", "اشنقاق", "أنشقاق", "انشقق", "إنشقاق")
        private val BRG = Arrays.asList("البروج", "بروج")
        private val TRQ = Arrays.asList("الطارق", "طارق", "طرق", "الطرق")


        //87-114
        private val ALA = Arrays.asList("الاعلى", "الأعلى", "الإعلى", "اعلى", "أعلى", "إعلى", "الاعلا", "الأعلا", "الإعلا", "اعلا", "أعلا", "إعلا")
        private val GSA = Arrays.asList("الغاشيه", "الغاشية", "غاشيه", "غاشية", "غشيه", "الغشيه", "الغشية", "غشية")
        private val FJR = Arrays.asList("الفجر", "فجر")
        private val BLD = Arrays.asList("البلد", "بلد")
        private val HMS = Arrays.asList("الشمس", "شمس")
        private val LIL = Arrays.asList("الليل", "ليل")
        private val DUH = Arrays.asList("الضحى", "ضحى")
        private val SHR = Arrays.asList("الشرح", "شرح")
        private val TIN = Arrays.asList("التين", "تين")
        private val ALQ = Arrays.asList("العلق", "علق")
        private val QDR = Arrays.asList("القدر", "قدر")
        private val BYN = Arrays.asList("البينه", "بينه", "البينة", "بينة")
        private val ZLA = Arrays.asList("الزلزله", "زلزله", "الزلزلة", "الذلذله", "ذلذله", "الزلزلة", "ذلذلة", "الذلذلة")
        private val ADY = Arrays.asList("العاديات", "عاديات")
        private val QRA = Arrays.asList("القارعه", "قارعه", "القارعة", "قارعة")
        private val TKU = Arrays.asList("التكاثر", "تكاثر", "التكاسر", "تكاسر")
        private val ASR = Arrays.asList("العصر", "عصر")
        private val HMZ = Arrays.asList("الهمزه", "الهمزة", "همزه", "الهمزة", "الهمذه", "الهمذه", "همذه", "همذة")
        private val FIL = Arrays.asList("الفيل", "فيل")
        private val QRS = Arrays.asList("قريش", "القريش")
        private val MAN = Arrays.asList("الماعون", "ماعون", "معون", "المعون")
        private val KWA = Arrays.asList("الكوثر", "كوثر", "كوسر", "الكوسر")
        private val KFR = Arrays.asList("الكافرون", "كافرون", "كفرون", "الكفرون", "الكافرين", "كافرين")
        private val NSR = Arrays.asList("النصر", "نصر")
        private val MSD = Arrays.asList("المسد", "مسد")
        private val IKS = Arrays.asList("الاخلاص", "الأخلاص", "الإخلاص", "اخلاص", "أخلاص", "إخلاص")
        private val FLQ = Arrays.asList("الفلق", "فلق")
        private val NAS = Arrays.asList("الناس", "ناس")


        val sSurahs = hashMapOf(

                //1-29
                QuranIndex.FTA to FTA,
                QuranIndex.BQR to BQR,
                QuranIndex.IMR to IMR,
                QuranIndex.NSA to NSA,
                QuranIndex.MDA to MDA,
                QuranIndex.ANM to ANM,
                QuranIndex.ARF to ARF,
                QuranIndex.ANF to ANF,
                QuranIndex.TWB to TWB,
                QuranIndex.YNS to YNS,
                QuranIndex.HUD to HUD,
                QuranIndex.YSF to YSF,
                QuranIndex.RAD to RAD,
                QuranIndex.IBR to IBR,
                QuranIndex.HJR to HJR,
                QuranIndex.NHL to NHL,
                QuranIndex.ISR to ISR,
                QuranIndex.KHF to KHF,
                QuranIndex.MRY to MRY,
                QuranIndex.THA to THA,
                QuranIndex.ANB to ANB,
                QuranIndex.HAJ to HAJ,
                QuranIndex.MON to MON,
                QuranIndex.NOR to NOR,
                QuranIndex.FUQ to FUQ,
                QuranIndex.SHU to SHU,
                QuranIndex.NML to NML,
                QuranIndex.QAS to QAS,
                QuranIndex.ANK to ANK,


                //30-58
                QuranIndex.ROM to ROM,
                QuranIndex.LUQ to LUQ,
                QuranIndex.SJD to SJD,
                QuranIndex.SBA to SBA,
                QuranIndex.AZB to AZB,
                QuranIndex.FTR to FTR,
                QuranIndex.YSN to YSN,
                QuranIndex.SFT to SFT,
                QuranIndex.SAD to SAD,
                QuranIndex.ZMR to ZMR,
                QuranIndex.GFR to GFR,
                QuranIndex.YSF to YSF,
                QuranIndex.FST to FST,
                QuranIndex.SRA to SRA,
                QuranIndex.ZRF to ZRF,
                QuranIndex.DKN to DKN,
                QuranIndex.GTY to GTY,
                QuranIndex.AGF to AGF,
                QuranIndex.MHD to MHD,
                QuranIndex.FTH to FTH,
                QuranIndex.JRT to JRT,
                QuranIndex.QAF to QAF,
                QuranIndex.ZRT to ZRT,
                QuranIndex.TUR to TUR,
                QuranIndex.NJM to NJM,
                QuranIndex.QMR to QMR,
                QuranIndex.RHM to RHM,
                QuranIndex.WQA to WQA,
                QuranIndex.HDY to HDY,
                QuranIndex.MGD to MGD,

                //59-86
                QuranIndex.HSR to HSR,
                QuranIndex.MUT to MUT,
                QuranIndex.SAF to SAF,
                QuranIndex.JUM to JUM,
                QuranIndex.MNF to MNF,
                QuranIndex.TGN to TGN,
                QuranIndex.TLQ to TLQ,
                QuranIndex.TRM to TRM,
                QuranIndex.MLK to MLK,
                QuranIndex.QLM to QLM,
                QuranIndex.HQA to HQA,
                QuranIndex.MRJ to MRJ,
                QuranIndex.NOH to NOH,
                QuranIndex.JIN to JIN,
                QuranIndex.MZL to MZL,
                QuranIndex.MDT to MDT,
                QuranIndex.QYM to QYM,
                QuranIndex.INS to INS,
                QuranIndex.MRS to MRS,
                QuranIndex.NBA to NBA,
                QuranIndex.NZA to NZA,
                QuranIndex.ABS to ABS,
                QuranIndex.TKR to TKR,
                QuranIndex.INF to INF,
                QuranIndex.MTF to MTF,
                QuranIndex.SQA to SQA,
                QuranIndex.BRG to BRG,
                QuranIndex.QAS to QAS,
                QuranIndex.TRQ to TRQ,


                //87-114
                QuranIndex.ALA to ALA,
                QuranIndex.GSA to GSA,
                QuranIndex.FJR to FJR,
                QuranIndex.BLD to BLD,
                QuranIndex.HMS to HMS,
                QuranIndex.LIL to LIL,
                QuranIndex.DUH to DUH,
                QuranIndex.SHR to SHR,
                QuranIndex.TIN to TIN,
                QuranIndex.ALQ to ALQ,
                QuranIndex.QDR to QDR,
                QuranIndex.BYN to BYN,
                QuranIndex.ZLA to ZLA,
                QuranIndex.ADY to ADY,
                QuranIndex.QRA to QRA,
                QuranIndex.TKU to TKU,
                QuranIndex.ASR to ASR,
                QuranIndex.HMZ to HMZ,
                QuranIndex.FIL to FIL,
                QuranIndex.QRS to QRS,
                QuranIndex.MAN to MAN,
                QuranIndex.KWA to KWA,
                QuranIndex.KFR to KFR,
                QuranIndex.NSR to NSR,
                QuranIndex.MSD to MSD,
                QuranIndex.IKS to IKS,
                QuranIndex.FLQ to FLQ,
                QuranIndex.NAS to NAS
        )

    }
}