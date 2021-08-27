package main.kotlin

class DistanceGrid(size: Size) : Grid(size) {

    var distances : Distances? = null

    override fun contentsOfCell(cell: Cell) : String {
        val result : String
        val distances = distances
        result = if (distances != null) {
            val distance = distances.cells[cell]
            distance?.toString(36) ?: super.contentsOfCell(cell)
        } else {
            super.contentsOfCell(cell)
        }
        return result
    }

}