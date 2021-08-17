package main.kotlin

class DistanceGrid(size: Size) : Grid(size) {

    var distances : Distances? = null

    override fun contentsOfCell(cell: Cell) : String {
        var result : String
        val distances = distances
        if (distances != null) {
            val distance = distances.cells[cell]
            if (distance != null) {
                result = distance.toString(36)
            }
            else {
                result = super.contentsOfCell(cell)
            }
        }
        else {
            result = super.contentsOfCell(cell)
        }
        return result
    }

}