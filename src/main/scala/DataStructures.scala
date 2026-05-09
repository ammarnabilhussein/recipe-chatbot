case class Recipe(
    name : String,
    cuisine : String,
    difficulty : String,
    ingredients : List[String],
    dietaryTages : List[String],
    prepTime : Int

    )

case class InteractionEntry(
    sequenceNumber: Int,
    timestamp: String,
    userInput: String,
    botResponse: String,
    intent: String

)

case class ConversationState(
  history: List[InteractionEntry],
  preferences: Map[String, String]

)

val allRecipes: List[Recipe] = List(
  Recipe("Spaghetti Aglio e Olio", "Italian", "Easy", List("Spaghetti", "Garlic", "Extra Virgin Olive Oil", "Red Pepper Flakes", "Fresh Parsley", "Parmesan Cheese"), List("Vegetarian"), 15),
  Recipe("Chicken Tikka Masala", "Indian", "Medium", List("Chicken Breast", "Yogurt", "Garam Masala", "Turmeric", "Cumin", "Tomato Puree", "Heavy Cream", "Ginger", "Garlic"), List("High-Protein", "Gluten-Free"), 45),
  Recipe("Vegan Buddha Bowl", "Global", "Easy", List("Quinoa", "Chickpeas", "Spinach", "Sweet Potato", "Avocado", "Tahini", "Lemon Juice"), List("Vegan", "Gluten-Free", "Healthy"), 25),
  Recipe("Classic Beef Tacos", "Mexican", "Easy", List("Ground Beef", "Taco Shells", "Lettuce", "Tomato", "Cheddar Cheese", "Cumin", "Chili Powder", "Sour Cream"), List("High-Protein"), 20),
  Recipe("Miso Soup", "Japanese", "Easy", List("Dashi Stock", "Miso Paste", "Tofu", "Green Onions", "Wakame Seaweed"), List("Vegetarian", "Low-Calorie", "Gluten-Free"), 10),
  Recipe("Caprese Salad", "Italian", "Easy", List("Fresh Mozzarella", "Tomatoes", "Fresh Basil", "Balsamic Glaze", "Extra Virgin Olive Oil", "Salt"), List("Vegetarian", "Gluten-Free", "Keto"), 10),
  Recipe("Red Lentil Dal", "Indian", "Easy", List("Red Lentils", "Coconut Milk", "Onion", "Turmeric", "Cumin Seeds", "Garlic", "Coriander"), List("Vegan", "Gluten-Free"), 30),
  Recipe("Greek Salad", "Greek", "Easy", List("Cucumber", "Cherry Tomatoes", "Kalamata Olives", "Feta Cheese", "Red Onion", "Dried Oregano", "Olive Oil"), List("Vegetarian", "Gluten-Free", "Low-Carb"), 15),
  Recipe("Shakshuka", "Middle Eastern", "Medium", List("Eggs", "Canned Tomatoes", "Bell Peppers", "Onion", "Garlic", "Paprika", "Cumin", "Feta Cheese"), List("Vegetarian", "High-Protein"), 25),
  Recipe("Shrimp Pad Thai", "Thai", "Hard", List("Rice Noodles", "Shrimp", "Bean Sprouts", "Peanuts", "Eggs", "Tamarind Paste", "Fish Sauce", "Lime"), List("Dairy-Free", "High-Protein"), 35),
  Recipe("Baked Falafel", "Middle Eastern", "Medium", List("Canned Chickpeas", "Fresh Parsley", "Fresh Cilantro", "Garlic", "Cumin", "Coriander", "Flour"), List("Vegan", "Vegetarian"), 40),
  Recipe("Classic Ratatouille", "French", "Medium", List("Eggplant", "Zucchini", "Bell Peppers", "Tomatoes", "Onion", "Garlic", "Thyme", "Olive Oil"), List("Vegan", "Vegetarian", "Gluten-Free"), 60),
  Recipe("Ginger Beef Stir-fry", "Chinese", "Easy", List("Flank Steak", "Broccoli", "Ginger", "Soy Sauce", "Sesame Oil", "Garlic", "Cornstarch"), List("High-Protein", "Dairy-Free"), 20),
  Recipe("Buttermilk Pancakes", "American", "Easy", List("Flour", "Buttermilk", "Egg", "Butter", "Baking Powder", "Sugar", "Maple Syrup"), List("Vegetarian"), 20),
  Recipe("Guacamole", "Mexican", "Easy", List("Avocados", "Lime Juice", "Cilantro", "Red Onion", "Jalapeño", "Salt"), List("Vegan", "Gluten-Free", "Keto"), 10),
  Recipe("Mediterranean Quinoa Salad", "Mediterranean", "Easy", List("Quinoa", "Cucumber", "Chickpeas", "Parsley", "Lemon Vinaigrette", "Cherry Tomatoes"), List("Vegan", "Gluten-Free", "Healthy"), 20),
  Recipe("Mushroom Risotto", "Italian", "Hard", List("Arborio Rice", "Mushrooms", "Vegetable Broth", "White Wine", "Shallots", "Parmesan Cheese", "Butter"), List("Vegetarian", "Gluten-Free"), 45),
  Recipe("Classic Hummus", "Middle Eastern", "Easy", List("Chickpeas", "Tahini", "Garlic", "Lemon Juice", "Olive Oil", "Cumin"), List("Vegan", "Gluten-Free"), 15),
  Recipe("Eggs Benedict", "American", "Hard", List("English Muffin", "Poached Eggs", "Canadian Bacon", "Butter", "Egg Yolks", "Lemon Juice"), List("High-Protein"), 30),
  Recipe("French Onion Soup", "French", "Medium", List("Yellow Onions", "Beef Broth", "Gruyère Cheese", "Baguette", "Thyme", "Butter", "Cognac"), List("Comfort Food"), 90)
)