package com.example.codingexerciseae.adapter

import com.example.codingexerciseae.model.Hiring
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HiringListAdapterListTest {

    private lateinit var mockHiringAdapter: HiringListAdapter
    private var mockHiringList = emptyList<Hiring>()

    @Before
    fun setUp() {
        mockHiringAdapter = HiringListAdapter()
        mockHiringList = listOf(
            Hiring(id = 1, listId = 1, name = "Item1"),
            Hiring(id = 2, listId = 1, name = "Item12"),
            Hiring(id = 3, listId = 2, name = "Item11")
        )
    }

    @Test
    fun updateHiringList_should_create_headers_and_items() {
        mockHiringAdapter.updateHiringList(mockHiringList)

        assertEquals(5, mockHiringAdapter.itemCount)
        assertEquals(ListItem.Header(1), mockHiringAdapter.getItemAt(0))
        assertEquals(ListItem.HiringItem(mockHiringList[0]), mockHiringAdapter.getItemAt(1))
        assertEquals(ListItem.HiringItem(mockHiringList[1]), mockHiringAdapter.getItemAt(2))
        assertEquals(ListItem.Header(2), mockHiringAdapter.getItemAt(3))
        assertEquals(ListItem.HiringItem(mockHiringList[2]), mockHiringAdapter.getItemAt(4))
    }

    @Test
    fun getItemViewType_should_return_correct_view_type() {
        mockHiringAdapter.updateHiringList(mockHiringList)

        assertEquals(HiringListAdapter.VIEW_TYPE_HEADER, mockHiringAdapter.getItemViewType(0))
        assertEquals(HiringListAdapter.VIEW_TYPE_ITEM, mockHiringAdapter.getItemViewType(1))
        assertEquals(HiringListAdapter.VIEW_TYPE_ITEM, mockHiringAdapter.getItemViewType(2))
    }
}