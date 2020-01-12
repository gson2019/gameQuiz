package com.example.gamequiz

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.gamequiz.model.QuestionAnswerList
import kotlinx.android.synthetic.main.question_fragment.*


class QuestionFragment : Fragment(), RadioGroup.OnCheckedChangeListener {
    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        val radioButton = p0!!.findViewById<View>(p1)
        viewModel.checkedId = p0.indexOfChild(radioButton)
    }

    companion object {
        fun newInstance() = QuestionFragment()
    }

    var alertDialog:AlertDialog? = null
    private lateinit var viewModel: QuestionViewModel
    private lateinit var mContext:Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.question_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(QuestionViewModel::class.java)
        nextBtn.setOnClickListener {
            if(viewModel.idx.value == viewModel.getQuestionListSize()){
                displayDialog("End of Quiz", "Go to quiz result summary", true)
            }else if(viewModel.checkedId == null){
                displayDialog("Answer not Chosen", "Please choose an answer!", false)
            }else{
                viewModel.getNextQuestion()
            }
        }
        answersRg.setOnCheckedChangeListener(this)
        viewModel.setQuestions(mContext)
        viewModel.idx.observe(this, Observer { idx ->
            if(idx == viewModel.getQuestionListSize()){
                displayDialog("End of Quiz", "Go to quiz result summary", true)
            }else {
                Log.i("QUES", "Observe data")
                val question = viewModel.getQuestionByIndex(idx)
                val questionText = idx.plus(1).toString()  + ". " + question.question
                questionTv.text = questionText
                answersRg.removeAllViews()
                question.options.forEachIndexed { qIndex, answer ->
                    run {
                        val radioButton = RadioButton(mContext)
                        val ansIdx: Char = 'A' + qIndex
                        val ans: String = "$ansIdx. $answer"
                        radioButton.text = ans
                        radioButton.id = View.generateViewId()
                        answersRg.addView(radioButton)
                    }
                }
            }
            if(viewModel.checkedId != null){
                (answersRg.getChildAt(viewModel.checkedId as Int) as RadioButton).isChecked = true
            }
        })
    }

    fun displayDialog(title:String, msg: String, quizEnd: Boolean){
        val builder = AlertDialog.Builder(mContext)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Go Summary"){
                dialogInterface, which ->
            Toast.makeText(mContext, "clicked yes", Toast.LENGTH_LONG).show()
            if(quizEnd) {
                val qaList = QuestionAnswerList()
                qaList.addAll(viewModel.answers)
                findNavController().navigate(
                    QuestionFragmentDirections.actionQuestionDestinationToSummaryFragment(
                        qaList
                    )
                );
            }
        }
        builder.setNegativeButton("Back Question"){
                dialogInterface, which ->
            Toast.makeText(mContext, "back question", Toast.LENGTH_LONG).show()
        }
        alertDialog = builder.create()
        alertDialog!!.setCancelable(true)
        alertDialog!!.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(alertDialog != null)
            alertDialog!!.dismiss()
        if(::viewModel.isInitialized){
            viewModel.idx.removeObservers(this)
        }
    }

}
