package moe.tlaster.shibasample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.android.synthetic.main.activity_live.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import com.here.oksse.ServerSentEvent
import com.here.oksse.OkSse



class LiveActivity : AppCompatActivity() {

    var dataContext: JsonNode? = null

    private val streaming = Streaming({
        onDataChanged(it)
    }, {
        onLayoutChanged(it)
    })

    private var shutdown: ServerSentEvent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live)

        hostInput.setText(streaming.host)

        connectButton.setOnClickListener {
            shutdown?.close()
            streaming.host = hostInput.text.toString()
            shutdown = streaming.fetch()
        }
        disconnectButton.setOnClickListener {
            shutdown?.close()
        }
    }

    private fun onDataChanged(jsonNode: JsonNode) {
        runOnUiThread {
            dataContext = jsonNode
            shibaHost.dataContext = dataContext
        }
    }

    private fun onLayoutChanged(data: String) {
        runOnUiThread {
            shibaHost.load(data, dataContext)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        shutdown?.close()
    }
}

class Client {

    private val client: OkHttpClient = OkHttpClient.Builder().build()

    fun get(url: String): Response {
        val call = client.newCall(
                Request.Builder()
                        .url(url)
                        .get()
                        .build())
        return call.execute()
    }

}

class Streaming(private val dataCallback: (JsonNode) -> Unit, private val layoutCallback: (String) -> Unit) {

    private val client = Client()

    var host = "http://10.0.2.2:5000/streaming"

    fun fetch(): ServerSentEvent? {
        val request = Request.Builder().url(host).build()
        val okSse = OkSse()
        return okSse.newServerSentEvent(request, object : ServerSentEvent.Listener {
            override fun onOpen(sse: ServerSentEvent?, response: Response?) {
            }

            override fun onRetryTime(sse: ServerSentEvent?, milliseconds: Long): Boolean {
                return true
            }

            override fun onComment(sse: ServerSentEvent?, comment: String?) {
            }

            override fun onRetryError(sse: ServerSentEvent?, throwable: Throwable?, response: Response?): Boolean {
                return true
            }

            override fun onPreRetry(sse: ServerSentEvent?, originalRequest: Request?): Request {
                return originalRequest ?: throw Error()
            }

            override fun onMessage(sse: ServerSentEvent?, id: String?, event: String?, message: String?) {
                when (event) {
                    "data_changed" -> {
                        dataCallback.invoke(ObjectMapper().readTree(message?.trim('"')))
                    }
                    "layout_changed" -> {
                        if (message != null) {
                            layoutCallback.invoke(message.trim('"'))
                        }
                    }
                }
            }

            override fun onClosed(sse: ServerSentEvent?) {
            }

        })
    }


//    fun fetch(): Shutdownable {
//        val response = client.get(host)
//        if (response.isSuccessful) {
//            val reader = response.body()?.byteStream()?.bufferedReader() ?: throw Error()//.body().byteStream().bufferedReader()
//            val dispatcher = Dispatcher()
//            dispatcher.invokeLater(Runnable {
//                while (true) {
//                    try {
//                        val line = reader.readLine()
//                        if (line == null || line.isEmpty()) {
//                            continue
//                        }
//                        val type = line.split(":")[0].trim()
//                        if (type != "event") {
//                            continue
//                        }
//                        val event = line.split(":")[1].trim()
//                        val payload = reader.readLine()
//                        val payloadType = payload.split(":")[0].trim()
//                        if (payloadType != "data") {
//                            continue
//                        }
//
//                        val start = payload.indexOf(":") + 1
//                        val data = payload.substring(start).trim().trim('"')
//                        when (event) {
//                            "data_changed" -> {
//                                dataCallback.invoke(ObjectMapper().readTree(data))
//                            }
//                            "layout_changed" -> {
//                                layoutCallback.invoke(data)
//                            }
//                        }
//
////                        if (event == "update") {
////                            val start = payload.indexOf(":") + 1
////                            val json = payload.substring(start).trim()
////                            val status = client.getSerializer().fromJson(
////                                    json,
////                                    Status::class.java
////                            )
////                            handler.onStatus(status)
////                        }
//                    } catch (e: java.io.InterruptedIOException) {
//                        break
//                    }
//                }
//                reader.close()
//            })
//            return Shutdownable(dispatcher)
//        } else {
//            throw Error()
//        }
//    }
}


class Shutdownable(private val dispatcher: Dispatcher) {
    private val lock = ReentrantLock()

    fun shutdown() {
        lock.withLock {
            dispatcher.shutdown()
        }
    }
}


class Dispatcher {
    val executorService: ExecutorService = Executors.newFixedThreadPool(1) { r ->
        val thread = Thread(r)
        thread.isDaemon = true
        return@newFixedThreadPool thread
    }

    val lock = ReentrantLock()
    val shutdownTime = 1000L

    fun invokeLater(task: Runnable) = executorService.execute(task)

    fun shutdown() {
        lock.withLock {
            executorService.shutdown()
            if (!executorService.awaitTermination(shutdownTime, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow()
            }
        }
    }
}