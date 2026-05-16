
object coreChatBot{
    def greetUser(): String = {
        "Hello!, I am a nutrition advisor. I can recommend recipes based on cuisine, time, and dietary restrictions. What would you like to eat today?"

    }

    def handleUserInput(input: String,memory : ConversationState): String = {
        input.toLowerCase match {
            
            case x if (x == "hello") || (x == "hi") || x.startsWith("hello") || x.startsWith("hi") => greetUser() // case to greet the user

            case x if x.contains("protein") || x.contains("carbs") || x.contains("fat") || x.contains("calories") => generateResponse(x) // case for topic queries

            case x if x.contains("recipe") || x.contains("meal") || x.contains("dish") || x.contains("food") || x.contains("eat") ||
            x.contains("suggest something") || x.contains("what do you recommend") || x.contains("what do you recommend") =>  
                val prefs = RecommendationEngine.getUserPreferences(memory)
                val answer = RecommendationEngine.recommend(prefs,data.allRecipes)
                if (answer.isEmpty) "I couldn't find recipes matching your preferences. Try updating them!" // should ask about diatry preferences instead and call updatePreferences
                else answer.map(RecommendationEngine.explainRecommendation).mkString("\n")
                
            case x if x.contains("vegan") || x.contains("prefer") || x.contains("vegetarian") ||
             x.contains("gluten-free") || x.contains("dairy-free") || x.contains("nut-free") || x.contains("high-ptotein") || x.contains("low-carb")
             => RecommendationEngine.updatePreferences(x,memory) //case for preference updates

            case x if x.contains("summarize") || x.contains("summary") || x.contains("what have we talked") => conversationMemory.summarizeConversation(memory.history)

            case x if x.contains("topics") || x.contains("what topics") => val topics = conversationMemory.extractTopics(memory.history, List())
            "We've discussed: " + topics.mkString(", ") // extractTopics returns full output, "We've discussed: " is reduntant

            case x if x.contains("most discussed") || x.contains("popular") => {
                val topics = conversationMemory.getMostDiscussedTopics(memory.history)
                topics.map(t => s"${t._1}: ${t._2} times").mkString("\n")
            }

            // should check for the number of recent interactions (n) asked and pass it instead of passing 3 (significant)
            case x if x.contains("recent") || x.contains("last") => {val last = conversationMemory.getLastNInteractions(3, memory).map(e => s"You: ${e.userInput}\nMe: ${e.botResponse}")
            last.mkString("\n---\n")
            }
            
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