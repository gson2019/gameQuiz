package com.example.gamequiz

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz.model.Question
import com.example.quiz.model.Quiz
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class QuestionViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private lateinit var job: Job
    private lateinit var questionList: MutableList<Question>
    private var _idx = MutableLiveData<Int>()
    var checkedId:Int? =null
    val idx: LiveData<Int>
        get() = _idx

    fun getNextQuestion() {
        checkedId = null
        _idx.value = _idx.value!! + 1
    }

    fun setQuestions(mContext: Context) {
        viewModelScope.launch(Dispatchers.Default){
            try{
                val inputString = mContext.assets.open("questions.json").bufferedReader().use {
                    it.readText()
                }
                val quiz = Gson().fromJson(inputString, Quiz::class.java)
                withContext(Dispatchers.Main) {
                    questionList = quiz.quiz.toMutableList()
                    if (_idx.value == null) {
                        _idx.value = 0
                    }
                }
            }catch (e: IOException){

            }
        }
    }

    private fun getQues(mContext: Context): List<Question> {
        val inputString = mContext.assets.open("questions.json").bufferedReader().use {
            it.readText()
        }
        val quiz = Gson().fromJson(inputString, Quiz::class.java)
        return quiz.quiz
    }

    fun getQuestionByIndex(id:Int):Question{
        return questionList[id]
    }

    fun getQuestionListSize():Int{
        return questionList.size
    }
}
