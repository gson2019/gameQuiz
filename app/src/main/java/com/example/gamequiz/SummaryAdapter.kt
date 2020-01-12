package com.example.gamequiz

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import androidx.core.content.getSystemService
import com.example.gamequiz.model.QuestionAnswer
import kotlinx.android.synthetic.main.question_fragment.view.*
import kotlinx.android.synthetic.main.question_fragment.view.questionTv
import kotlinx.android.synthetic.main.question_summary_item.view.*

class SummaryAdapter(private val context: Context,
                     private val qaList: MutableList<QuestionAnswer>) : BaseAdapter(){
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.question_summary_item, p2, false);
        rowView.questionTv.text = qaList[p0].question
        rowView.answerTv.text = qaList[p0].answer
        return rowView
    }

    override fun getItem(p0: Int): Any {
        return qaList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return qaList.size
    }

}