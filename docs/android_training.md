footer: Poohdish Rattanavijai (+RobGThai), 2015
slidenumbers: true

![right|80%](res/logo.png)

#[fit] Testing Android Application

---

![](res/bg.png)

# Machine Setup
- JDK (1.7+)
- Android SDK
- Android Studio
- Gradle
- Genymotion (Optional)

---

![](res/bg.png)

# Objectives

- Understand the need for Instrumenting Acceptance Test
- Being able to write basic instrument test for Android application
- Generating consumable and sane report
- Learning about core component of Android Testing
- Tips, Tricks, and limitations

---

# Why Automate Test?

^ Talk about the tedious testing over number of devices.

---

# 2012

![original](res/frag_2012.png)

---

# 2013

![original](res/frag_2013.png)

---

# 2014

![original](res/frag_2014.png)

---

# What is your scope?

^ How many device should be tested?

---

One device?

![original|fit](res/fb_1.png)
![original|fit](res/fb_2.png)
![original|fit](res/fb_3.png)

---

# A brief history of testing on Android

---

![](res/bg.png)

# Testing on Android (Dark Ages)

- Robolectric
- Robotium
- JUnit
- Android Mock

---

![](res/bg.png)

# Robolectric

![inline fill](res/tools/robolectric.png)

---

![](res/bg.png)

# Robotium

![inline fill](res/tools/robotium.png)

---

![](res/bg.png)

# JUnit

![inline fill](res/tools/junit.png)

---

![](res/bg.png)

# Android Mock

![inline fill](res/tools/android-mock.png)

---

![](res/bg.png)

# Testing on Android (Middle Ages)

- Robolectric
- Calabash
- UiAutomator
- Espresso 1.1

---

![](res/bg.png)

# Calabash

![inline fill](res/tools/calabash.png)

---

![](res/bg.png)

# UiAutomator

![inline fill](res/tools/uiautomator.png)

---

![original](res/goldenages.jpg)

## Testing on Android (Golden Ages)

1. Testing Framework
1. Spoon
1. Espresso 2.0
1. Burst

---

# Begin Android Testing

---

![right|fit](res/test_framework.png)

# 1. Testing Framework

---

![original](res/test_indicator.png)

# Creating HelloTest

1. Create _**New Project**_ in Android Studio
2. Name it _**HelloTest**_
3. Minimum SDK 16 (Android 4.1 JB)
4. Blank Activity
5. Make some coffee while waiting

![right](res/set_up.png)

---

![](res/bg.png)

# Act like an expert

_Check if project can be built_

```
./gradlew build
```

_Check if device connected_

```
adb devices
```

_Install application_

```
./gradlew installDebug
```

![left|fit](res/toolbar.png)

---

![](res/bg.png)

# Run your first test

```
./gradlew connectedCheck
```

or use Gradle shortcuts

```
./gradlew cC
```

![inline](res/test_build.png)

---

![](res/bg.png)

![inline](res/test_pyramid.png)

---

![](res/bg.png)

# Test report?

```
open app/build/outputs/reports/androidTests/connected/index.html
```

![inline](res/test_report.png)

---

# Project setup
- espresso-core:2.0
- testing-support-lib:0.1
- espresso-contrib:2.0
- junit:4.11

---

# Create your 1st test case

1. Create _**MainActivityTest**_ Class in the same directory with _**ApplicationTest**_.
2. Extends ActivityInstrumentationTestCase_**2**_

```Java
public class MainActivityTest
extends ActivityInstrumentationTestCase2<MainActivity>{

  public MainActivityTest() {
    super(MainActivity.class);
  }
}
```

---

# Create your 1st test method

```Java
public void testActivityIsAvailable() {
  assertNotNull("Activity is not available", getActivity());
}
```

Rerun the test

```
./gradlew cC
```

---

# Introduction to Spoon [^1]
![fit,right](res/ceiling_android.png)

[^1]: http://square.github.io/spoon/

---

# Spoon

> Spoon improve upon the existing instrumentation tests to make it more useful by visualizing the result.

Spoon run tests on all connected devices simultaneously as long as _**adb**_ can see it.

---

# Integrating Spoon

• Add Spoon client to project.

```
androidTestCompile 'com.squareup.spoon:spoon-client:1.1.2'
```

