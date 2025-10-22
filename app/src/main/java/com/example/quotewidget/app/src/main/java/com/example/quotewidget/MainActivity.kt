package com.example.quotewidget

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.quotewidget.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editText.setText(Prefs.getText(this))

        binding.btnSave.setOnClickListener {
            val text = binding.editText.text?.toString()?.trim().orEmpty()
            Prefs.saveText(this, text.ifEmpty { "" })
            updateAllWidgets()
            binding.inputLayout.error = null
            binding.inputLayout.helperText = "已保存，回到桌面刷新小组件查看"
        }
    }

    private fun updateAllWidgets() {
        val manager = AppWidgetManager.getInstance(this)
        val component = ComponentName(this, QuoteWidgetProvider::class.java)
        val ids = manager.getAppWidgetIds(component)
        QuoteWidgetProvider.updateAppWidgets(this, manager, ids)
    }
}
