package ca.algomau.console.calculator

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private var displayValue: UInt = 0u
    private var storedValue: UInt = 0u
    private var operator = '\u+000'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle=SystemBarStyle.dark(scrim=Color.TRANSPARENT))
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        tvDisplay = findViewById(R.id.tvDisplay)

        findViewById<Button>(R.id.btEquals).setOnClickListener {_ ->
            displayValue = when(operator) {
                '+' -> storedValue + displayValue
                '-' -> storedValue - displayValue
                'X' -> storedValue * displayValue
                '÷' -> storedValue / displayValue
                '%' -> storedValue % displayValue
                else -> displayValue
            }
            operator = '\u+000'
            tvDisplay.text = displayValue.toString()
        }

        findViewById<Button>(R.id.btClear).setOnClickListener {_ ->
            displayValue = 0u
            storedValue = 0u
            operator = '\u+000'
            tvDisplay.text = "0"
        }

    }

    fun onNumberTapped(view: View) {
        displayValue *= 10u
        displayValue += (view as Button).text.toString().toUInt()
        tvDisplay.text = displayValue.toString()
    }

    fun onOperatorTapped(view: View) {
        storedValue = displayValue
        displayValue = 0u
        operator = (view as Button).text[0]
        tvDisplay.text = "0"
    }
}