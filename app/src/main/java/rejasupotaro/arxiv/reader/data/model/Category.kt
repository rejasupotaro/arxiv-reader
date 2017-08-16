package rejasupotaro.arxiv.reader.data.model

import android.support.annotation.ColorRes
import rejasupotaro.arxiv.reader.R

data class Category(
        val primary: String,
        val sub: String,
        @ColorRes val color: Int
) {

    companion object {
        fun stringToModel(iterm: String): Category {
            var primary = ""
            var sub = ""
            if (iterm.contains(" - ")) {
                iterm.split(" - ").let {
                    primary = it[0].trim()
                    sub = it[1].trim()
                }
            } else {
                primary = iterm
            }

            // "Astrophysics"
            // "Computer Science - *"
            // "Mathematics - *"
            // "Mathematical Physics"
            // "Nonlinear Sciences - *"
            // "Nuclear Experiment"
            // "Nuclear Theory"
            // "Physics"
            // "Physics - *"
            // "High Energy Physics"
            // "Quantum Physics"
            // "Quantitative Biology - *"
            // "Statistics - *"
            val color = if (primary == "Astrophysics") {
                R.color.ink_turquoise
            } else if (primary == "Computer Science") {
                R.color.ink_orange
            } else if (primary.startsWith("Mathematic")) {
                R.color.ink_blue
            } else if (primary == "Nonlinear Science") {
                R.color.ink_lime_green
            } else if (primary.contains("Physics") || primary.contains("Quant")) {
                R.color.ink_pink
            } else if (primary == "Statistics") {
               R.color.ink_purple
            } else {
                R.color.md_grey_600
            }

            return Category(primary, sub, color)
        }
    }
}

