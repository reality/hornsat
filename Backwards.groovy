class Backwards {
  static backwardsChain(KB, toEntail) {
    backwardsChain(KB, toEntail, 0)
  }

  static backwardsChain(KB, toEntail, steps) {
    if(toEntail.size() == 0) return [true, steps];

    for(def literal : toEntail) {
      steps++
      def clauses = KB.clauses.findAll { 
        steps++
        it.last() == literal 
      }
      for(def clause : clauses) {
        steps++
        def newMatch = toEntail.clone()
        newMatch.remove(literal)
        newMatch += clause.init()
        if(backwardsChain(KB, newMatch, steps)) return [true, steps];
      }
    }

    return [false, steps];
  }
}
