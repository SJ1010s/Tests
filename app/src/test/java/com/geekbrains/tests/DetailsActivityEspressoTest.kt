package com.geekbrains.tests

import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.view.details.DetailsActivity
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsActivityEspressoTest {
    private lateinit var scenario: ActivityScenario<DetailsActivity>
    @Before
    fun setup(){
        scenario = ActivityScenario.launch(DetailsActivity::class.java)
    }

    @Test
    fun activity_AssertNotNull(){
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed(){
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activity_TextViewNotNull(){
        scenario.onActivity {
            val textView = it.findViewById<TextView>(R.id.totalCountTextView)
            TestCase.assertNotNull(textView)
        }
    }

    @Test
    fun activity_TextView_HasText(){
        val assertion: ViewAssertion = matches(withText(TEST_NUMBER_OF_RESULTS_ZERO))
        onView(withId(R.id.totalCountTextView)).check(assertion)
    }

    //	Следующим шагом давайте убедимся, что текст отображается на экране.
    //	Посмотрите на использование двух разных методов. Метод isDisplayed()
    //	вернет true если хотя бы часть View отображается на экране.
    //	Метод isCompletelyDisplayed() вернет true только если виджет полностью виден.
    //	Используйте нужный вам метод в подходящей ситуации:
    @Test
    fun activity_TextView_isDisplayed(){
        onView(withId(R.id.totalCountTextView)).check(matches(isDisplayed()))
    }

    @Test
    fun activity_TextView_isCompletelyDisplayed(){
        onView(withId(R.id.totalCountTextView)).check(matches(isCompletelyDisplayed()))
    }

    //Проверим кнопки:
    @Test
    fun activityButtons_AreEffectiveVisible(){
        onView(withId(R.id.incrementButton)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.decrementButton)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    //Метод withEffectiveVisibility() возвращает VISIBLE только если все вью в иерархии видны.
    // То есть если контейнер, в котором находится наша кнопка, на данный момент INVISIBLE
    // (или GONE), то и кнопка будет невидимой, несмотря ее параметр VISIBLE.
    //	Более того, как следует из названия метода — он возвращает именно видимость виджета,
    //	а не его отображение на экране. То есть наша кнопка может быть за границей экрана,
    //	и чтобы ее увидеть, нужно промотать экран ниже. Но тем не менее, она VISIBLE.
    //	Если вам нужно убедиться не только в том, что кнопка видна, но и отображается в видимой
    //	части экрана — используйте метод isDisplayed().

    @Test
    fun activityButtons_isDisplayed(){
        onView(withId(R.id.incrementButton)).check(matches(isDisplayed()))
        onView(withId(R.id.decrementButton)).check(matches(isDisplayed()))
    }

    //Осталось проверить как у нас отрабатываются нажатия на кнопки:
    fun activityButtonIncrement_IsWorking() {
        onView(withId(R.id.incrementButton)).perform(click())
        onView(withId(R.id.totalCountTextView)).check(matches(withText(TEST_NUMBER_OF_RESULTS_PLUS_1)))
    }
    fun activityButtonDecrement_IsWorking() {
        onView(withId(R.id.decrementButton)).perform(click())
        onView(withId(R.id.totalCountTextView)).check(matches(withText(
            TEST_NUMBER_OF_RESULTS_MINUS_1)))
    }

    @After
    fun close(){
        scenario.close()
    }
}