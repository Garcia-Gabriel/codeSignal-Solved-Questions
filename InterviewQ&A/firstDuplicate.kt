fun solution(a: MutableList<Int>): Int {
  var lowestIndex = Int.MAX_VALUE
  var numsSet = hashSetOf<Int>()
  var indicesSet = hashSetOf<Int>()
  
  for (index in a.indices){
    if(!numsSet.contains(a[index])) numsSet.add(a[index]) else indicesSet.add(index)
  }
  
  // Or "lowestIndex = indicesSet.min()" using "import java.utils.*" or "import java.Math.min"
  for (index in indicesSet) {
    if(index < lowestIndex) lowestIndex = index
  }
  
  if(indicesSet.size > 0) return a[lowestIndex] else return -1
} 