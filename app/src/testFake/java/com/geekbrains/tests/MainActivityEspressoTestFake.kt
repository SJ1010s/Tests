package com.geekbrains.tests

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.view.search.MainActivityFake
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTestFake {
    private lateinit var scenario: ActivityScenario<MainActivityFake>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivityFake::class.java)
    }

    //	• Выделяем по тапу наш EditText;
    //	• Вставляем в него текст запроса. В нашем случае мы выбираем довольно редкий язык начала эры
    //	программирования Алгол. Это сделано с целью получить консистентный результат в процессе
    //	тестирования, потому что мало кто пишет проекты на этом языке и возвращаемое количество
    //	репозиториев не сильно меняется в отличие от того же Android, в котором количество может
    //	меняться каждую секунду;
    //	• Нажимаем на кнопку поиска на виртуальной клавиатуре;
    //	• Сравниваем отображаемый текст с ожидаемым результатом.
    @Test
    fun activitySearch_IsWorking() {
        fun activitySearch_IsWorking() {
            onView(withId(R.id.searchEditText)).perform(click())
            onView(withId(R.id.searchEditText)).perform(replaceText("algol"), closeSoftKeyboard())
            onView(withId(R.id.searchEditText)).perform(pressImeActionButton())

            onView(withId(R.id.totalCountTextView)).check(matches(withText("Number of results: 42")))

        }
    }

    private fun delay(): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $2 seconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(2000)
            }
        }
    }


    @After
    fun close() {
        scenario.close()
    }

}