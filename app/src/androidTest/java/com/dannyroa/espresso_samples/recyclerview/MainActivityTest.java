package com.dannyroa.espresso_samples.recyclerview;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.dannyroa.espresso_samples.recyclerview.TestUtils.withRecyclerView;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

  @Rule
  public ActivityScenarioRule<MainActivity> activityScenarioRule =
      new ActivityScenarioRule<>(MainActivity.class);

  @Test
  public void itemClick() {
    onView(ViewMatchers.withId(R.id.recycler_view))
        .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

    onView(withId(R.id.team_name)).check(matches(isDisplayed()));
  }

  @Test
  public void followButton_Click() {
    onView(withId(R.id.recycler_view))
        .perform(TestUtils.actionOnItemViewAtPosition(1, R.id.follow_button, click()));
    onView(withRecyclerView(R.id.recycler_view).atPositionOnView(1, R.id.follow_button))
        .check(matches(withText(followingText())));
  }


  @Test
  public void followButton_Click_2() {

    //Confirm there is not following button before clicked
    onView(withText(followingText())).check(doesNotExist());
    //Another way to confirm that the follow button is available.
    onView(withRecyclerView(R.id.recycler_view).atPositionOnView(0, R.id.follow_button))
        .check(matches(withText("Follow")));
    onView(withRecyclerView(R.id.recycler_view).atPositionOnView(1, R.id.follow_button))
        .check(matches(withText("Follow")));

    //Click the following button
    onView(withId(R.id.recycler_view))
        .perform(TestUtils.actionOnItemViewAtPosition(1, R.id.follow_button, click()));

    //Verify following button existed
    onView(withText(followingText())).check(matches(isDisplayed()));

    //Another approach to verify the following button existed.
    onView(withRecyclerView(R.id.recycler_view).atPositionOnView(1, R.id.follow_button))
        .check(matches(withText(followingText())));

    onView(withRecyclerView(R.id.recycler_view).atPositionOnView(0, R.id.follow_button))
        .check(matches(withText("Follow")));


  }

  private String followingText() {
    return InstrumentationRegistry.getInstrumentation()
        .getTargetContext()
        .getString(R.string.following);
  }

  @Test
  public void checkTest() {
    ViewInteraction viewInteraction = onView(withText("USA"));
    viewInteraction.check(matches(isDisplayed()));
    ViewInteraction colombia = onView(withText("Colombia"));
    colombia.check(doesNotExist());
  }

  @Test
  public void scrollToCertainItem_checkItsText() {
    onView(withId(R.id.recycler_view))
        .perform(RecyclerViewActions.actionOnItemAtPosition(10, click()));
    onView(withText("Uruguay")).check(matches(isDisplayed()));
  }

}
