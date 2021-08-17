package main.kotlin

data class Size(val x: Int, val y: Int)

data class Location(val x: Int, val y: Int )

class Cell(val location: Location) {
    var links = mutableListOf<Cell>()

    var north : Cell? = null
    var south : Cell? = null
    var east : Cell? = null
    var west : Cell? = null

    fun link( cell: Cell, bidirectional: Boolean = true ) {
        links.add(cell)
        if ( bidirectional ) {
            cell.link( this, false)
        }
    }

    fun unlink( cell: Cell, bidirectional: Boolean = true ) {
        if ( links.contains(cell) ) {
            val newLinks = links.filter { it != cell }
            links = mutableListOf<Cell>()
            links.addAll(newLinks)
            if ( bidirectional ) {
                cell.unlink(this, false)
            }

        }
    }

    fun linked( cell: Cell? ) : Boolean {
        var result = false
        if ( cell != null ) {
            result = links.contains( cell )
        }
        return result
    }

    // TODO:  Come back to this method
    fun neighbors() : List<Cell> {
        val result = mutableListOf(north, south, east, west)
        return result.filterNotNull()
    }



    // TODO:  need to implement Distances

    fun distances() : Distances {
        val result = Distances(this)

        var frontier : List<Cell> = mutableListOf(this)

        while (frontier.isNotEmpty()) {
            val newFrontier = mutableListOf<Cell>()

            for (cell in frontier) {
                val cellDistance=result.cells[cell]
                if (cellDistance != null) {
                    for (linked in cell.links) {
                        val linkedCell = result.cells[linked]
                        if (linkedCell == null) {
                            result.cells[linked] = cellDistance + 1
                            newFrontier.add(linked)
                        }
                    }
                }
            }

            frontier = newFrontier
        }

        return result
    }


}
