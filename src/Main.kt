fun main() {
    println(" Задача №1. Только что ")
    println(agoToText(30))
    println(agoToText(120))
    println(agoToText(3600))
    println(agoToText(7200))
    println(agoToText(90000))
    println(agoToText(200000))
    println(agoToText(300000))
    println()

    println("Задача №2. Разная комиссия ")
    println(calculateCommission())
    println(calculateCommission("Visa", 0.0, 5000.0))
    println(calculateCommission("MasterCard", 50000.0, 5000.0))
    println(calculateCommission("MasterCard", 80000.0, 5000.0))
    println(calculateCommission("Maestro", 10000.0, 1000.0))
    println(calculateCommission("VK Pay", 0.0, 3000.0))
}

fun agoToText(seconds: Int): String {
    return when {
        seconds in 0..60 -> "был(а) только что"
        seconds in 61..60 * 60 -> "был(а) в сети ${minutesText(seconds / 60)} назад"
        seconds in 60 * 60 + 1..24 * 60 * 60 -> "был(а) в сети ${hoursText(seconds / 3600)} назад"
        seconds in 24 * 60 * 60 + 1..2 * 24 * 60 * 60 -> "был(а) вчера"
        seconds in 2 * 24 * 60 * 60 + 1..3 * 24 * 60 * 60 -> "был(а) позавчера"
        else -> "был(а) давно"
    }
}

fun minutesText(minutes: Int): String {
    val word = when {
        minutes % 100 in 11..14 -> "минут"
        minutes % 10 == 1 -> "минуту"
        minutes % 10 in 2..4 -> "минуты"
        else -> "минут"
    }
    return "$minutes $word"
}

fun hoursText(hours: Int): String {
    val word = when {
        hours % 100 in 11..14 -> "часов"
        hours % 10 == 1 -> "час"
        hours % 10 in 2..4 -> "часа"
        else -> "часов"
    }
    return "$hours $word"
}

fun calculateCommission(
    cardType: String = "VK Pay",
    summaRanshe: Double = 0.0,
    summaPerevoda: Double = 0.0
): Double {
    return when (cardType) {
        "VK Pay" -> 0.0
        "Visa", "Мир" -> maxOf(summaPerevoda * 0.0075, 35.0)
        "MasterCard", "Maestro" -> {
            if (summaRanshe + summaPerevoda <= 75000.0) {
                0.0
            } else {
                summaPerevoda * 0.006 + 20.0
            }
        }
        else -> 0.0
    }
}