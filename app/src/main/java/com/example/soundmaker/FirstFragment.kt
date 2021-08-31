package com.example.soundmaker

import android.media.AudioAttributes
import android.media.AudioManager.STREAM_MUSIC
import android.media.SoundPool
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.soundmaker.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var pool: SoundPool;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val audioAttr = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).build()
        pool = SoundPool.Builder().setMaxStreams(10).setAudioAttributes(audioAttr).build()
        val sound1 = pool.load(activity, R.raw.arcade_casino, 1)
        val sound2 = pool.load(activity, R.raw.bass_guitar, 1)

        binding.btnArcade.setOnClickListener {
            val loop = binding.editLoop.text.toString().toInt()
            val rate = binding.editRate.text.toString().toFloat()
            pool.play(sound1, 1F, 1F, 0, loop, rate)
        }
        binding.btnBass.setOnClickListener {
            val loop = binding.editLoop.text.toString().toInt()
            val rate = binding.editRate.text.toString().toFloat()
            pool.play(sound2, 1F, 1F, 0, loop, rate)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        pool.release()
    }
}