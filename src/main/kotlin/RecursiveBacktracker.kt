package main.kotlin

import kotlin.random.Random

class RecursiveBacktracker : MazeGenerator() {
    override fun on(grid: Grid) {
        on(grid.randomCell())
    }

    private fun on(at: Cell) {
        val stack = mutableListOf<Cell>()
        stack.add(at)

        while ( stack.isNotEmpty() ) {
            val current = stack.last()
            val neighbors = current.neighbors().filter { cell -> cell.links.isEmpty() }
            if ( neighbors.isEmpty() ) {
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