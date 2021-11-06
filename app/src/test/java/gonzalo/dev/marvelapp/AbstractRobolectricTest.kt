package gonzalo.dev.marvelapp

import android.app.Application
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
abstract class AbstractRobolectricTest {

    fun getAppContext(): Application = RuntimeEnvironment.application
}