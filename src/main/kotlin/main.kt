package main.kotlin

fun main(args: Array<String>) {
    val maze = Grid(Size(15,10))
    //val mazeGenerator =  BinaryTree()
    //val mazeGenerator =  RecursiveBacktracker()
    val mazeGenerator =  randomMazeGenerator()
    val className = mazeGenerator::class.simpleName
    println("Here's a maze built using the $className algorithm!")
    mazeGenerator.on( maze )
    print( maze.description() )
}