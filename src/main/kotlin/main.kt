package main.kotlin

fun main(args: Array<String>) {
    val maze = Grid(Size(10,10))
    val mazeGenerator =  randomMazeGenerator() //RecursiveBacktracker() //BinaryTree()
    val className = mazeGenerator::class.simpleName
    println("Here's a maze built using the $className algorithm!")
    mazeGenerator.on( maze )
    print( maze.description() )
}