package mpdev.perm
class HouseTEst {
    // the 5 houses here
    var houses = mutableListOf(House())

    /** create initial list of 5 houses */
    fun createHouseList() {
        for (i in 1..5)
            houses.add(House())
    }

    /** get house by attribute - 5 different functions */
    fun getByNationality(nationality: Nat): House? {
        for (house in houses)
            if (house.nationality == nationality)
                return house
        return null
    }

    fun getByColour(colour: Col): House? {
        for (house in houses)
            if (house.colour == colour)
                return house
        return null
    }

    fun getByPet(pet: Pet): House? {
        for (house in houses)
            if (house.pet == pet)
                return house
        return null
    }

    fun getByDrink(drink: Drnk): House? {
        for (house in houses)
            if (house.drink == drink)
                return house
        return null
    }

    fun getByCigarettes(cigarettes: Cig): House? {
        for (house in houses)
            if (house.cigarettes == cigarettes)
                return house
        return null
    }

    /** get index by attribute */
    fun getIndexByColour(colour: Col): Int? {
        for (i in houses.indices)
            if (houses[i].colour == colour)
                return i
        return null
    }

    fun getIndexByCigarettes(cigarettes: Cig): Int? {
        for (i in houses.indices)
            if (houses[i].cigarettes == cigarettes)
                return i
        return null
    }

    fun getIndexByPet(pet: Pet): Int? {
        for (i in houses.indices)
            if (houses[i].pet == pet)
                return i
        return null
    }

    fun getIndexByNationality(nationality: Nat): Int? {
        for (i in houses.indices)
            if (houses[i].nationality == nationality)
                return i
        return null
    }

    /** verify puzzle conditions */
    fun verifyPuzzle(): Boolean {
        if (getByNationality(Nat.English)?.colour != Col.Red)
            return false
        if (getByNationality(Nat.Spanish)?.pet != Pet.Dog)
            return false
        if (getByDrink(Drnk.Coffee)?.colour != Col.Green)
            return false
        if (getByNationality(Nat.Ukranian)?.drink != Drnk.Tea)
            return false
        if (getIndexByColour(Col.Green)!! - getIndexByColour(Col.Ivory)!! != 1)
            return false
        if (getByCigarettes(Cig.OldGold)?.pet != Pet.Snails)
            return false
        if (getByCigarettes(Cig.Kools)?.colour != Col.Yellow)
            return false
        if (houses[2].drink != Drnk.Milk)
            return false
        if (houses[0].nationality != Nat.Norwegian)
            return false
        if (getIndexByCigarettes(Cig.Chesterfields)!! - getIndexByPet(Pet.Fox)!! != 1)
            return false
        if (getIndexByCigarettes(Cig.Kools)!! - getIndexByPet(Pet.Horse)!! != 1)
            return false
        if (getByCigarettes(Cig.LuckyStrike)?.drink != Drnk.OrangeJuice)
            return false
        if (getByNationality(Nat.Japanese)?.cigarettes != Cig.Parliaments)
            return false
        if (getIndexByNationality(Nat.Norwegian)!! - getIndexByColour(Col.Blue)!! != 1)
            return false
        return true
    }
}
