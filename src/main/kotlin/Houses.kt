/** House class and attributes */

const val NUM_HOUSES = 5

enum class Nat {English, Spanish, Norwegian, Japanese, Ukrainian}
enum class Col {Red, Green, Ivory, Yellow, Blue}
enum class Pet {Dog, Snails, Fox, Horse, Zebra}
enum class Drnk {Coffee, Tea, Milk, OJ, Water}
enum class Cig {Kools, Chesterfields, Parliaments, LuckyStrike, OldGold}

/** the house object */
class House(var nationality: Nat? = null, var colour: Col? = null, var pet: Pet? = null,
            var drink: Drnk? = null, var cigarettes: Cig? = null) {

    /** set an attribute to obj (can be any of nationality, colour, etc.) */
    fun setAttribute(obj: Any) {
        when (obj) {
            is Nat -> nationality = obj
            is Col -> colour = obj
            is Pet -> pet = obj
            is Drnk -> drink = obj
            is Cig -> cigarettes = obj
        }
    }

    /** get an attribute - obj gives us the type */
    fun getAttribute(obj: Any): Any? {
        return when (obj) {
            is Nat -> nationality
            is Col -> colour
            is Pet -> pet
            is Drnk -> drink
            is Cig -> cigarettes
            else -> null
        }
    }

    /** check if the house has the attribute obj */
    fun hasAttribute(obj: Any?): Boolean {
        return obj == nationality || obj == colour ||
                obj == pet || obj == drink || obj == cigarettes
    }

    /** print house */
    fun print() {
        print("${nationality}\t")
        if (nationality.toString().length < 8) print("\t")
        print("${colour}\t")
        if (colour.toString().length < 4) print("\t")
        print("${pet}\t")
        if (pet.toString().length < 4) print("\t")
        print("${drink}\t")
        if (drink.toString().length < 4) print("\t")
        print("$cigarettes")
        println()
    }
}

/** the 5 houses */
class HousesList(var theHouses: Array<House>) {

    /** get the position of the house in the list that has attribute obj */
    fun getAttrPos(obj: Any): Int {
        for (i in theHouses.indices)
            if (theHouses[i].hasAttribute(obj))
                return i
        return -1
    }

    /** apply one constraint (one part of a multi-house rule only) */
    private fun applyOneConstraint(constraint: House, position: Int): Boolean {
        if (position < 0 || position > theHouses.size-1)
            return false
        val attributes = arrayListOf<Any?>(constraint.nationality, constraint.colour, constraint.pet,
                                            constraint.drink, constraint.cigarettes)
        for (obj in attributes) {
            if (obj == null)
                continue
            val attrPos = getAttrPos(obj)
            if (attrPos >=0 && attrPos != position) // if this attribute exists but in a different position
                return false                        // we can't apply this constraint
            val attrVal = theHouses[position].getAttribute(obj)
            if (attrVal != null && attrVal != obj)  // if a different value of this attribute exists in this position
                return false                        // we can't apply this constraint
            theHouses[position].setAttribute(obj)
        }
        return true
    }

    /** apply a constraint in a certain position in the house list */
    fun applyConstraint(constraint: Constraint, position: Int): Boolean {
        if (constraint.position >= 0 && constraint.position != position)
            return false
        for (i in constraint.rules.indices) {
            if (!applyOneConstraint(constraint.rules[i], position+i))
                return false
        }
        return true
    }

    /** clone houses */
    fun clone(): HousesList {
        val h = Array(NUM_HOUSES) { House() }
        for (i in theHouses.indices) {
            h[i] = House(theHouses[i].nationality, theHouses[i].colour, theHouses[i].pet, theHouses[i].drink, theHouses[i].cigarettes)
        }
        return HousesList(h)
    }

    /** print houses */
    fun print() {
        println("-------------------------------------------------")
        for (h in theHouses)
            h.print()
    }
}
