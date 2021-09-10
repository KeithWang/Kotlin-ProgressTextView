package vic.sample.progresstextview

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import vic.sample.progresstextview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Main) {
            for (i in 0..100) {
                delay(20)
                binding.progressTextView.setProgress(i)
                binding.progressTextView.setText("Processing ... $i %")

                binding.progressTextViewDecrease.setProgress(i)
                binding.progressTextViewDecrease.setText("Processing ... $i %")

            }
            binding.seekbar.visibility = View.VISIBLE
            binding.seekbar.progress = 100
        }

        binding.seekbar.onProgressChanged { seekBar, progress, fromUser ->
            val i = seekBar?.progress
            i?.let {
                binding.progressTextView.setProgress(it)
                binding.progressTextView.setText("Processing ... $it %")

                binding.progressTextViewDecrease.setProgress(it)
                binding.progressTextViewDecrease.setText("Processing ... $it %")
            }
        }
    }
}

fun SeekBar.onProgressChanged(onProgressChanged: (seekBar: SeekBar?, progress: Int, fromUser: Boolean) -> Unit) {
    this.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            onProgressChanged.invoke(seekBar, progress, fromUser)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
        }
    })
}