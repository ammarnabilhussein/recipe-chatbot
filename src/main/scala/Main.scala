import scala.io.StdIn

object Main extends App {


  def start(input : String,memory : ConversationState): Unit = input match {
    case input if (input == "quit") => ()
    case _ => {

      val response = if(conversationMemory.detectRepeatedQuery(input,memory.history)) {
        println("You asked about this before! Here's what I said...")
        val getHistorySize = conversationMemory.getConversationHistory(memory).size
        println(memory.history(getHistorySize - 1).botResponse)
        "you was asking again about " + memory.history(getHistorySize - 1).userInput

      } else{
        coreChatBot.handleUserInput(input,memory)

      }

      val mood = conversationMemory.getUserMood(memory.history)
      val tone = mood match {
        case "positive" => "Great to see you're in a good mood! "
        case "negative" => "I hope I can cheer you up! "
        case _ => ""
      }

      println(tone + response)

      val updatedPrefs : Map[String,String] = input.toLowerCase() match {
      case input if input.contains("vegan") => memory.preferences + ("diet" -> "vegan")
      case input if input.contains("vegetarian") => memory.preferences + ("diet" -> "vegetarian")
      case input if input.contains("gluten-free") => memory.preferences + ("diet" -> "gluten-free")
      case input if input.contains("dairy-free") => memory.preferences + ("diet" -> "dairy-free")
      case input if input.contains("high-protein") => memory.preferences + ("diet" -> "high-protein")
      case input if input.contains("low-carb") => memory.preferences + ("diet" -> "low-carb")
      case _ => memory.preferences
}

      val newPreference = ConversationState(memory.history,updatedPrefs)

      val nextInput = StdIn.readLine()
      start(nextInput,conversationMemory.logInteraction(input,response,memory))
    }

  }

  println("Hello")
  val memo = ConversationState(List(),Map.empty[String,String])
  val first = StdIn.readLine()
  start(first,memo)

  
}