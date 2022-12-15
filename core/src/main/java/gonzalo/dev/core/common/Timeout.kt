package gonzalo.dev.core.common

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Timeout(val timeout: Long)
