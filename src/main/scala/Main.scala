import scala.io.StdIn

object Main extends App {

  val memo = ConversationState(List(),Map.empty[String,String])
  println("Hello ")
  val first = StdIn.readLine()

  def start(input : String,memory : ConversationState): Unit = input match {
    case input if (input == "quit") => ()
    case _ => {

      val response = if(conversationMemory.detectRepeatedQuery(input,memory.history)) {
        println("You asked about this before! Here's what I said...")
        val getHistorySize = conversationMemory.getConversationHistory(memory).size
        println(memory.history(getHistorySize - 1).botResponse)
        "you was asking again about " + memory.history(getHistorySize - 1).userInput

      } else{
        coreChatBot.handleUserInput(input)

      }

      val mood = conversationMemory.getUserMood(memory.history)
      val tone = mood match {
        case "positive" => "Great to see you're in a good mood! "
        case "negative" => "I hope I can cheer you up! "
        case _ => ""
      }

      println(tone + response)

      val updatedPrefs : Map[String,String] = input.toLowerCase() match {
        case input if input.contains("vegan") => RecommendationEngine.updatePreferences(memory.preferences, "diet", "vegan")
        case input if input.contains("vegetarian") => RecommendationEngine.updatePreferences(memory.preferences, "diet", "vegetarian")
        case input if input.contains("gluten-free") => RecommendationEngine.updatePreferences(memory.preferences, "diet", "gluten-free")
        case input if input.contains("dairy-free") => RecommendationEngine.updatePreferences(memory.preferences, "diet", "dairy-free")
        case input if input.contains("nut-free") => RecommendationEngine.updatePreferences(memory.preferences, "diet", "nut-free")
        case input if input.contains("high-protein") => RecommendationEngine.updatePreferences(memory.preferences, "diet", "high-protein")
        case input if input.contains("low-carb") => RecommendationEngine.updatePreferences(memory.preferences, "diet", "low-carb")

      }

      val nextInput = StdIn.readLine()
      start(nextInput,conversationMemory.logInteraction(input,response,memory))
    }

  }

  start(first,memo)

  
}