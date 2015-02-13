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

# What are there to test?

![](res/bg.png)

![inline](res/test_pyramid.png)

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

## <<Recheck this>>

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

# Test report?

```
open app/build/outputs/reports/androidTests/connected/index.html
```

![inline](res/test_report.png)

---

![](res/bg.png)

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

![](res/bg.png)

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

# Looks boring?

---

# Introduction to Spoon [^1]
![fit,right](res/logo.png)

[^1]: http://square.github.io/spoon/

---

![](res/bg.png)

# Spoon

> Spoon improve upon the existing instrumentation tests to make it more useful by visualizing the result.

Spoon run tests on all connected devices simultaneously as long as _**adb**_ can see it.

---

![](res/bg.png)

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

![](res/bg.png)

# Running Spoon

```
java -jar spoon-runner-1.1.2-jar-with-dependencies.jar
 --apk debug.apk
 --test-apk test.apk
 --sdk /usr/local/Cellar/android-sdk/23.0.2
```

---

![](res/bg.png)

# Open spoon report

```
open app/build/spoon/debug/index.html
```

![inline](res/spoon_1.png)

---

![](res/bg.png)

# Too hard? <br/> Let's find some help

---

![](res/bg.png)

# Integrate Spoon-gradle-plugin

Add buildscript to the top of App's _**build.gradle**_ and apply plugin.

```Java
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

![](res/bg.png)


# Using Spoon-gradle-plugin

_**spoon-gradle-plugin**_ allow Gradle to start spoon instrumentation testing.

```
./gradlew spoon
./gradlew spoon -PspoonClassName=fully.qualified.TestCase
```

_**Just like that**_  

---

![](res/bg.png)

# Config spoon plugin

```Java
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

![](res/bg.png)

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

![](res/bg.png)

# Instrumentation Testing

1. Design how actual user would interact with your application.
1. Then write a script imitating those actions.
1. Validating if the outcome match the expectation.

---

![original](res/test_indicator.png)

# What are the Test Cases?
![left|fit](res/login_still.png)

---

![](res/bg.png)

# Let's check if user see "Hello World!"

Back to _**MainActivityTest**_

```Java
public void testHelloWorldVisibleByDefault() {
  ViewGroup root = (ViewGroup) getActivity().findViewById(android.R.id.content);
  int total = root.getChildCount();

  for(int i = 0; i < total; i++) {
    View v = root.getChildAt(i);
    if(v instanceof TextView) {
      assertEquals("TextView contain Login"
      , ((TextView) v).getText().toString(), "Hello Login");
    }
  }
}
```

---

![](res/bg.png)

![inline| fit](res/donotwantcat.jpg)

---

![](res/bg.png)

![inline fit](res/espresso_lockup.png)

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

![](res/bg.png)

# Espresso.onView()

Use to find view inside the current hierarchy. It is to be used in conjunction with _**ViewMatcher**_ or any _**org.hamcrest.Matcher**_ implementation.

Example:

```Java
onView(withId(R.id.name_field))
onView(withText("Hello Steve!"))
```

## TBD

---

![fit](res/spoon_before.png)
![fit](res/spoon_after.png)

---

# That's ~~voodoo~~ Espresso

---

![](res/bg.png)

![inline](res/anatomy.png)

---

![](res/bg.png)

# 1. Espresso's ViewMatcher save the day.

- isDisplayed() // Match displayed view
- isEnabled() // Match enabled view
- hasSibling() // Match view with sibling
- withId // Match view with id
- Many more ...

---

![](res/bg.png)

# 2. Perform action on view using ... _**perform()**_

You can do all sort of stuff using _**ViewAction**_.

Click, scroll, type, swipe, hardware button, etc.

---

![](res/bg.png)

# 3. Check view state using ... _**check()**_

Check whether the view match the specified state.

_**ViewAssertion**_ contains **matches()** method that adapt Matcher to use when checking. ViewAssertions also contain **doesNotExist()** method for check such that.

