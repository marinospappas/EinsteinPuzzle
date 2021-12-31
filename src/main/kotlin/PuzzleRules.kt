/**
 * the puzzle rules
 * the 3 "next to" or "to the right of" rules have two variants each,
 * so they create 8 combinations or 8 different rule lists of 14 rules (plus 2 which form the puzzle answer)
 */
val rulesList0 = arrayOf(Rule0(), Rule1(), Rule2(), Rule3(), Rule4(),
    Rule5(), Rule6(), Rule7(), Rule8(), Rule9(),
    Rule10(), Rule11(), Rule12(), Rule13(), Rule14(), Rule15(), Rule16())
val rulesList1 = arrayOf(Rule0(), Rule1(), Rule2(), Rule3(), Rule4Alt(),
    Rule5(), Rule6(), Rule7(), Rule8(), Rule9(),
    Rule10(), Rule11(), Rule12(), Rule13(), Rule14(), Rule15(), Rule16())
val rulesList2 = arrayOf(Rule0(), Rule1(), Rule2(), Rule3(), Rule4(),
    Rule5(), Rule6(), Rule7(), Rule8(), Rule9(),
    Rule10(), Rule11Alt(), Rule12(), Rule13(), Rule14(), Rule15(), Rule16())
val rulesList3 = arrayOf(Rule0(), Rule1(), Rule2(), Rule3(), Rule4Alt(),
    Rule5(), Rule6(), Rule7(), Rule8(), Rule9(),
    Rule10(), Rule11Alt(), Rule12(), Rule13(), Rule14(), Rule15(), Rule16())
val rulesList4 = arrayOf(Rule0(), Rule1(), Rule2(), Rule3(), Rule4(),
    Rule5(), Rule6(), Rule7(), Rule8(), Rule9(),
    Rule10(), Rule11(), Rule12Alt(), Rule13(), Rule14(), Rule15(), Rule16())
val rulesList5 = arrayOf(Rule0(), Rule1(), Rule2(), Rule3(), Rule4Alt(),
    Rule5(), Rule6(), Rule7(), Rule8(), Rule9(),
    Rule10(), Rule11(), Rule12Alt(), Rule13(), Rule14(), Rule15(), Rule16())
val rulesList6 = arrayOf(Rule0(), Rule1(), Rule2(), Rule3(), Rule4(),
    Rule5(), Rule6(), Rule7(), Rule8(), Rule9(),
    Rule10(), Rule11Alt(), Rule12Alt(), Rule13(), Rule14(), Rule15(), Rule16())
val rulesList7 = arrayOf(Rule0(), Rule1(), Rule2(), Rule3(), Rule4Alt(),
    Rule5(), Rule6(), Rule7(), Rule8(), Rule9(),
    Rule10(), Rule11Alt(), Rule12Alt(), Rule13(), Rule14(), Rule15(), Rule16())

/** the puzzle rules */
interface Rule {
    fun rule(houseRow: Array<House>, indx: Int): Boolean
}

class Rule0: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean = true
}

class Rule1: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (houseRow[indx].nationality != Nat.none
            && houseRow[indx].nationality != Nat.Norwegian)
            return false
        houseRow[indx].nationality = Nat.Norwegian
        return true
    }
}

class Rule2: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (indx >= 4)
            return false
        if (houseRow[indx].nationality != Nat.none
            && houseRow[indx].nationality != Nat.Norwegian)
            return false
        if (houseRow[indx+1].colour != Col.none
            && houseRow[indx+1].colour != Col.Blue)
            return false
        houseRow[indx].nationality = Nat.Norwegian
        houseRow[indx+1].colour = Col.Blue
        return true
    }
}

class Rule3: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (houseRow[indx].drink != Drnk.none
            && houseRow[indx].drink != Drnk.Milk)
            return false
        houseRow[indx].drink = Drnk.Milk
        return true
    }
}

class Rule4: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (indx >= 4)
            return false
        if (houseRow[indx].colour != Col.none
            && houseRow[indx].colour != Col.Green)
            return false
        if (houseRow[indx+1].colour != Col.none
            && houseRow[indx+1].colour != Col.Ivory)
            return false
        for (i in 0..4)
            if(i != indx && houseRow[i].colour == Col.Green)
                return false
        for (i in 0..4)
            if(i != indx+1 && houseRow[i].colour == Col.Ivory)
                return false
        houseRow[indx].colour = Col.Green
        houseRow[indx+1].colour = Col.Ivory
        return true
    }
}

