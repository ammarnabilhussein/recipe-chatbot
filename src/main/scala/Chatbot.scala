
object coreChatBot{
    def greetUser(): String = {
        "Hello!, I am a nutrition advisor. I can recommend recipes based on cuisine, time, and dietary restrictions. What would you like to eat today?"

    }

    def handleUserInput(input: String): String = {
        input.toLowerCase match {
            case x if x.contains("hello") || x.contains("hi") => greetUser() // case to greet the user

            case x if x.contains("protein") || x.contains("carbs") || x.contains("fat") || x.contains("calories") => generateResponse(x) // case for topic queries

            case x if x.contains("recipe") || x.contains("meal") || x.contains("dish") || x.contains("food") || x.contains("eat") =>  
                val recommend = parseInput(x)
                val answer = RecommendationEngine.recommend(recommend,data.allRecipes)
                answer.map((x : Recipe) => RecommendationEngine.explainRecommendation(x)).mkString("\n")
                

            case x if x.contains("vegan") || x.contains("prefer") || x.contains("vegetarian") || x.contains("gluten-free") || x.contains("dairy-free") || x.contains("nut-free") => updatePreferences(x) //case for preference updates

            case _ => "I'm sorry, I didn't understand that. Can you please rephrase your request?" // case for fallbacks
        }
    }

    def parseInput(input: String): List[String] = {
        input.toLowerCase
            .replaceAll("[^a-zA-Z0-9\\s]", "")
            .split("\\s+")
            .toList
    }
    
    def generateResponse(query: String): String = {
        val tokens = parseInput(query)
        tokens match {
            case x if x.contains("protein") => "Protein is essential for building and repairing tissues. It can be found in foods like meat, dairy, legumes, and nuts."
            case x if x.contains("carbs") => "Carbohydrates are the body's main source of energy. They are found in foods like bread, pasta, fruits, and vegetables."
            case x if x.contains("fat") => "Fats are important for energy storage and cell function. Healthy fats can be found in foods like avocados, nuts, seeds, and olive oil."
            case x if x.contains("calories") => "Calories are a measure of energy. The number of calories you need depends on your age, gender, weight, and activity level."
            case _ => "I'm sorry, I don't have information on that topic. Can you please ask about protein, carbs, fat, or calories?"
        }
    }
}