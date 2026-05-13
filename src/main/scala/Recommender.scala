
object RecommendationEngine {  

  def getUserPreferences(
      state: ConversationState
  ): Map[String, String] = {

    state.preferences 
  }
  

  def updatePreferences(preferences: Map[String, String],key: String,value: String): Map[String, String] = {
    preferences + (key -> value)

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

    s"${recipe.name} is a great choice because it is " +
    s"${recipe.difficulty.toLowerCase} to make " +
    s"and only takes ${recipe.prepTime} minutes."
  }


  def getTopRecommendations(
      preferences: Map[String, String]
  ): List[Recipe] = {

    recommend(preferences, data.allRecipes)
      .take(3)
  }


 
  def formatRecommendations(
      recipes: List[Recipe]
  ): String = {

    if (recipes.isEmpty) {

      "Sorry, I couldn't find matching recipes."

    } else {

      recipes
        .map(recipe =>
          explainRecommendation(recipe)
        )
        .mkString("\n")
    }
  }

}