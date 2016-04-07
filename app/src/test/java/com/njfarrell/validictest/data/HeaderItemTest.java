package com.njfarrell.validictest.data;

import com.njfarrell.validictest.CustomRecyclerAdapter;
import com.njfarrell.validictest.data.models.HeaderItem;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HeaderItemTest {

    @Test
    public void testGetViewType() {
        HeaderItem item = new HeaderItem(null);
        assertEquals(item.getViewType(), CustomRecyclerAdapter.HEADER_ITEM);
    }

    @Test
    public void testGetText() {
        HeaderItem item = new HeaderItem(null);
        assertNotNull(item.getText());
    }
}
