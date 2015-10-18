import groovy.json.JsonSlurper

// Load the KB
def file = new File('knowledgebase.json')
def KB = new JsonSlurper().parse(file).clauses

def toEntail = [ "Girl" ]

def solve(literals, KB) {
  if(literals.size() == 0) return true;

  for(def literal : literals) {
    def clauses = KB.findAll { it.last() == literal }
    for(def clause : clauses) {
      def newMatch = literals - literal + clause.init()
      if(solve(newMatch, KB)) return true;
    }
  }

  return false;
}

if(solve(toEntail, KB)) {
  println "KB ⊨ " + toEntail
} else {
  println "False"
}
