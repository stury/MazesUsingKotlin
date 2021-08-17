package main.kotlin

fun main(args: Array<String>) {
    val maze = DistanceGrid(Size(5,5))
    //val mazeGenerator =  BinaryTree()
    //val mazeGenerator =  RecursiveBacktracker()
    val mazeGenerator =  randomMazeGenerator()
    val className = mazeGenerator::class.simpleName
    println("Here's a maze built using the $className algorithm!")
    mazeGenerator.on( maze )
    print( maze.description() )

    val start = maze.grid[0][0]
    if (start != null ) {
        print( "I've got a Cell.  Let's get some distances!\n" )

        val distances = start.distances()
        val end = maze.grid[maze.grid.count()-1][maze.grid[maze.grid.count()-1].count()-1]
        if (end != null ) {
            distances.path(end)
        }
        maze.distances = distances

        print( maze.description() )
    }
}
