import groovy.json.JsonSlurper

// Load the KB
def file = new File('knowledgebase.json')
def KB = new JsonSlurper().parse(file).clauses

def toEntail = [ "Girl" ]
def entailed = []

// Naive 
// TODO: https://en.wikipedia.org/wiki/Unit_propagation
while(toEntail.size() > 0) {
  KB.each { clause ->
    def positive = clause.last() // Positive literal is last
    if(entailed.contains(positive)) return;

    if(clause.size() == 1) {
      entailed << positive
      toEntail -= positive
    } else {
      def ent = clause.every { entailed.contains(it) || it == positive }
      if(ent) {
        entailed << positive
        toEntail -= positive
      }
    }
  }
}

println "KB ⊨ Girl"
