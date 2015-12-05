import groovy.json.JsonSlurper
import java.util.Random
def rand = new Random()

def file = new File('knowledgebases.json')
def KBs = new JsonSlurper().parse(file)

KBs.eachWithIndex { kb, i ->
  def toEntail = kb.grammar[rand.nextInt(kb.grammar.size())]
  println Forwards.forwardChain(kb, toEntail)
  println Backwards.backwardsChain(kb, [toEntail])
}
