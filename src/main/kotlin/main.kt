package main.kotlin

fun main(args: Array<String>) {
    val maze = DistanceGrid(Size(10,10))
    //val mazeGenerator =  BinaryTree()
    //val mazeGenerator =  RecursiveBacktracker()
    val mazeGenerator =  randomMazeGenerator()
    val className = mazeGenerator::class.simpleName
    println("Here's a maze built using the $className algorithm!")
    mazeGenerator.on( maze )
    print( maze.description() )

    val start = maze.grid[0][0]
    if (start != null ) {
        var distances = start.distances()
        maze.distances = distances
        print( "\nDistances from North East (Upper Left) cell\n" )
        print( maze.description() )

        val end = maze.grid[maze.grid.count()-1][maze.grid[maze.grid.count()-1].count()-1]
        if (end != null ) {
            distances = distances.path(end)
        }
        maze.distances = distances
        print( "\nPath from NorthEast Cell to SouthWest Cell (Upper Left - to Lower Right)\n" )
        print( maze.description() )

    }
}
