class Forwards {
  static forwardChain(KB, toEntail) {
    def entailed = []
    def steps = 0

    while(toEntail.size() > 0) {
      steps++
      def marked = false
      KB.clauses.each { clause ->
        steps++
        def positive = clause.last() // Positive literal is last
        if(entailed.contains(positive)) return;

        if(clause.size() == 1) {
          entailed << positive
          toEntail -= positive
          marked = true
        } else {
          def ent = clause.every { 
            steps++
            entailed.contains(it) || it == positive 
          }
          if(ent) {
            entailed << positive
            toEntail -= positive
            marked = true
          }
        }
      }
      if(!marked) {
        break;
      }
    }

    if(toEntail.size() == 0) {
      return [true, steps]
    } else {
      return [false, steps]
    }
  }
}
