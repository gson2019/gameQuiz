package com.example.gamequiz

//import com.example.gamequiz.SummaryFragmentArgs.Companion.fromBundle
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class SummaryFragment : Fragment() {
//    val qaList by lazy {
//        QuestionfromBundle(arguments!!).questionAnswerList
//    }

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
//        val adapter = SummaryAdapter(activity as Context, qaList.toMutableList())
//        summaryLv.adapter = adapter
        // TODO: Use the ViewModel
    }

//    private fun observeSummary(viewModel: QuestionViewModel){
//        viewModel.answers.observe(this, Observer {
//           val adapter = SummaryAdapter(activity as Context, it)
//            summaryLv.adapter = adapter
//        })
//    }

}
