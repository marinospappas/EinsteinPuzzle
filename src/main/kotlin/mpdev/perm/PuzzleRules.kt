package mpdev.perm
/** get index by attribute */
fun getIndexByColour(col: String): Int {
    for (i in 0..4)
        if (colour[i] == col)
            return i
    return -1
}
fun getIndexByNationality(nat: String): Int {
    for (i in 0..4)
        if (nationality[i] == nat)
            return i
    return -1
}
fun getIndexByDrink(drnk: String): Int {
    for (i in 0..4)
        if (drink[i] == drnk)
            return i
    return -1
}
fun getIndexByPet(pt: String): Int {
    for (i in 0..4)
        if (pet[i] == pt)
            return i
    return -1
}
fun getIndexByCigarettes(cig: String): Int {
    for (i in 0..4)
        if (cigarette[i] == cig)
            return i
    return -1
}

/** verify puzzle conditions */
fun verifyPuzzle(): Boolean {
    if (colour[getIndexByNationality("English")] != "Red")
        return false
    if (pet[getIndexByNationality("Spanish")] != "Dog")
        return false
    if (colour[getIndexByDrink("Coffee")] != "Green")
        return false
    if (drink[getIndexByNationality("Ukranian")] != "Tea")
        return false
    if (getIndexByColour("Green") - getIndexByColour("Ivory") != 1)
        return false
    if (pet[getIndexByCigarettes("OldGold")] != "Snails")
        return false
    if (colour[getIndexByCigarettes("Kool")] != "Yellow")
        return false
    if (drink[2] != "Milk")
        return false
    if (nationality[0] != "Norwegian")
        return false
    if (getIndexByCigarettes("Chesterfield") - getIndexByPet("Fox") != 1)
        return false
    if (getIndexByCigarettes("Kool") - getIndexByPet("Horse") != 1)
        return false
    if (drink[getIndexByCigarettes("LuckyStrike")] != "OrangeJuice")
        return false
    if (cigarette[getIndexByNationality("Japanese")] != "Parliament")
        return false
    if (getIndexByNationality("Norwegian") - getIndexByColour("Blue") != 1)
        return false
    return true
}
