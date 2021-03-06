package com.bybutter.sisyphus.starter.grpc.transcoding.codec

import com.bybutter.sisyphus.protobuf.Message
import com.bybutter.sisyphus.protobuf.ProtoSupport
import kotlin.reflect.full.companionObjectInstance
import org.springframework.core.ResolvableType
import org.springframework.core.codec.AbstractDataBufferDecoder
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.util.MimeType

class ProtobufDecoder(vararg mimeTypes: MimeType) : AbstractDataBufferDecoder<Message<*, *>>(*mimeTypes) {
    override fun decode(buffer: DataBuffer, targetType: ResolvableType, mimeType: MimeType?, hints: Map<String, Any>?): Message<*, *> {
        val support = targetType.rawClass.kotlin.companionObjectInstance as ProtoSupport<*, *>
        return support.parse(buffer.asInputStream())
    }
}
