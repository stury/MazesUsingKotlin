package main.kotlin

class Distances( val root : Cell ) {

    var cells : MutableMap<Cell,Int>

    init {
        cells = mutableMapOf<Cell,Int>()
        cells[root] = 0
    }

    fun knownCells() : List<Cell> {
        val result = mutableListOf<Cell>()

        for (cell:Cell in cells.keys) {
            result.add(cell)
        }

        return result
    }

    fun path(goal: Cell) : Distances {

        val result : Distances = Distances(root)
        var current = goal

        val item = cells[current]
        if (item != null) {
            result.cells[current] = item
            while ( current != root ) {
                val currentDistance = cells[current]
                if (currentDistance != null) {
                    for (neighbor:Cell in current.links) {
                        val neighborDistance = cells[neighbor]
                        if (neighborDistance != null) {
                            if (neighborDistance < currentDistance) {
                                result.cells[neighbor] = neighborDistance
                                current = neighbor
                                break
                            }
                        }
                    }
                }
                else {
                    print("Looks like there is a problem in Distances!  I cannot get a currentDistance.")
                    break
                }
            }
        }

        return result
    }

    fun max() : Pair<Cell, Int> {
        var maxDistance = 0
        var maxCell = root

        for (cell in cells.keys) {
            val distance = cells[cell]
            if (distance != null) {
                if (distance > maxDistance) {
                    maxCell = cell
                    maxDistance = distance
                }
            }
        }
        return Pair(maxCell, maxDistance)
    }

}