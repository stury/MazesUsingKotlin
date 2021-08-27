package main.kotlin

import kotlin.random.Random

class Sidewinder : MazeGenerator() {

    override fun on(grid: Grid) {
        grid.eachRow { row ->
            val run = mutableListOf<Cell>()
            for (cell in row) {
                if (cell != null) {
                    run.add(cell)
                    val atEasternBoundary = cell.east == null
                    val atNorthernBoundary = cell.north == null
                    val shouldCloseOut = atEasternBoundary || (!atNorthernBoundary && Random.nextInt(2) == 0)
                    if ( shouldCloseOut ) {
                        val member = run.random()
                        val north = member.north
                        if ( north != null ) {
                            member.link(north)
                        }
                        run.clear()
                    }
                    else {
                        val east = cell.east
                        if ( east != null ) {
                            cell.link(east)
                        }
                    }
                }
            }
        }
    }
}