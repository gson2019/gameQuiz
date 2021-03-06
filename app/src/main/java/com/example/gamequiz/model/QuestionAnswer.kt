package com.example.gamequiz.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuestionAnswer constructor(val question: String, val answer: String) : Parcelable