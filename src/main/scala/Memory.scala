
def logInteraction(userInput : String, botResponse : String, context : ConversationState) : ConversationState = {
    val sequenceNumber = context.history.size + 1
    val timestamp = java.time.LocalDateTime.now().toString
    val intention = userInput match  {
        case userInput if (userInput.contains("hi") || userInput.contains("hello") || userInput.contains("hi")) || userInput.contains("good") => "greeting"
        case userInput if (userInput.contains("suggest something") || userInput.contains("what do you recommend") || userInput.contains("what do you recommend")) => "recommendation"
        case _ => "general"
    }
    
    val newExchange = InteractionEntry(sequenceNumber,timestamp,userInput,botResponse,intention)
    ConversationState(context.history :+ newExchange, context.preferences)
}

def getConversationHistory(context : ConversationState) : List[InteractionEntry] = {
    context.history
}

def getLastNInteractions(n : Int, context : ConversationState) : List[InteractionEntry] = {
    context.history.filter(_.sequenceNumber > (context.history.size - n))
}

def detectRepeatedQuery(input : String, history : List[InteractionEntry]): Boolean = {
    val splitWords = input.split(" ")
    history.filter(x => splitWords.filter(y => x.userInput.contains(y)).nonEmpty).nonEmpty
}

def extractTopics(history : List[InteractionEntry],acc : List[String]): List[String] = history match{
    case Nil => acc
    case head :: tail if (!acc.contains(head.intent)) => extractTopics(tail,acc :+ head.intent)
    case _ :: tail => extractTopics(tail, acc)
}

def summarizeConversation(history : List[InteractionEntry]): String = {
    val recommendationCounts = history.filter(_.intent == "recommendation").size
    val greetingCounts = history.filter(_.intent == "greeting").size
    val summary = "We've had " + history.size.toBinaryString + " exchanges."

    val summaryPart1 = recommendationCounts match
        case recommendationCounts if (recommendationCounts > 0) => "You asked for " + recommendationCounts.toBinaryString + " recommendations. "
        case _ => summary

    val summaryPart2 = greetingCounts match
        case greetingCounts if (greetingCounts > 0) => "We exchanged " + greetingCounts.toBinaryString + " greetings. "
        case _ => summary
    
    val finalSummary = summary + summaryPart1 + summaryPart2
    finalSummary
}



def getMostDiscussedTopics(history : List[InteractionEntry]) : List[(String,Int)] = {
    history.groupBy(x => x.intent).mapValues(_.size).toList.sortBy(x => (x._2,x._1)).reverse
}

def getUserMood(history : List[InteractionEntry]) : String = {
    val positive = List("great", "love", "amazing", "good", "nice")
    val negative = List("boring", "bad", "hate", "don't like", "awful")
    val pair = history.foldLeft((0,0))((acc,x) => (
        if (positive.filter(y => x.userInput.contains(y)).nonEmpty) (acc._1 + 1) else (acc._1),
        if(negative.filter(y => x.userInput.contains(y)).nonEmpty) (acc._2 + 1) else (acc._2)
    )
        )
    val mood = pair match {
        case (a,b) if (a > b) => "positive"
        case (a,b) if (a < b) => "negative"
        case _ => "neutral"
    }
    mood
}