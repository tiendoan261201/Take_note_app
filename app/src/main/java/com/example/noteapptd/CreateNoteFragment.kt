package com.example.noteapptd

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.noteapptd.Database.NoteDatabase
import com.example.noteapptd.databinding.FragmentCreateNoteBinding
import com.example.noteapptd.entitites.Notes
import com.example.noteapptd.utils.NoteBottomSheetFragment
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class CreateNoteFragment : BaseFragment() {
    var currentDate: String? = null
    var selectedColor = "#171C26"
    private lateinit var binding: FragmentCreateNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CreateNoteFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        currentDate = sdf.format(Date())
        binding.tvDateTime.text = currentDate

        binding.imgDone.setOnClickListener {
            saveNote()
        }
        binding.imgBack.setOnClickListener {
            replaceFragment(HomeFragment.newInstance(), true)
        }
        binding.imgMore.setOnClickListener {

            LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
                BroadcastReceiver, IntentFilter("bottom_sheet_action")
            )


            var noteBottomSheetFragment = NoteBottomSheetFragment.newInstance()
            noteBottomSheetFragment.show(
                requireActivity().supportFragmentManager,
                "Note Bottom Sheet Fragment"
            )
        }

    }

    private fun saveNote() {
        //check condition
        if (binding.etNoteTitle.text.isNullOrEmpty()) {
            Toast.makeText(context, "Note Title is required", Toast.LENGTH_SHORT).show()
        } else if (binding.etNoteSubTitle.text.isNullOrEmpty()) {
            Toast.makeText(context, "Note subtitle is required", Toast.LENGTH_SHORT).show()
        } else if (binding.etNoteDesc.text.isNullOrEmpty()) {
            Toast.makeText(context, "Note description is required", Toast.LENGTH_SHORT).show()
        }
        //launch
        launch {
            var notes = Notes()
            //set text to note
            notes.title = binding.etNoteTitle.text.toString()
            notes.subTitle = binding.etNoteSubTitle.text.toString()
            notes.noteText = binding.etNoteDesc.text.toString()
            notes.dateTime = currentDate
            context?.let {
                NoteDatabase.getDatabase(it).noteDao().insertNotes(notes)
                binding.etNoteDesc.setText("")
                binding.etNoteTitle.setText("")
                binding.etNoteSubTitle.setText("")
                requireActivity().supportFragmentManager.popBackStack()
            }

        }
    }

    fun replaceFragment(fragment: Fragment, istransition: Boolean) {
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()

        if (istransition) {
            fragmentTransition.setCustomAnimations(
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left
            )
        }
        fragmentTransition.replace(R.id.frame_layout, fragment)
            .addToBackStack(fragment.javaClass.simpleName).commit()
    }

    private val BroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {

            var actionColor = p1!!.getStringExtra("action")

            when (actionColor!!) {

                "Blue" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }

                "Yellow" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Purple" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Green" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Orange" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }


                "Black" -> {
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }
                else -> {
//                    layoutImage.visibility = View.GONE
//                    imgNote.visibility = View.GONE
//                    layoutWebUrl.visibility = View.GONE
                    selectedColor = p1.getStringExtra("selectedColor")!!
                    binding.colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }

            }
        }
    }
    override fun onDestroy() {

        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(BroadcastReceiver)
        super.onDestroy()


    }
}