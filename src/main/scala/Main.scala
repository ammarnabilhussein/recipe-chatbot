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

      println(response)
      val nextInput = StdIn.readLine()
      start(nextInput,conversationMemory.logInteraction(input,response,memory))
    }

  }

  start(first,memo)

  
}