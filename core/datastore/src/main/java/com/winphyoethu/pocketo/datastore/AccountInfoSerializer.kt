package com.winphyoethu.pocketo.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.winphyoethu.pocketo.AccountInfo
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class AccountInfoSerializer @Inject constructor() : Serializer<AccountInfo> {

    override val defaultValue: AccountInfo = AccountInfo.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): AccountInfo =
        try {
            AccountInfo.parseFrom(input)
        } catch (e: CorruptionException) {
            throw CorruptionException("Cannot read proto.", e)
        }

    override suspend fun writeTo(t: AccountInfo, output: OutputStream) = t.writeTo(output)
}