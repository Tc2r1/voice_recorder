package com.nudennie.recorder.playback

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

/**
 * AndroidAudioPlayer.kt
 *
 * Created by Nudennie White aka Tc2r on 2023...
 *
 */
class AndroidAudioPlayer(
    private val context: Context
) : AudioPlayer {

    private var player: MediaPlayer? = null

    override fun playFile(file: File) {
        MediaPlayer.create(context, file.toUri()).apply {
            player = this
            start()
        }
    }

    override fun stop() {
        player?.apply {
            stop()
            release()
        }
        player = null
    }

    override fun getStatus(): Boolean? {
        return player?.isPlaying
    }
}
