package main.kotlin

import kotlin.random.Random

class Grid(val size: Size) {

    private var grid : List<List<Cell?>>

    init {
        // Upon construction of the class, we should then create the list of cells
        grid = prepareGrid()
        configureCells()
    }

    private fun prepareGrid() : List<List<Cell?>> {
        val height = size.y
        val width = size.x

        val myGrid = mutableListOf<List<Cell?>>()
        for (y in 0 until height) {
            val myRow = mutableListOf<Cell?>()
            for (x in 0 until width) {
                val cell = Cell(Location(x,y))
                myRow.add(cell)
            }
            myGrid.add(myRow)
        }
        return myGrid
    }

    private fun configureCells() {
        val height = size.y
        val width = size.x

        for (row in 0 until height) {
            for (col in 0 until width) {
                val cell = grid[row][col]
                if ( cell != null ) {
                    if ( row > 0 ) {
                        cell.north = grid[row-1][col]
                    }
                    if ( row < height-1 ) {
                        cell.south = grid[row+1][col]
                    }
                    if ( col > 0 ) {
                        cell.west  = grid[row][col-1]
                    }
                    if ( col < width-1 ) {
                        cell.east  = grid[row][col+1]
                    }
                }
            }
        }
    }

    fun randomCell() : Cell {
        val result : Cell

        val row = Random.nextInt(0, size.y)
        val cellsInRow = grid[row].filterNotNull()
        val col = Random.nextInt( 0, cellsInRow.size )

        result = cellsInRow[col]

        return result
    }

    fun eachRow(block: (List<Cell?>) -> Void ) {
        for (row in grid) {
            block( row )
        }
    }

    // Return true from the block to tell us to stop iterating over the cells!
    fun eachCell(block: (Cell?) -> Boolean ) {
        var stop = false
        for (row in grid) {
            for (col in row) {
                stop = block( col )

                if ( stop ) {
                    break
                }
            }
            if ( stop ) {
                break
            }
        }
    }

//    public var cells : [Cell?] {
//        get {
//            var result = [Cell?]()
//
//            eachCell { (cell) -> Bool in
//                    result.append(cell)
//                return false
//            }
//            //print( result )
//            return result
//        }
//    }

    fun description() : String {
        var result = "+" + ("---+" * (size.x)) + "\n"

        for ( row in grid ) {
            var top = "|"
            var bottom = "+"
            for (cell in row) {

                val corner = "+"

                if (cell != null) {
                    val contents = " "
                    val body : String

                    val length = contents.count()

                    body = when ( length ) {
                        1 -> " $contents "
                        2 -> " $contents"
                        else -> contents
                    }

                    var eastBoundary = "|"
                    val eastCell = cell.east
                    if ( cell.linked(eastCell) ) {
                        eastBoundary = " "
                    }
                    top += body + eastBoundary

                    var southBoundary = "---"
                    val southCell = cell.south
                    if (cell.linked(southCell)) {
                        southBoundary = "   "
                    }
                    bottom += southBoundary + corner
                }
                else {
                    val eastBoundary  = "|"
                    val southBoundary = "---"

                    val body = "XXX"
                    top += body + eastBoundary
                    bottom += southBoundary + corner
                }
            }
            result += top + "\n"
            result += bottom + "\n"
        }

        return result
    }
}