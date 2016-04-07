package com.njfarrell.validictest.data;

import com.njfarrell.validictest.CustomRecyclerAdapter;
import com.njfarrell.validictest.data.models.ListItem;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ListItemTest {

    @Test
    public void testGetViewType() {
        ListItem item = new ListItem(null);
        assertEquals(item.getViewType(), CustomRecyclerAdapter.LIST_ITEM);
    }

    @Test
    public void testGetText() {
        ListItem item = new ListItem(null);
        assertNotNull(item.getText());
    }
}
