package kartikey.saran.aisle.Views.Dashboard.ui.matches

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kartikey.saran.aisle.R

class MatchFragment : Fragment() {

    companion object {
        fun newInstance() = MatchFragment()
    }

    private lateinit var viewModel: MatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MatchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}