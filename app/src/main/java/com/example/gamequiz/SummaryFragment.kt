package com.example.gamequiz

//import com.example.gamequiz.SummaryFragmentArgs.Companion.fromBundle
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.summary_fragment.*


class SummaryFragment : Fragment() {
    val qaList by lazy {
        SummaryFragmentArgs.fromBundle(arguments!!).questionAnswerList
    }

    companion object {
        fun newInstance() = SummaryFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.summary_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = SummaryAdapter(activity as Context, qaList.toMutableList())
        summaryLv.adapter = adapter
        // TODO: Use the ViewModel
    }

}
