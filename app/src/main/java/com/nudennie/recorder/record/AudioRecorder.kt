package com.nudennie.recorder.record

import java.io.File

/**
 * AudioRecorder.kt
 *
 * Created by Nudennie White aka Tc2r on 2023...
 *
 */
interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}
