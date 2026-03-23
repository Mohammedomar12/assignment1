package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val TAG = "SocialSparks"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "App started successfully")

        val etTimeOfDay = findViewById<EditText>(R.id.etTimeOfDay)
        val btnGetSpark = findViewById<Button>(R.id.btnGetSpark)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val tvSuggestion = findViewById<TextView>(R.id.tvSuggestion)

        btnGetSpark.setOnClickListener {
            val timeInput = etTimeOfDay.text.toString().trim()
            Log.d(TAG, "User entered: $timeInput")

            val suggestion = getSocialSpark(timeInput)

            if (suggestion != null) {
                tvSuggestion.text = suggestion
                tvSuggestion.visibility = View.VISIBLE
                Log.d(TAG, "Suggestion shown: $suggestion")
            } else {
                tvSuggestion.text = "❌ Oops! Please enter a valid time like:\n" +
                        "Morning, Mid-morning, Afternoon,\n" +
                        "Afternoon Snack Time, Dinner, or Night 😊"
                tvSuggestion.visibility = View.VISIBLE
                Log.w(TAG, "Invalid input received: $timeInput")
            }
        }

        btnReset.setOnClickListener {
            etTimeOfDay.text.clear()
            tvSuggestion.text = ""
            tvSuggestion.visibility = View.GONE
            Log.d(TAG, "App reset by user")
        }
    }

    private fun getSocialSpark(timeOfDay: String): String? {
        return when (timeOfDay.lowercase()) {
            "morning" -> "☀️ Morning Spark:\nSend a 'Good morning' text to a family member!"
            "mid-morning", "mid morning" -> "🤝 Mid-Morning Spark:\nReach out to a colleague with a quick 'Thank you'!"
            "afternoon" -> "😄 Afternoon Spark:\nShare a funny meme or interesting link with a friend!"
            "afternoon snack time", "snack time" -> "💌 Snack Time Spark:\nSend a quick 'Thinking of you' message to someone special!"
            "dinner" -> "📞 Dinner Spark:\nCall a friend or relative for a 5-minute catch-up!"
            "after dinner", "night", "evening" -> "💬 Evening Spark:\nLeave a thoughtful comment on a friend's post!"
            else -> null
        }
    }
}
