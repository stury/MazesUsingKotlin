package main.kotlin

import kotlin.random.Random

class BinaryTree : MazeGenerator() {
    override fun on( grid: Grid ) {
        grid.eachCell { cell ->
            val result = false
            val neighbors = mutableListOf<Cell>()
            if ( cell != null ) {
                val north = cell.north
                if ( north != null ) {
                    neighbors.add( north )
                }
                val east = cell.east
                if ( east != null ) {
                    neighbors.add( east )
                }
            }
            else {
                print("Unknown Cell sent to BinaryTree to process!")
            }

            if (neighbors.count() > 0) {
                val neighbor : Cell = when( neighbors.count() ) {
                    1 -> neighbors[0]
                    else -> {
                        val index = Random.nextInt( 0, neighbors.count() )
                        if ( index > neighbors.count() ) {
                            print( "Error" )
                        }
                        neighbors[index]
                    }
                }
                cell?.link( neighbor )
            }

            result
        }
    }
}