• Capture screenshot inside test.

```Java
Spoon.screenshot(activity, "initial_state");
```

_**That's it.**_

---

# <Run Spoon Manually>

---

# Open spoon report

```
open app/build/spoon/debug/index.html
```

![inline](res/spoon_1.png)

---

# <Make it easier>

---

# Integrate Spoon-gradle-plugin

Add buildscript to the top of App's _**build.gradle**_ and apply plugin.

```
buildscript {
  repositories {
    jcenter()
  }
  dependencies {
    classpath 'com.stanfy.spoon:spoon-gradle-plugin:0.14.1'
  }
}

apply plugin: 'spoon'
```

---

# Using Spoon-gradle-plugin

_**spoon-gradle-plugin**_ allow Gradle to start spoon instrumentation testing.

```
./gradlew spoon
./gradlew spoon -PspoonClassName=fully.qualified.TestCase
```

---

# Config spoon plugin

```
spoon {
  // for debug output
  debug = true

  // Change report output directory
  baseOutputDir = file("$buildDir/outputs/reports/spoon/")

  // Allow specifying single test via CLI
  if (project.hasProperty('spoonClassName')) {
    className = project.spoonClassName

    if (project.hasProperty('spoonMethodName')) {
      methodName = project.spoonMethodName
    }
  }
}
```

---

# Let's "see" the report

```Java
public void testScreenshotIsWorking() {
  assertNotNull("Activity is not available", getActivity());
  Spoon.screenshot(getActivity(), "start");
}
```

Run it using Spoon Runner

```
./gradlew spoon
```

---

# Let's do some real testing

---

# Instrumentation Testing

1. Design how actual user would interact with your application.
1. Then write a script imitating those actions.
1. Validating if the outcome match the expectation.

---

# Let's check if user see "Hello World!"

Back to MainActivityTest

```Java
public void testHelloWorldVisibleByDefault() {
  ViewGroup root = (ViewGroup) getActivity().findViewById(android.R.id.content);
  int total = root.getChildCount();

  for(int i = 0; i < total; i++) {
    View v = root.getChildAt(i);
    if(v instanceof TextView) {
      assertEquals("TextView contain Hello World!", ((TextView) v).getText().toString(), "Hello World!");
    }
  }
}
```

---

# <Add WTF Login screen test>

---

![fit](res/donotwantcat.jpg)

---

![fit](res/espresso_lockup.png)

---

# Espresso is a _simple API_ for writing _beautiful_ UI test

---

# Let's Begin

^ - onView()
- onData()
- registerIdlingResources()
- setFailureHandler()
- closeSoftKeyboard()
- pressBack()

---

# Espresso.onView()

Use to find view inside the current hierarchy. It is to be used in conjunction with _**ViewMatcher**_ or any _**org.hamcrest.Matcher**_ implementation.

Example:

```Java
onView(withId(R.id.name_field))
onView(withText("Hello Steve!"))
```

## TBD

---

# testHiEspressoIsDisplayed

create a new test to check TextView with "_**Hi Espresso**_" is displayed.

---

# testTextHelloIsDisplayed

create a new test to check TextView with id _**R.id.txtHello**_ is displayed.

---

# testTextHelloIsDisplayed

create a new test to check TextView with id _**R.id.txtHello**_ is displayed.

---

# Espresso Punchline

---

![inline](res/anatomy.png)

---

# 1. Espresso's ViewMatcher save the day.

- isDisplayed() // Match displayed view
- isEnabled() // Match enabled view
- hasSibling() // Match view with sibling
- withId // Match view with id
- Many more ...

---

# 2. Perform action on view using ... _**perform()**_

You can do all sort of stuff using _**ViewAction**_.

Click, scroll, type, swipe, hardware button, etc.

---

# 3. Check view state using ... _**check()**_

Check whether the view match the specified state.

_**ViewAssertion**_ contains **matches()** method that adapt Matcher to use when checking. ViewAssertions also contain **doesNotExist()** method for check such that.

---

![fit](res/espresso_v2_cheat_sheet.png)

---

# <Show final login outcome>

---

# Give it a shot

---

# Exploring Espresso (TDD style) checkpoint 1

