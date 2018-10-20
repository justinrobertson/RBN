package io.github.justinrobertson.rbn.display

import javafx.application.Application
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Parent
import tornadofx.*
import java.time.LocalDate
import java.time.Period

class Person (id: Int, name: String, birthday: LocalDate) {
    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty
    val nameProperty = SimpleStringProperty(name)
    var name by nameProperty
    val birthdayProperty = SimpleObjectProperty(birthday)
    var birthday by birthdayProperty
    val age: Int get() = Period.between(birthday, LocalDate.now()).years
}

class MyView : View() {
    private val persons = listOf(
    Person(1, "Fred Bloggs", LocalDate.of(2018,10,18)),
    Person(2, "Fred Bassett", LocalDate.of(2018,10,18)),
    Person(3, "Jack Russell", LocalDate.of(2018,10,18)),
    Person(4, "Jo Shlomo", LocalDate.of(2018,10,18))
    ).observable()

    override val root = tableview(persons) {
        column("ID", Person::idProperty)
        column("Name", Person::nameProperty)
        column("Birthday", Person::birthdayProperty)
        //column("Age", Person::age)
    }
}

class MyApp : App(MyView::class)

fun main(args: Array<String>) {
    Application.launch(MyApp::class.java, *args)
}