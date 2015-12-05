class Forwards {
  static forwardChain(KB, toEntail) {
    def entailed = []
    def steps = 0
    def marked

    while(toEntail.size() > 0) {
      steps++
      marked = false
      KB.clauses.each { clause ->
        steps++
        def positive = clause.last() // Positive literal is last
        if(entailed.contains(positive)) return;

        def ent = clause.every { 
          steps++
          entailed.contains(it) || it == positive 
        }

        if(ent == true) {
          entailed << positive
          toEntail -= positive
          marked = true
        }
      }
      if(marked == false) {
        return [false, steps]
      }
    }

    if(toEntail.size() == 0) {
      return [true, steps]
    } else {
      return [false, steps]
    }
  }
}
