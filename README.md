### Usage

groovy backwards.groovy Girl Boy

groovy forwards.groovy Girl Boy

Additional entailment literals can be given, it's just space-delimited in the arguments.

### Knowledgebase

The knowledgebase is formed of positive Horn clauses, which form simple IF .. THEN ..
statements with exactly one positive literal. The last literal in the list is the positive
literal, and the rest are negatives. 

For example,

```json 
  [ "Child", "Female", "Girl" ]
```

Is logically equivalent to

```
  Child ∧ Female → Girl
```

##### Derive the empty clause, you know you want to
