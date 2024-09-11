import java.util.*

const val alpha = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЫЬЭЮЯ"

fun main()
{
    println("Введите сообщение:")
    val m = readln().uppercase(Locale.getDefault())

    println("Введите ключ:")
    val k = readln().uppercase(Locale.getDefault())

    println("Методом Виженера (1) / генерация случайно (2)?")
    val c = readln().toInt()

    val t = if (c == 1) genTableST() else genTableRand()

    println("Исходное сообщение: $m")
    val extK = extK(m, k)
    println("Ключ: $extK")

    val enc = enc(m, extK, t)
    println("Зашифрованное сообщение: $enc")
}

fun genTableST(): Array<CharArray>
{
    val table = Array(alpha.length) { CharArray(alpha.length) }
    for (i in alpha.indices)
    {
        for (j in alpha.indices)
        {
            table[i][j] = alpha[(j + i) % alpha.length]
        }
    }
    return table
}

fun genTableRand(): Array<CharArray>
{
    val sAlpha = alpha.toList().shuffled().toCharArray()
    val table = Array(alpha.length) { CharArray(alpha.length) }
    for (i in sAlpha.indices)
    {
        for (j in sAlpha.indices)
        {
            table[i][j] = sAlpha[(j + i) % sAlpha.size]
        }
    }
    return table
}

fun extK(mess: String, key: String): String
{
    val keyB = StringBuilder()
    var kI = 0
    for (i in mess.indices)
    {
        if (alpha.contains(mess[i]))
        {
            keyB.append(key[kI % key.length])
            kI++
        }

        else
        {
            keyB.append(mess[i])
        }
    }
    return keyB.toString()
}

fun enc(mess: String, key: String, vTab: Array<CharArray>): String
{
    val encM = StringBuilder()
    for (i in mess.indices)
    {
        val mesC = mess[i]
        val keyC = key[i]
        if (alpha.contains(mesC) && alpha.contains(keyC))
        {
            val row = alpha.indexOf(keyC)
            val col = alpha.indexOf(mesC)
            encM.append(vTab[row][col])
        }

        else
        {
            encM.append(mesC)
        }
    }
    return encM.toString()
}
