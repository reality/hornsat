class Backwards {
  static backwardsChain(KB, toEntail) {
    def steps = 0
    if(toEntail.size() == 0) return [true, steps];

    for(def literal : toEntail) {
      def clauses = KB.clauses.findAll { c ->
        steps++
        c.last() == literal
      }
      for(def clause : clauses) {
        steps++
        def newMatch = toEntail.clone() + clause.init() - literal
        def res = backwardsChain(KB, newMatch)

        steps += res[1]
        if(res[0] == true) {
          return [true, steps];
        }
      }
    }

    return [false, steps];
  }
}
