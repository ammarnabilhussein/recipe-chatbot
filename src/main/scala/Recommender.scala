
object RecommendationEngine {  

  def getUserPreferences(state: ConversationState): Map[String, String] = {

    state.preferences
  }

  def updatePreferences(input: String, memory: ConversationState): String = {
    val key = "diet"
    val value = 
        if (input.contains("vegan")) "vegan"
        else if (input.contains("vegetarian")) "vegetarian"
        else if (input.contains("gluten-free")) "gluten-free"
        else if (input.contains("dairy-free")) "dairy-free"
        else if (input.contains("high-protein")) "high-protein"
        else if (input.contains("low-carb")) "low-carb"
        else "none"
    s"Got it! I'll remember you prefer $value food."
  }

  def recommend(preferences: Map[String, String], recipes: List[Recipe]): List[Recipe] = {

    recipes.filter(recipe =>preferences.get("cuisine").forall(prefCuisine => recipe.cuisine.equalsIgnoreCase(prefCuisine)
          )

        &&

        preferences.get("difficulty")
          .forall(prefDifficulty =>
            recipe.difficulty.equalsIgnoreCase(prefDifficulty)
          )

        &&

        preferences.get("diet")
          .forall(prefDiet =>
            recipe.dietaryTags.exists(tag =>
              tag.equalsIgnoreCase(prefDiet)
            )
          )
      )

      
      .sortBy(_.prepTime)
  }
  

  def explainRecommendation(recipe: Recipe): String = {

    s"${recipe.name} is a great choice because it's ingredients are : " + 
    s"${recipe.ingredients}"+
    s"and dietary Tags: ${recipe.dietaryTags}"
  }

}