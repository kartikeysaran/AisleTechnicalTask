package kartikey.saran.aisle.Views.Dashboard.ui.notes

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kartikey.saran.aisle.API.ResultData
import kartikey.saran.aisle.Adapter.LikesAdapter
import kartikey.saran.aisle.Model.Note
import kartikey.saran.aisle.R
import kartikey.saran.aisle.Utils.PrefUtil
import kartikey.saran.aisle.Views.PhoneNumber.PhoneNumberActivity
import kartikey.saran.aisle.databinding.FragmentNotesBinding

@AndroidEntryPoint
class NotesFragment : Fragment() {

    companion object {
        fun newInstance() = NotesFragment()
    }

    private val viewModel: NotesViewModel by viewModels()
    private lateinit var binding: FragmentNotesBinding
    private lateinit var token: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpObserver()
        setUpViews()
    }

    private fun setUpObserver() {
        viewModel.profileListData.observe(viewLifecycleOwner) {
            when (it) {
                is ResultData.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is ResultData.Success -> {
                    binding.progressBar.visibility = View.GONE
                    setData(it.data)
                    Toast.makeText(context, getString(R.string.you_are_good_to_go), Toast.LENGTH_SHORT).show()
                }

                is ResultData.Failed -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, getString(R.string.please_try_again_later)+" "+it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setUpViews() {
        activity?.let {
            PrefUtil(it).getToken.asLiveData().observe(it, Observer {
                if (it != null && it != "") {
                    token = it
                    viewModel.getTestProfiles(token)
                } else {
                    val intent = Intent(activity, PhoneNumberActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    Toast.makeText(context, getString(R.string.please_login_first), Toast.LENGTH_SHORT).show()
                    activity?.finish()
                }
            })
        }
    }

    private fun setData(note: Note?) {
        if (note != null) {
            context?.let {
                Glide.with(it)
                    .load(note.invites.profiles.get(0).photos.get(0).photo)
                    .into(binding.iVImg1)
            }
            binding.tVName.setText(note.invites.profiles.get(0).general_information.first_name +", "+note.invites.profiles.get(0).general_information.age)
            val adapter = LikesAdapter(note.likes.profiles)
            binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
            binding.recyclerView.adapter = adapter
        } else {
            Toast.makeText(context, getString(R.string.some_error_occurred_please_try_again_later), Toast.LENGTH_SHORT).show()
        }
    }
}