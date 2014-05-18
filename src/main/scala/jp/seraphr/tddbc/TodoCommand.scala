package jp.seraphr.tddbc

import scala.collection.mutable.ArrayBuffer
import scala.util.Try

class TodoCommand {

  val mTodos = new ArrayBuffer[Todo]

  def add(aTodo: Todo): Unit = {
    mTodos += aTodo
  }

  def getLastTodo(): Option[Todo] =
    if (mTodos.isEmpty)
      None
    else
      Some(mTodos.last)

  def getFirstTodo(): Option[Todo] =
    if (mTodos.isEmpty)
      None
    else
      Some(mTodos.head)

  def getTodos(): Seq[Todo] = {
    mTodos.toSeq
  }

  def removeFirst(): Option[Todo] = {
    if (mTodos.isEmpty)
      None
    else
      Some(mTodos.remove(0))
  }

  def removeLast(): Option[Todo] = {
    if (mTodos.isEmpty)
      None
    else
      Some(mTodos.remove(mTodos.length - 1))
  }
  
  def removeAll(): Unit = {
    mTodos.clear()
  }
}

object Todo {
  def create(aTodoString: String): Todo = Todo(aTodoString)
}

case class Todo(content: String)