object Reccomendation_Engine{
// 1. Retrieves stored preferences
  def getUserPreferences(): Map[String, String] = {
    // For now, you can return a hardcoded Map to test your logic
    Map("cuisine" -> "Italian", "fitness" -> "High")
  }

  // 2. Immutably updates preferences
  def updatePreferences(current: Map[String, String], key: String, value: String): Map[String, String] = {
    current + (key -> value) 
  }

  // 3. Filters and ranks using higher-order functions
  def recommend(preferences: Map[String, String], data: List[Recipe]): List[Recipe] = {
    data.filter(r => r.cuisine == preferences.getOrElse("cuisine", ""))
        .sortBy(_.prepTime) // Example of using sortBy
  }

  // 4. Generates a human-readable string
  def explainRecommendation(item: Recipe): String = {
    s"This recipe matches your preference for ${item.cuisine}!"
  }




}