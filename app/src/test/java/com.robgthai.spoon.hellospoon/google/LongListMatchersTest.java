package com.robgthai.spoon.hellospoon.google;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static com.robgthai.spoon.hellospoon.google.LongListMatchers.withItemContent;
import static com.robgthai.spoon.hellospoon.google.LongListMatchers.withItemSize;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;

import android.test.ActivityUnitTestCase;

/**
 * UnitTests for LongListMatchers matcher factory.
 */
public final class LongListMatchersTest extends ActivityUnitTestCase<LongListActivity> {

    LongListActivity mActivity;

    public LongListMatchersTest() {
        super(LongListActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        assertNotNull(mActivity);
//    startActivity(new Intent(getInstrumentation().getTargetContext(), LongListActivity.class),
//        null, null);
    }

    public void testWithContent() {
        assertThat(mActivity.makeItem(54), withItemContent("item: 54"));
        assertThat(mActivity.makeItem(54), withItemContent(endsWith("54")));
        assertFalse(withItemContent("hello world").matches(getActivity().makeItem(54)));
    }

    @SuppressWarnings("unchecked")
    public void testWithItemSize() {
        assertThat(mActivity.makeItem(54), withItemSize(8));
        assertThat(mActivity.makeItem(54), withItemSize(anyOf(equalTo(8), equalTo(7))));
        assertFalse(withItemSize(7).matches(getActivity().makeItem(54)));
    }
}
