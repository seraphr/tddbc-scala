package jp.seraphr.tddbc

import scala.collection.mutable.ArrayBuffer

class TodoCommand {
  
  val mTodos = new ArrayBuffer[Todo]
  
  def add(aTodo: Todo): Unit = {
    mTodos += aTodo
  }

  def getLastTodo(): Todo = {
    mTodos.last
  }
  
  def getFirstTodo(): Todo = {
    mTodos.head
  }
  
  def getTodos(): Seq[Todo] = {
    mTodos.toSeq
  }
}

object Todo{
  def create(aTodoString: String): Todo = Todo(aTodoString)
}

case class Todo(content: String)