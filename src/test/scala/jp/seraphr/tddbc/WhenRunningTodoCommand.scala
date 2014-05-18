package jp.seraphr.tddbc

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.BeforeAndAfter

class WhenRunningTodoCommand extends FlatSpec with Matchers with BeforeAndAfter {

  private var mTodoCommand: TodoCommand = _
  before {
    mTodoCommand = new TodoCommand
  }

  "user" should "TODOを追加できる" in {
    val tTodoCommand = mTodoCommand
    import tTodoCommand._

    add(Todo.create("command line string"))
  }

  "user" should "最後に追加したTODOを取得できる" in {
    val tTodoCommand = mTodoCommand
    import tTodoCommand._

    add(Todo.create("command1"))
    add(Todo.create("command2"))
    getLastTodo() should equal(Todo.create("command2"))
  }

  "user" should "最初に追加したTODOを取得できる" in {
    val tTodoCommand = mTodoCommand
    import tTodoCommand._

    add(Todo.create("command1"))
    add(Todo.create("command2"))
    getFirstTodo() should equal(Todo.create("command1"))
  }

  "user" should "追加した全てのTODOを取得できる" in {
    val tTodoCommand = mTodoCommand
    import tTodoCommand._

    add(Todo.create("最初に追加した文字列"))
    add(Todo.create("2番目"))
    add(Todo.create("最後に追加した文字列"))

    tTodoCommand.getTodos() should equal(Seq(
      Todo.create("最初に追加した文字列"),
      Todo.create("2番目"),
      Todo.create("最後に追加した文字列")))
  }
}