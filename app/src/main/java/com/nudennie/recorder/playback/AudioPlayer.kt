package com.nudennie.recorder.playback

import java.io.File

/**
 * AudioPlayer.kt
 *
 * Created by Nudennie White aka Tc2r on 2023...
 *
 */
interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
    fun getStatus(): Boolean?
}