1. TextView with id _**txtHello**_ is _**visible**_.
1. TextView with id _**txtHello**_ is displaying "_**Hi Espresso**_".
1. Button with text _**Start**_ is _**displayed**_.
1. Click on Button with text _**Start**_ should start another Activity.

---

# Exploring Espresso (TDD style) checkpoint 2

1. Click on Button with text _**Toast**_ should toast "_**Espresso rocks**_".
2. After clicked button should become _**disabled**_.

---

# Lunch

---

# Recap

---

# <ListView>

---

# How?

---

# Espresso.onData()

## The ugly sister

---

# Espresso.onData()

_**onData**_ is very similar to _**onView**_ but is used to interact with data inside AdapterView. You can specify root or adapter or the view at given position.

• onChildView() • atPosition() • inRoot() • inAdapterView() • usingAdapterViewProtocol() • check() • perform()


---

# Espresso.onData()

```Java
onData(Matchers.is(LongListMatchers.withItemSize(8)))
.atPosition(0)
.perform(click());

onView(allOf(withText("7"), hasSibling(withText("item: 0"))))
.perform(click());
```

---

# Hands On with onData

---

# Hands On with onData

---

# Hands On with onData

---

# Hands On with onData

---

# Espresso recap

- onView()
- onData()
- registerIdlingResources()
- setFailureHandler()
- closeSoftKeyboard()
- pressBack()

---

# public void testSayHello()

```Java
onView(withId(R.id.name_field))
.perform(typeText("Steve"));

onView(withId(R.id.greet_button))
.perform(click());

onView(withText("Hello Steve!"))
.check(matches(isDisplayed()));
```

---

# public void testSayHello()

_**onView**_(_**withId**_(R.id._**text**_))
._**perform**_(_**typeText**_("Steve"));

_**onView**_(_**withText**_("Hello Steve!"))
._**check**_(_**matches**_(_**isDisplayed**_()));

---

# What else can it do?

- Open/Close NavigationDrawer
- Option Menu
- Contextual ActionBar
- etc.

---

# Extras

---

# Introduction to Burst
![](res/burst.jpg)

---

# Burst
Burst is a library for testing module that required varying data to test.

**Sample Case:**

A month factory handle creation of month object from the given number of month.

i.e. 1 -> January, 2 -> February, ... , 12 -> December

---

# Standard UnitTest method

Test 1: VerifyJanuary
Test 2: VerifyFebruary
Test 3: VerifyMarch
Test 4: VerifyApril
...
Test 12: VerifyDecember

---

# Let's write that test

---

# Integrating Burst

## Dependencies
```
androidTestCompile 'junit:junit:4.11'
androidTestCompile 'com.squareup.spoon:spoon-client:1.1.2'
androidTestCompile ('com.android.support.test.espresso:espresso-core:2.0')
androidTestCompile ('com.android.support.test.espresso:espresso-contrib:2.0') {
  exclude group: 'com.android.support', module: 'appcompat'
  exclude group: 'com.android.support', module: 'support-v4'
  exclude module: 'recyclerview-v7'
}
androidTestCompile ('com.squareup.burst:burst-junit4:1.0.2')
```

---

# Integrating Burst

## Force Hamcrest versioning

```
configurations.all {
  exclude module : 'junit-dep'

  resolutionStrategy {
    force 'org.hamcrest:hamcrest-core:1.1'
    force 'org.hamcrest:hamcrest-integration:1.1'
    force 'org.hamcrest:hamcrest-library:1.1'
  }
}
```

---

# AndroidTestCase

Extras

---

# Android UnitTest

Extras

---

# Android Test Kit [^*]

< should remove this?>

---

[^*]: git clone https://code.google.com/p/android-test-kit/

# Hamcrest Matcher [^*] (Removed)

Hamcrest is a framework for writing matcher objects allowing _**match rules**_ to be defined declaratively.

[^*]: https://code.google.com/p/hamcrest/wiki/Tutorial

---

# Hamcrest Matcher Example: (Removed)

```Java
public class IsNotANumber extends TypeSafeMatcher<Double> {

  @Override
  public boolean matchesSafely(Double number) {
    return number.isNaN();
  }

  public void describeTo(Description description) {
    description.appendText("not a number");
  }

  @Factory
  public static <T> Matcher<Double> notANumber() {
    return new IsNotANumber();
  }

}
```

---
