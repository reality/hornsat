import groovy.json.JsonSlurper
import java.util.Random
def rand = new Random()

def file
def KBs
def results

for(def i=10;i<=1000;i+=10) {
  file = new File('kbs/'+i+'.json')
  KBs = new JsonSlurper().parse(file)
  results = [[], []]

  KBs.eachWithIndex { kb, z ->
    def toEntail = kb.grammar[rand.nextInt(kb.grammar.size())]
    def a = Forwards.forwardChain(kb, toEntail)
    def b = Backwards.backwardsChain(kb, [toEntail])
//    if(a[0] == true) {
    results[0] << a[1]
    results[1] << b[1]
 //   }
  }

  new File('./results', i+'.csv').withWriter('utf-8') { writer ->
    writer.writeLine results[0].join(',')
    writer.writeLine results[1].join(',')
  }
}
