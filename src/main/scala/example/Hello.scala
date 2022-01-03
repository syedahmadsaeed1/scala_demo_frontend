package example
import org.scalajs.dom
import org.scalajs.dom.document
import scala.scalajs.js
// import org.scalajs.jquery.JQueryStatic
import scala.scalajs.js.annotation.{JSGlobal}
import scala.scalajs.js.annotation.{JSExportTopLevel, JSExport, JSExportAll, JSImport}
import scala.scalajs.js.JSON
import scala.concurrent.ExecutionContext.Implicits.global
import org.scalajs.dom.ext.Ajax


@js.native
trait Framework7Views extends js.Object {
  def create(a: js.Any): Unit = js.native;
  
}

// @js.native
// @JSImport("jquery", JSImport.Namespace)
// object jquery extends JQueryStatic

@js.native
@JSGlobal
class Framework7(conf: js.Any) extends js.Object {
  val views: Framework7Views = js.native;
  
}

@js.native
trait DomElement extends js.Object {
  def html(): String = js.native
  def html(c: String): String = js.native
  def valu(): String = js.native
}

@js.native
@JSGlobal
object Dom7 extends js.Object {
  // def apply(selector: String): dom.Element = document.querySelector(selector)
  def apply(selector: String): DomElement = js.native
}

@js.native
@JSGlobal
object Template7 extends js.Object {
  def compile(template: String): js.Function1[js.Dictionary[js.Array[js.Dictionary[String]]], String] = js.native
  def registerHelper(name: String, helper: js.Function1[String, String]): String = js.native
}

// @JSExportTopLevel("TodoHelpers")
class TodoHelper() {
  // val items = js.Array();

  @JSExport
  def addToList() = {
    println("asd")
  }
}

object Hello extends Greeting with App {

  val app = new Framework7(js.Dictionary("el" -> "#app", "id" -> "com.myapp.test"));
  val mainView = app.views.create(".view-main");

  val $$ = Dom7;
  val template = $$("#app").html();
  // val list_items = js.Array("list item 1", "list item 2", "list item 3", "list item 4", "list item 5");
  // val list_items = js.Array(js.Dictionary("title" -> "list item 1", "isc" -> ""), js.Dictionary("title" -> "list item 2", "isc" -> ""), js.Dictionary("title" -> "list item 3", "isc" -> ""));
  var list_items = js.Array(js.Dictionary("title" -> "", "isc" -> ""));
  getToDoList();

  def renderTemplate(template: String, data: js.Array[js.Dictionary[String]]): Unit = {
    val compiledTemplate = Template7.compile(template);
    
    val compiled = compiledTemplate(js.Dictionary("context_data" -> data));
    $$("#app").html(compiled);
    bindClick();
    changeChecked();
    bindDelete();
  }

  def changeChecked(): Unit = {
    val btns = document.querySelectorAll(".item-checkbox");
    for (i <- 0 until btns.length) {
      btns(i).addEventListener("click", { (e: dom.Event) =>
        val index = btns(i).asInstanceOf[dom.Element].getAttribute("data-index").toInt;
        val value = btns(i).asInstanceOf[dom.Element].querySelector("input").asInstanceOf[dom.html.Input].checked.toString
        var check = ""
        if(value == "true") {
          list_items(index).update("isc", "checked");
          check = "checked";
        } else {
          list_items(index).update("isc", "");
        }

        val item = js.Dictionary("item" -> list_items(index), "index" -> index);
        list_items(index).update("isc", check);
        Ajax.post("http://localhost:5000/update_todo_item", JSON.stringify(item))

        renderTemplate(template, list_items);
      });
    }
  }

  def bindClick(): Unit = {
    val btn = document.getElementById("addTodo");
    var input = document.getElementById("todoText").asInstanceOf[dom.html.Input];
    btn.addEventListener("click", { (e: dom.Event) =>  
      if ( input.value != null && input.value != "") {
        val item = js.Dictionary("title"-> input.value, "isc" -> "");
        list_items += item;
        Ajax.post("http://localhost:5000/add_todo_item", JSON.stringify(item))
        renderTemplate(template, list_items);
      }
    });
  }

  def bindDelete(): Unit = {
    val btns = document.querySelectorAll(".delete-todo");
    
    for (i <- 0 until btns.length) {
      btns(i).addEventListener("click", { (e: dom.Event) =>
        val index = btns(i).asInstanceOf[dom.Element].getAttribute("data-index").toInt;
        // list_items -= list_items(index);
        val item = js.Dictionary("item" -> list_items(index), "index" -> index);
        Ajax.post("http://localhost:5000/delete_todo_item", JSON.stringify(item)).onSuccess {
          case xhr =>
            list_items -= list_items(index);
            renderTemplate(template, list_items);
        }
      });
    }

    // btn.addEventListener("click", { (e: dom.Event) =>  
    //   list_items -= input.value;
    //   renderTemplate(template, list_items);
    // });

    // val deleteBtns = document.getElementById("deleteTodo");
  }

  def getToDoList(): Unit = {
      val res = Ajax.get("http://localhost:5000/get_todo_list").map(xhr => {
        val data = JSON.parse(xhr.responseText).asInstanceOf[js.Array[js.Dictionary[String]]];
        list_items = data
        // for(i <- 0 until data.length) {
        //   println(data(i))
        //   list_items += data(i);
        // }
        renderTemplate(template, list_items);
      })
  }

  // renderTemplate(template, list_items);


   //onclick = {() => {}};
  // println(btn);

  
  // val compiledTemplate = Template7.compile(template);
  // val compiled = compiledTemplate(js.Dictionary("context_data" -> list_items));

  // $$("#app").html(compiled);
}


trait Greeting {
  lazy val greeting: String = "hello"
}

