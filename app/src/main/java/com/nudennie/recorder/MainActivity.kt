package com.nudennie.recorder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.nudennie.recorder.playback.AndroidAudioPlayer
import com.nudennie.recorder.record.AndroidAudioRecorder
import com.nudennie.recorder.ui.theme.RecorderTheme
import java.io.File

class MainActivity : ComponentActivity() {
    private val recorder by lazy {
        AndroidAudioRecorder(applicationContext)
    }

    private val player by lazy {
        AndroidAudioPlayer(applicationContext)
    }

    private var audioFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.RECORD_AUDIO),
            0
        )

        setContent {
            var isRecording by remember { mutableStateOf(false) }
            var playerStatus by remember { mutableStateOf(player.getStatus()) }
            var isPlaying by remember { mutableStateOf(false) }


            RecorderTheme {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center

                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick =
                            if (isRecording) {
                                {
                                    isRecording = false
                                    recorder.stop()
                                }
                            } else {
                                {
                                    isRecording = true
                                    File(cacheDir, "audio.mp3").also {
                                        recorder.start(it)
                                        audioFile = it
                                    }
                                }
                            }
                        ) {
                            Image(
                                painter =
                                painterResource(
                                    id = if (isRecording) {
                                        R.drawable.baseline_stop_circle_48
                                    } else {
                                        R.drawable.baseline_mic_48
                                    }
                                ),
                                contentDescription = "Recording Button"
                            )
                        }
                        Text(
                            modifier = Modifier.width(100.dp).align(alignment = Alignment.CenterHorizontally),
                            textAlign = TextAlign.Center,
                            text =
                            if (isRecording) {
                                "Stop Recording"
                            } else {
                                "Start Recording"
                            },
                            maxLines = 2
                        )
                    }

                    Column(
                        modifier = Modifier.padding(24.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = {
                                if (isPlaying || playerStatus == true) {
                                    isPlaying = false
                                    player.stop()
                                } else {
                                    player.playFile(audioFile ?: return@IconButton)
                                    isPlaying = true
                                }
                            }
                        ) {
                            Image(
                                painter =
                                painterResource(
                                    id = if (isPlaying || playerStatus == true) {
                                        R.drawable.baseline_pause_48
                                    } else {
                                        R.drawable.baseline_play_arrow_48
                                    }
                                ),
                                contentDescription = "Play Button"
                            )
                        }

                        Text(
                            modifier = Modifier.width(100.dp).align(alignment = Alignment.CenterHorizontally),
                            textAlign = TextAlign.Center,
                            text =
                            if (isPlaying || playerStatus == true) {
                                "Stop"
                            } else {
                                "Play"
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecorderTheme {
    }
}
