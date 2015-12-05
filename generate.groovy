// Here we generate a nice grammar
@Grab(group='org.apache.commons', module='commons-lang3', version='3.4')

import org.apache.commons.lang3.RandomStringUtils
import java.util.Random
import groovy.json.JsonBuilder

def kbsToGenerate = args[0].toInteger()
def wordsPerKB = args[1].toInteger()
def maxWordsPerClause = args[2].toInteger()
def clausesPerKB = args[3].toInteger()
def kbs = []
def rand = new Random()

def charset = (('a'..'z') + ('A'..'Z') + ('0'..'9')).join()

(1..kbsToGenerate).each {
  def grammar = []
  (1..wordsPerKB).each {
    grammar << RandomStringUtils.random(5, charset.toCharArray())
  }

  // Add some simple ones
  def newKB = [ 'grammar': grammar, clauses: [] ]
  (1..clausesPerKB).each {
    def clause = []
    def cAmount = rand.nextInt(maxWordsPerClause+1) 
    def clauseGrammar = grammar.clone()
    (0..cAmount).each {
      def newWordIndex = rand.nextInt(clauseGrammar.size())
      clause << clauseGrammar[newWordIndex]
      clauseGrammar.remove(newWordIndex)
    }
    newKB.clauses << clause
  }

  kbs << newKB
}

new File('knowledgebases.json').write(new JsonBuilder(kbs).toPrettyString())

