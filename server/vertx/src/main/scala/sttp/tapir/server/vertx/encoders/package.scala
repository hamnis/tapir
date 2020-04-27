package sttp.tapir.server.vertx

import java.io.InputStream

import io.vertx.core.buffer.Buffer

package object encoders {

  private val bufferSize = 1024

  /**
    * README: Tests are using a ByteArrayInputStream, which is totally fine,
    * but other blocking implementations like FileInputStream etc. should maybe be wrapped in executeBlocking:
    * it's fine for encoding responses, but will not be suitable for decoding requests (which expect an InputStream...)
    */
  private[vertx] def inputStreamToBuffer(is: InputStream): Buffer = {
    val buffer = Buffer.buffer()
    val buf = new Array[Byte](bufferSize)
    while (is.available() > 0) {
      val read = is.read(buf)
      buffer.appendBytes(buf, 0, read)
    }
    buffer
  }

}
