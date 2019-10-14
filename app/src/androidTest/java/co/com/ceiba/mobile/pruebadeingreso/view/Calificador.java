package co.com.ceiba.mobile.pruebadeingreso.view;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.ceiba.mobile.pruebadeingreso.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Calificador {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Test
    public void emptyTest() {

        clickInputSearch();

        keypressInputSearch("empty");

        onView(allOf(withText("List is empty"))).check(matches(withText("List is empty")));


    }

    @Test
    public void ervinTest() {

        clickInputSearch();

        keypressInputSearch("Ervin");

        onView(allOf(withId(R.id.name))).check(matches(withText("Ervin Howell")));
        onView(allOf(withId(R.id.phone))).check(matches(withText("010-692-6593 x09125")));
        onView(allOf(withId(R.id.email))).check(matches(withText("Shanna@melissa.tv")));


    }

    @Test
    public void leanneTest() {

        clickInputSearch();

        keypressInputSearch("Leanne");

        onView(allOf(withId(R.id.name))).check(matches(withText("Leanne Graham")));
        onView(allOf(withId(R.id.phone))).check(matches(withText("1-770-736-8031 x56442")));
        onView(allOf(withId(R.id.email))).check(matches(withText("Sincere@april.biz")));

    }

    @Test
    public void leannePostClickVerPublicacionesTest() throws InterruptedException {

        clickInputSearch();
        keypressInputSearch("Leanne");

        onView(allOf(withId(R.id.btn_view_post))).perform(click());
        Thread.sleep(4000);

        onView(allOf(withId(R.id.name))).check(matches(withText(endsWith("Leanne Graham"))));
        onView(allOf(withId(R.id.phone))).check(matches(withText("1-770-736-8031 x56442")));


        onView(allOf(withId(R.id.title), withText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))).check(matches(withText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")));


    }

    private void keypressInputSearch(String valueToWrite) {
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextSearch),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayoutSearch),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText(valueToWrite), closeSoftKeyboard());
    }

    private void clickInputSearch() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextSearch),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayoutSearch),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(click());
    }

}
