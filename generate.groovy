// Here we generate a nice grammar
@Grab(group='org.apache.commons', module='commons-lang3', version='3.4')

import org.apache.commons.lang3.RandomStringUtils
import java.util.Random
import groovy.json.JsonBuilder

def kbsToGenerate = args[0].toInteger()
def maxWordsPerClause = args[1].toInteger()
def kbs = []
def rand = new Random()

def charset = (('a'..'z') + ('A'..'Z') + ('0'..'9')).join()

for(def i=10;i<=1000;i+=10) {
  kbs = []
  (1..kbsToGenerate).each {
    def grammar = []
    (1..i).each {
      grammar << RandomStringUtils.random(5, charset.toCharArray())
    }

    // Add some simple ones
    def newKB = [ 'grammar': grammar, clauses: [] ]
    def usedLiterals = []
    while(usedLiterals.size() < grammar.size()) {
      def clause = []
      def cAmount = rand.nextInt(maxWordsPerClause+1) 
      def clauseGrammar = grammar.clone() - usedLiterals
      (0..cAmount).each {
        if(clauseGrammar.size() == 0) {
          return;
        }
        def newWordIndex = rand.nextInt(clauseGrammar.size())
        clause << clauseGrammar[newWordIndex]
        clauseGrammar.remove(newWordIndex)
      }
      if(clause) {
        usedLiterals += clause
        newKB.clauses << clause
      }
    }

    kbs << newKB
  }
  new File('kbs/'+i+'.json').write(new JsonBuilder(kbs).toPrettyString())
}
