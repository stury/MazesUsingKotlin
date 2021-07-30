package main.kotlin

import kotlin.random.Random

abstract class MazeGenerator {
    abstract fun on( grid: Grid )
 }

// Add in a static routine to return a random MazeGenerator object.
fun randomMazeGenerator() : MazeGenerator {
    val mazes = listOf(BinaryTree(), RecursiveBacktracker())
    return mazes[Random.nextInt(mazes.count())]
}
