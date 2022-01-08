/**
 * the puzzle rules
 * the 2 "next to" or "to the right of" rules have two variants each,
 * so they create 4 combinations or 4 different rule lists of 14 rules
 */

/**
 * Constraint class
 * consists of a list of House objects with certain defined attributes
 * (e.g. English, Red or Green to the right of the Ivory)
 * and an optional position in case it can be applied only in one specific house
 * (e.g. Milk in the middle house)
 * position = -1 means that the constraint can be applied on any house
 */
class Constraint(var name: String, var comment: String,
                 var rules: List<House>,
                 var position: Int = -1)

// the 14 rules
val rule1 = Constraint(
    "Rule1", "The Englishman lives in the red house",
    listOf(House(nationality = Nat.English, colour = Col.Red))
)
val rule2 = Constraint(
    "Rule2", "The Spaniard owns the dog",
    listOf(House(nationality = Nat.Spanish, pet = Pet.Dog))
)
val rule3 = Constraint(
    "Rule3", "Coffee is drunk in the green house",
    listOf(House(colour = Col.Green, drink = Drnk.Coffee))
)
val rule4 = Constraint(
    "Rule4", "The Ukrainian drinks tea",
    listOf(House(nationality = Nat.Ukrainian, drink = Drnk.Tea))
)
val rule5 = Constraint(
    "Rule5", "The green house is immediately to the right of the ivory house (alternative)",
    listOf(House(colour = Col.Ivory),
        House(colour = Col.Green))
)
val rule6 = Constraint(
    "Rule6", "The Old Gold smoker owns snails",
    listOf(House(pet = Pet.Snails, cigarettes = Cig.OldGold))
)
val rule7 = Constraint(
    "Rule7", "Kools are smoked in the yellow house",
    listOf(House(colour = Col.Yellow, cigarettes = Cig.Kools))
)
val rule8 = Constraint(
    "Rule8", "Milk is drunk in the middle house",
    listOf(House(drink = Drnk.Milk)),
    NUM_HOUSES/2    // middle house
)
val rule9 = Constraint(
    "Rule9", "The Norwegian lives in the first house",
    listOf(House(nationality = Nat.Norwegian)),
    0   // first house
)
val rule10a = Constraint(
    "Rule10a", "The man who smokes Chesterfields lives in the house next to the man with the fox",
    listOf(House(pet = Pet.Fox),
        House(cigarettes = Cig.Chesterfields)),
)
val rule10b = Constraint(
    "Rule10b", "The man who smokes Chesterfields lives in the house next to the man with the fox (alternative)",
    listOf(House(cigarettes = Cig.Chesterfields),
        House(pet = Pet.Fox))
)
val rule11a = Constraint(
    "Rule11a", "Kools are smoked in the house next to the house where the horse is kept",
    listOf(House(pet = Pet.Horse),
        House(cigarettes = Cig.Kools))

)
val rule11b = Constraint(
    "Rule11b", "Kools are smoked in the house next to the house where the horse is kept (alternative)",
    listOf(House(cigarettes = Cig.Kools),
        House(pet = Pet.Horse))

)
val rule12 = Constraint(
    "Rule12", "The Lucky Strike smoker drinks orange juice",
    listOf(House(cigarettes = Cig.LuckyStrike, drink = Drnk.OJ))
)
val rule13 = Constraint(
    "Rule13", "The Japanese smokes Parliaments",
    listOf(House(nationality = Nat.Japanese, cigarettes = Cig.Parliaments))
)
val rule14 = Constraint(
    "Rule14", "The Norwegian lives next to the blue house",
    listOf(House(nationality = Nat.Norwegian),
        House(colour = Col.Blue))
)

// the two questions
val question1 = Constraint(
    "Question1", "Who drinks water?",
    listOf(House(drink = Drnk.Water))
)
val question2 = Constraint(
    "Question2", "Who owns the zebra?",
    listOf(House(pet = Pet.Zebra))
)

// the 4 different rule sets
val rulesList0 = mutableListOf(rule1, rule2, rule3, rule4, rule5, rule6, rule7,
    rule8, rule9, rule10a, rule11a, rule12, rule13, rule14)
val rulesList1 = mutableListOf(rule1, rule2, rule3, rule4, rule5, rule6, rule7,
    rule8, rule9, rule10a, rule11b, rule12, rule13, rule14)
val rulesList2 = mutableListOf(rule1, rule2, rule3, rule4, rule5, rule6, rule7,
    rule8, rule9, rule10b, rule11a, rule12, rule13, rule14)
val rulesList3 = mutableListOf(rule1, rule2, rule3, rule4, rule5, rule6, rule7,
    rule8, rule9, rule10b, rule11b, rule12, rule13, rule14)

// the list of questions
val questionsList = mutableListOf(question1, question2)

/** print rules */
fun printRules(cList: List<Constraint>, long: Boolean = false) {
    if (long) {
        println(">> rules list size: ${cList.size}")
        for (r in cList)
            println("${r.name} - ${r.comment}")
    } else {
        print(">> ${cList.size} rules: ")
        for (r in cList)
            print("${r.name}, ")
        println()
    }
}