class Rule4Alt: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (indx >= 4)
            return false
        if (houseRow[indx].colour != Col.none
            && houseRow[indx].colour != Col.Ivory)
            return false
        if (houseRow[indx+1].colour != Col.none
            && houseRow[indx+1].colour != Col.Green)
            return false
        for (i in 0..4)
            if(i != indx && houseRow[i].colour == Col.Ivory)
                return false
        for (i in 0..4)
            if(i != indx+1 && houseRow[i].colour == Col.Green)
                return false
        houseRow[indx].colour = Col.Ivory
        houseRow[indx+1].colour = Col.Green
        return true
    }
}

class Rule5: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (houseRow[indx].nationality != Nat.none
            && houseRow[indx].nationality != Nat.English)
            return false
        if (houseRow[indx].colour != Col.none
            && houseRow[indx].colour != Col.Red)
            return false
        for (i in 0..4)
            if(i != indx && houseRow[i].nationality == Nat.English)
                return false
        for (i in 0..4)
            if(i != indx && houseRow[i].colour == Col.Red)
                return false
        houseRow[indx].nationality = Nat.English
        houseRow[indx].colour = Col.Red
        return true
    }
}

class Rule6: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (houseRow[indx].nationality != Nat.none
            && houseRow[indx].nationality != Nat.Spanish)
            return false
        if (houseRow[indx].pet != Pet.none
            && houseRow[indx].pet != Pet.Dog)
            return false
        for (i in 0..4)
            if(i != indx && houseRow[i].nationality == Nat.Spanish)
                return false
        for (i in 0..4)
            if(i != indx && houseRow[i].pet == Pet.Dog)
                return false
        houseRow[indx].nationality = Nat.Spanish
        houseRow[indx].pet = Pet.Dog
        return true
    }
}

class Rule7: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (houseRow[indx].drink != Drnk.none
            && houseRow[indx].drink != Drnk.Coffee)
            return false
        if (houseRow[indx].colour != Col.none
            && houseRow[indx].colour != Col.Green)
            return false
        for (i in 0..4)
            if(i != indx && houseRow[i].drink == Drnk.Coffee)
                return false
        for (i in 0..4)
            if(i != indx && houseRow[i].colour == Col.Green)
                return false
        houseRow[indx].drink = Drnk.Coffee
        houseRow[indx].colour = Col.Green
        return true
    }
}

class Rule8: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (houseRow[indx].nationality != Nat.none
            && houseRow[indx].nationality != Nat.Ukrainian)
            return false
        if (houseRow[indx].drink != Drnk.none
            && houseRow[indx].drink != Drnk.Tea)
            return false
        for (i in 0..4)
            if(i != indx && houseRow[i].nationality == Nat.Ukrainian)
                return false
        for (i in 0..4)
            if(i != indx && houseRow[i].drink == Drnk.Tea)
                return false
        houseRow[indx].nationality = Nat.Ukrainian
        houseRow[indx].drink = Drnk.Tea
        return true
    }
}

class Rule9: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (houseRow[indx].cigarettes != Cig.none
            && houseRow[indx].cigarettes != Cig.OldGold)
            return false
        if (houseRow[indx].pet != Pet.none
            && houseRow[indx].pet != Pet.Snails)
            return false
        for (i in 0..4)
            if(i != indx && houseRow[i].cigarettes == Cig.OldGold)
                return false
        for (i in 0..4)
            if(i != indx && houseRow[i].pet == Pet.Snails)
                return false
        houseRow[indx].cigarettes = Cig.OldGold
        houseRow[indx].pet = Pet.Snails
        return true
    }
}

class Rule10: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (houseRow[indx].cigarettes != Cig.none
            && houseRow[indx].cigarettes != Cig.Kools)
            return false
        if (houseRow[indx].colour != Col.none
            && houseRow[indx].colour != Col.Yellow)
            return false
        for (i in 0..4)
            if(i != indx && houseRow[i].cigarettes == Cig.Kools)
                return false
        for (i in 0..4)
            if(i != indx && houseRow[i].colour == Col.Yellow)
                return false
        houseRow[indx].cigarettes = Cig.Kools
        houseRow[indx].colour = Col.Yellow
        return true
    }
}

