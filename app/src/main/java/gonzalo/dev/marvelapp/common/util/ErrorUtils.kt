package gonzalo.dev.marvelapp.common.util

import android.content.Context
import gonzalo.dev.marvelapp.R
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorUtils {

    /**
     * Convert an exception in a message friendly to the user.
     *
     * @param applicationContext The application context.
     * @param error The error exception.
     */
    fun errorMessage(applicationContext: Context, error: Throwable): String {
        return when (error) {
            is SocketTimeoutException -> return applicationContext.getString(R.string.timeout_message)
            is UnknownHostException -> return applicationContext.getString(R.string.no_internet_conn)
            else -> ""
        }
    }
}