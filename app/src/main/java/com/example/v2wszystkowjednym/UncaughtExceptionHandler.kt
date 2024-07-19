import android.content.Context
import java.io.File

class MyExceptionHandler(private val context: Context) : Thread.UncaughtExceptionHandler {

    private val defaultUEH: Thread.UncaughtExceptionHandler? = Thread.getDefaultUncaughtExceptionHandler()

    override fun uncaughtException(thread: Thread, ex: Throwable) {
        // Zapisz informacje o wyjątku do pliku lub bazy danych
        val crashLog = ex.stackTraceToString()
        val crashFile = File(context.filesDir, "crash_log.txt")
        crashFile.writeText(crashLog)

        // Wywołaj domyślny UncaughtExceptionHandler
        defaultUEH?.uncaughtException(thread, ex)
    }
}

