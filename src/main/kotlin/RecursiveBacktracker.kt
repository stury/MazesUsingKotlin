package main.kotlin

import kotlin.random.Random

class RecursiveBacktracker : MazeGenerator() {
    override fun on(grid: Grid) {
        on(grid, grid.randomCell())
    }

    private fun on( grid: Grid, at: Cell) {
        val stack = mutableListOf<Cell>()
        stack.add(at)

        while ( stack.count() > 0 ) {
            val current = stack.last()
            val neighbors = current.neighbors().filter { cell -> cell.links.count() == 0 }
            if ( neighbors.count() == 0 ) {
                stack.removeLast()
            }
            else {
                val neighbor = neighbors[Random.nextInt(neighbors.count())]
                current.link(neighbor)
                stack.add(neighbor)
            }
        }
    }
}