---

![fit](res/espresso_v2_cheat_sheet.png)

---

# Let's try this

## https://code.google.com/p/android-test-kit/wiki/EspressoV2CheatSheet

![right | fit](res/login_flow.gif)

---

# Give it a shot

---

![original](res/test_indicator.png)

# Wild CEO appeared!

## The CEO use "Where is my logo?" <br /> It's super effective.

---

![](res/bg.png)

![left | fit](res/celebration.jpg)

# Congratulations you can now use Espresso!

---

# Recap

---

# What about ListView

![right | fit](res/listview.png)

---

# How?

---

# Espresso.onData()

## The ugly sister

---

![](res/bg.png)

# Espresso.onData()

_**onData**_ is very similar to _**onView**_ but is used to interact with data inside AdapterView. You can specify root or adapter or the view at given position.

```Java
• onChildView() • atPosition() • inRoot()
• inAdapterView() • usingAdapterViewProtocol()
• check() • perform()
```

---

![](res/bg.png)

# Espresso.onData()

```Java
onData(Matchers.is(LongListMatchers.withItemSize(8)))
.atPosition(0)
.perform(click());

onView(allOf(withText("7"), hasSibling(withText("item: 0"))))
.perform(click());
```

---

![original](res/test_indicator.png)

![left|fit](res/listview.png)

# Hands On with onData Spinner

---

![original](res/test_indicator.png)

![left|fit](res/listview.png)

# Hands on with ListView

---

![original](res/test_indicator.png)

![left|fit](res/thread.png)

# Hands On with Asynchronous task

registerIdlingResources

---

# Espresso recap

- onView()
- onData()
- registerIdlingResources()
- setFailureHandler()
- closeSoftKeyboard()
- pressBack()

---

# What else can it do?

- Open/Close NavigationDrawer
- Option Menu
- Contextual ActionBar
- etc.

---

# Introduction to Burst
![](res/burst.jpg)

---

![](res/bg.png)

# Burst
Burst is a library for testing module that required varying data to test.

**Sample Case:**

A month factory handle creation of month object from the given number of month.

i.e. 1 -> January, 2 -> February, ... , 12 -> December

---

![](res/bg.png)

# Standard UnitTest method

Test 1: VerifyJanuary
Test 2: VerifyFebruary
Test 3: VerifyMarch
Test 4: VerifyApril
...
Test 12: VerifyDecember

---

![original](res/test_indicator.png)

# Let's write that test

---

# You can use Burst in Instrumentation Test with ActivityRule

---

![](res/bg.png)

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

![](res/bg.png)

# Integrating Burst

## Force Hamcrest versioning

```Java
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

![](res/bg.png)

# Challenge Time <br /> Let's automate <br /> Github app[^g]

[^g]: https://github.com/github/android

---

# Extras

---

![](res/bg.png)

# AndroidTestCase & Android UnitTest

---

![](res/bg.png)

# Hamcrest Matcher [^h]

Hamcrest is a framework for writing matcher objects allowing _**match rules**_ to be defined declaratively.

[^h]: https://code.google.com/p/hamcrest/wiki/Tutorial

---

# Q&A

# :beers: :wine_glass: :cocktail:

---

# Thank you
![](res/me_full.png)

Poohdish Rattanavijai
thisisrobg@gmail.com
+RobGThai

---

![](res/bg.png)

# testHiEspressoIsDisplayed

create a new test to check TextView with "_**Hi Espresso**_" is displayed.

---

![](res/bg.png)

# testTextHelloIsDisplayed

create a new test to check TextView with id _**R.id.txtHello**_ is displayed.

---

![](res/bg.png)

# testTextHelloIsDisplayed

create a new test to check TextView with id _**R.id.txtHello**_ is displayed.

---

# Project setup
- espresso-core:2.0
- testing-support-lib:0.1
- espresso-contrib:2.0
- junit:4.11
