package com.example.flashcardapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private val questions = arrayOf(
        "The capital of Australia is Sydney.",
        "The Pacific Ocean is the largest ocean on Earth.",
        "Humans have five senses.",
        "The Great Pyramid of Giza is in Egypt.",
        "Mount Everest is the tallest mountain above sea level."
    )

    private val answers = booleanArrayOf(true, false, true, true, false)

    private var currentIndex = 0
    private var score = 0
    private val userAnswers = BooleanArray(questions.size)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val questionText: TextView = findViewById(R.id.questionText)
        val feedbackText: TextView = findViewById(R.id.feedbackText)
        val trueButton: Button = findViewById(R.id.trueButton)
        val falseButton: Button = findViewById(R.id.falseButton)
        val nextButton: Button = findViewById(R.id.nextButton)

        fun loadQuestion() {
            questionText.text = questions[currentIndex]
            feedbackText.text = ""
        }

        loadQuestion()

        trueButton.setOnClickListener {
            checkAnswer(true, feedbackText)
        }

        falseButton.setOnClickListener {
            checkAnswer(false, feedbackText)
        }

        nextButton.setOnClickListener {
            if (currentIndex < questions.size - 1) {
                currentIndex++
                loadQuestion()
            } else {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("questions", questions)
                intent.putExtra("answers", answers)
                intent.putExtra("userAnswers", userAnswers)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun checkAnswer(selected: Boolean, feedbackText: TextView) {
        val correct = answers[currentIndex]
        userAnswers[currentIndex] = selected

        if (selected == correct) {
            feedbackText.text = "Correct!"
            score++
        } else {
            feedbackText.text = "Incorrect."
        }
    }
}
