package com.example.flashcardapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("score", 0)
        val questions = intent.getStringArrayExtra("questions") ?: arrayOf()
        val answers = intent.getBooleanArrayExtra("answers") ?: booleanArrayOf()
        val userAnswers = intent.getBooleanArrayExtra("userAnswers") ?: booleanArrayOf()

        val scoreText: TextView = findViewById(R.id.scoreText)
        val reviewText: TextView = findViewById(R.id.reviewText)
        val reviewButton: Button = findViewById(R.id.reviewButton)
        val exitButton: Button = findViewById(R.id.exitButton)

        scoreText.text = "You scored $score out of ${questions.size}."
        val feedback = if (score >= 3) "Great job!" else "Keep practicing!"
        scoreText.append("\n$feedback")

        reviewButton.setOnClickListener {
            val builder = StringBuilder()
            for (i in questions.indices) {
                builder.append("Q${i + 1}: ${questions[i]}\n")
                builder.append("Your Answer: ${userAnswers.getOrNull(i)} | Correct: ${answers.getOrNull(i)}\n\n")
            }
            reviewText.text = builder.toString()
        }

        exitButton.setOnClickListener {
            finishAffinity()
        }
    }
}
