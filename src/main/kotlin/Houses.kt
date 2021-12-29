enum class Nat {
    English, Spanish, Norwegian, Japanese, Ukrainian, none
}

enum class Col {
    Red, Green, Ivory, Yellow, Blue, none
}

enum class Pet {
    Dog, Snails, Fox, Horse, Zebra, none
}

enum class Drnk {
    Coffee, Tea, Milk, OJ, Water, none
}

enum class Cig {
    Kools, Chesterfields, Parliaments, LuckyStrike, OldGold, none
}

class House(var nationality: Nat = Nat.none, var colour: Col = Col.none, var pet: Pet = Pet.none,
            var drink: Drnk = Drnk.none, var cigarettes: Cig = Cig.none)