package com.android.labo

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.labo.enums.Constants
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class SocketIOActivity : AppCompatActivity() {
    private val TAG = "[SocketIOTAG]"
    private var mSocket = MainApplication.getSocket()

    private lateinit var editUsername: EditText
    private lateinit var editUUID: EditText
    private lateinit var textLogOutput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socket_client)

        editUsername = findViewById(R.id.edit_username)
        editUUID = findViewById(R.id.edit_uuid)

        mSocket.on(Socket.EVENT_CONNECT, onConnectListener)
        mSocket.on(Socket.EVENT_DISCONNECT, onDisconnectListener)
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectErrorListener)
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectTimeoutListener)
        mSocket.on(Constants.EVENT_FORCE_LOGOUT, onForceLogoutListener)
        mSocket.connect()
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "onDestroy()")

        mSocket.disconnect()
        mSocket.off(Socket.EVENT_CONNECT, onConnectListener)
        mSocket.off(Socket.EVENT_DISCONNECT, onDisconnectListener)
        mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectErrorListener)
        mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectTimeoutListener)
        mSocket.off(Constants.EVENT_FORCE_LOGOUT, onForceLogoutListener)
    }

    fun clickSend(view: View) {
        val username = editUsername.text.toString()
        val uuid = editUUID.text.toString()

        Log.d(TAG, "username = $username")
        Log.d(TAG, "uuid = $uuid")

        mSocket.emit(Constants.EVENT_LOGOUT_OTHER_SESSIONS,
                JSONObject("""{"username":"$username", "uuid":$uuid}"""))
    }

    // Listener
    private var onConnectListener = Emitter.Listener { args ->
        Log.i(TAG, "onConnectListener() : args.count = ${args.count()}")

        if (args.count() > 0) {
            val data = args[0]
            Log.i(TAG, "onConnectListener() : $data")
        }
    }

    private var onDisconnectListener = Emitter.Listener { args ->
        Log.i(TAG, "onDisconnectListener() : args.count = ${args.count()}")

        if (args.count() > 0) {
            val data = args[0]
            Log.i(TAG, "onDisconnectListener() : $data")
        }
    }

    private var onConnectErrorListener = Emitter.Listener { args ->
        Log.i(TAG, "onConnectErrorListener() : args.count = ${args.count()}")

        if (args.count() > 0) {
            val data = args[0]
            Log.i(TAG, "onConnectErrorListener() : $data")
        }
    }

    private var onConnectTimeoutListener = Emitter.Listener { args ->
        Log.i(TAG, "onConnectTimeoutListener() : args.count = ${args.count()}")

        if (args.count() > 0) {
            val data = args[0]
            Log.i(TAG, "onConnectTimeoutListener() : $data")
        }
    }

    private var onForceLogoutListener = Emitter.Listener { args ->
        Log.i(TAG, "onForceLogoutListener() : args.count = ${args.count()}")

        if (args.count() > 0) {
            val data = args[0]
            Log.i(TAG, "onForceLogoutListener() : $data")
        }
    }

    private fun getCurrentUtcDateTime(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
        } else {
            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US).format(Date())
        }
    }
}