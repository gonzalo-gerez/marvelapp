package gonzalo.dev.marvelapp.common.annotation

import java.math.BigInteger
import java.security.MessageDigest

object Utils {

    fun toMD5(privateKey: String, publicKey: String): String {
        return BigInteger(
            1,
            MessageDigest.getInstance("MD5")
                .digest(("1$privateKey$publicKey").toByteArray())
        ).toString(16).padStart(32, '0')
    }
}