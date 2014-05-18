package jp.seraphr.tddbc

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.BeforeAndAfter
import scala.util.Success
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class WhenRunningTodoCommand extends FlatSpec with Matchers with BeforeAndAfter with GeneratorDrivenPropertyChecks {

  private var mTodoCommand: TodoCommand = _
  before {
    mTodoCommand = new TodoCommand
  }

  private val firstTodo = Todo.create("first todo")
  private val secondTodo = Todo.create("second todo")

  private def addTwoTodos(): Unit = {
    mTodoCommand.add(firstTodo)
    mTodoCommand.add(secondTodo)
  }

  "user" should "TODOを追加できる" in {
    val tTodoCommand = mTodoCommand
    import tTodoCommand._

    add(Todo.create("command line string"))
  }

  "user" should "最後に追加したTODOを取得できる" in {
    val tTodoCommand = mTodoCommand
    import tTodoCommand._

    addTwoTodos()
    getLastTodo() should equal(Some(secondTodo))
  }

  "user" should "0件のときは最後のTODOを取得できない" in {
    val tTodoCommand = mTodoCommand
    import tTodoCommand._

    getLastTodo() should be(None);
  }

  "user" should "最初に追加したTODOを取得できる" in {
    val tTodoCommand = mTodoCommand
    import tTodoCommand._

    addTwoTodos()

    getFirstTodo() should equal(Some(firstTodo))
  }

  "user" should "0件のときは最初のTODOを取得できない" in {
    val tTodoCommand = mTodoCommand
    import tTodoCommand._

    getFirstTodo() should be(None);
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

  "user" should "TODOを2個を追加したあと、最初のTODOを削除すると、2つ目に追加したものだけになる" in {
    val tTodoCommand = mTodoCommand
    import tTodoCommand._

    addTwoTodos()

    removeFirst()
    getTodos() should be(Seq(secondTodo))
  }

  "user" should "TODOが空の状態で最初のTODOを削除しても、空のままになる" in {
    val tTodoCommand = mTodoCommand
    import tTodoCommand._

    getTodos().isEmpty should be(true)
    removeFirst()
    getTodos().isEmpty should be(true)
  }

  "user" should "TODOを2個を追加したあと、最後のTODOを削除すると、1つ目に追加したものだけになる" in {
    val tTodoCommand = mTodoCommand
    import tTodoCommand._

    addTwoTodos()

    removeLast()
    getTodos() should be(Seq(firstTodo))
  }

  "user" should "TODOが空の状態で最後のTODOを削除しても、空のままになる" in {
    val tTodoCommand = mTodoCommand
    import tTodoCommand._

    getTodos().isEmpty should be(true)
    removeLast()
    getTodos().isEmpty should be(true)
  }

  "user" should "TODOを何個か追加したあと、全てを削除すると、空になる" in {
    val tTodoCommand = mTodoCommand
    import tTodoCommand._

    forAll { tTodos: List[String] =>
      tTodos.map(Todo.create).foreach(tTodoCommand.add)
      tTodoCommand.removeAll()
      tTodoCommand.getTodos() should have size 0
    }
  }
}