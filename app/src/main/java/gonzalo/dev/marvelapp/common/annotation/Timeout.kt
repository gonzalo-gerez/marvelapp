package gonzalo.dev.marvelapp.common.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Timeout(val timeout: Long)
