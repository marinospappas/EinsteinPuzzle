var a = arrayOf(1,2,3,4)

fun printa(ar: Array<Int>) {
    for (i in 0..3)
        print("${ar[i]} ")
    println()
}

fun doit(b: Array<Int>) {
    printa(b)
    b[1] = 0
    b[2] = 999
    printa(b)
}

fun main(args: Array<String>) {
    printa(a)
    doit(a.copyOf())
    printa(a)
}