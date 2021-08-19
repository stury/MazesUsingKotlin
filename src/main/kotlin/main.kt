package main.kotlin

fun distances(maze:DistanceGrid, startCell:Cell? = null ) {
    var start = maze.grid[0][0]
    if ( startCell != null ) {
        start = startCell
    }
    if (start != null ) {
        var distances = start.distances()
        maze.distances = distances
        print("\nDistances from (${start.location.x},${start.location.y}) cell\n")
        print(maze.description())
    }
}

fun path(maze:DistanceGrid, startCell:Cell? = null, endCell:Cell? = null) {
    var start = maze.grid[0][0]
    if ( startCell != null ) {
        start = startCell
    }
    if (start != null ) {
        var distances = start.distances()
        maze.distances = distances
        var end = maze[Location(maze.grid[maze.grid.count() - 1].count() - 1, maze.grid.count() - 1,)]
        if ( endCell != null ) {
            end = endCell
        }
        if (end != null) {
            distances = distances.path(end)
            print("\nPath from (${start.location.x},${start.location.y}) to (${end.location.x},${end.location.y})\n")
        }
        maze.distances = distances
        print(maze.description())
    }
}

fun main(args: Array<String>) {
    val width = 10
    val height = 10

    val maze = DistanceGrid(Size(width,height))
    //val mazeGenerator =  BinaryTree()
    //val mazeGenerator =  RecursiveBacktracker()
    val mazeGenerator =  randomMazeGenerator()
    val className = mazeGenerator::class.simpleName
    println("Here's a maze built using the $className algorithm!")
    mazeGenerator.on( maze )
    print( maze.description() )

    // Let's create a map of distances
    distances(maze)
    // Let's create a map of distances from a different start location
    distances(maze, maze.grid[height/2][width/2])

    // Let's create a path from upperLeft to lower right
    path(maze)

    // Let's create a path from a different start location
    path(maze, maze.grid[height-1][0], maze.grid[0][width-1])
}