class Rule11: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (indx >= 4)
            return false
        if (houseRow[indx].cigarettes != Cig.none
            && houseRow[indx].cigarettes != Cig.Chesterfields)
            return false
        if (houseRow[indx+1].pet != Pet.none
            && houseRow[indx+1].pet != Pet.Fox)
            return false
        for (i in 0..4)
            if(i != indx && houseRow[i].cigarettes == Cig.Chesterfields)
                return false
        for (i in 0..4)
            if(i != indx+1 && houseRow[i].pet == Pet.Fox)
                return false
        houseRow[indx].cigarettes = Cig.Chesterfields
        houseRow[indx+1].pet = Pet.Fox
        return true
    }
}

class Rule11Alt: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (indx >= 4)
            return false
        if (houseRow[indx+1].cigarettes != Cig.none
            && houseRow[indx+1].cigarettes != Cig.Chesterfields)
            return false
        if (houseRow[indx].pet != Pet.none
            && houseRow[indx].pet != Pet.Fox)
            return false
        for (i in 0..4)
            if(i != indx+1 && houseRow[i].cigarettes == Cig.Chesterfields)
                return false
        for (i in 0..4)
            if(i != indx && houseRow[i].pet == Pet.Fox)
                return false
        houseRow[indx+1].cigarettes = Cig.Chesterfields
        houseRow[indx].pet = Pet.Fox
        return true
    }
}

class Rule12: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (indx >= 4)
            return false
        if (houseRow[indx].cigarettes != Cig.none
            && houseRow[indx].cigarettes != Cig.Kools)
            return false
        if (houseRow[indx+1].pet != Pet.none
            && houseRow[indx+1].pet != Pet.Horse)
            return false
        for (i in 0..4)
            if(i != indx && houseRow[i].cigarettes == Cig.Kools)
                return false
        for (i in 0..4)
            if(i != indx+1 && houseRow[i].pet == Pet.Horse)
                return false
        houseRow[indx].cigarettes = Cig.Kools
        houseRow[indx+1].pet = Pet.Horse
        return true
    }
}

class Rule12Alt: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (indx >= 4)
            return false
        if (houseRow[indx+1].cigarettes != Cig.none
            && houseRow[indx+1].cigarettes != Cig.Kools)
            return false
        if (houseRow[indx].pet != Pet.none
            && houseRow[indx].pet != Pet.Horse)
            return false
        for (i in 0..4)
            if(i != indx+1 && houseRow[i].cigarettes == Cig.Kools)
                return false
        for (i in 0..4)
            if(i != indx && houseRow[i].pet == Pet.Horse)
                return false
        houseRow[indx+1].cigarettes = Cig.Kools
        houseRow[indx].pet = Pet.Horse
        return true
    }
}

class Rule13: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (houseRow[indx].cigarettes != Cig.none
            && houseRow[indx].cigarettes != Cig.LuckyStrike)
            return false
        if (houseRow[indx].drink != Drnk.none
            && houseRow[indx].drink != Drnk.OJ)
            return false
        for (i in 0..4)
            if(i != indx && houseRow[i].cigarettes == Cig.LuckyStrike)
                return false
        for (i in 0..4)
            if(i != indx && houseRow[i].drink == Drnk.OJ)
                return false
        houseRow[indx].cigarettes = Cig.LuckyStrike
        houseRow[indx].drink = Drnk.OJ
        return true
    }
}

class Rule14: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (houseRow[indx].nationality != Nat.none
            && houseRow[indx].nationality != Nat.Japanese)
            return false
        if (houseRow[indx].cigarettes != Cig.none
            && houseRow[indx].cigarettes != Cig.Parliaments)
            return false
        for (i in 0..4)
            if(i != indx && houseRow[i].nationality == Nat.Japanese)
                return false
        for (i in 0..4)
            if(i != indx && houseRow[i].cigarettes == Cig.Parliaments)
                return false
        houseRow[indx].nationality = Nat.Japanese
        houseRow[indx].cigarettes = Cig.Parliaments
        return true
    }
}

class Rule15: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (houseRow[indx].drink != Drnk.none
            && houseRow[indx].drink != Drnk.Water)
            return false
        for (i in 0..4)
            if(i != indx && houseRow[i].drink == Drnk.Water)
                return false
        houseRow[indx].drink = Drnk.Water
        return true
    }
}

class Rule16: Rule {
    override fun rule(houseRow: Array<House>, indx: Int): Boolean {
        if (houseRow[indx].pet != Pet.none
            && houseRow[indx].pet != Pet.Zebra)
            return false
        for (i in 0..4)
            if(i != indx && houseRow[i].pet == Pet.Zebra)
                return false
        houseRow[indx].pet = Pet.Zebra
        return true
    }
}
