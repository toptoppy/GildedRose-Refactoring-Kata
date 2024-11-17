package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class GildedRoseTest {
    @Nested
    inner class CommonItems {

        @Test
        fun `quality cannot be negative`() {
            val items = listOf(Item("foo", 0, 0))
            val app = GildedRose(items)
            app.updateQuality()

            val expect = app.items[0]
            assertEquals(0, expect.quality)
        }

        @Test
        fun `given item type normal, then should be decrease in quality and sellin by 1`() {
            val items = listOf(Item("normal", 1, 1))

            val app = GildedRose(items)
            app.updateQuality()

            val expect = app.items[0]
            assertEquals("normal", expect.name)
            assertEquals(0, expect.sellIn)
            assertEquals(0, expect.quality)
        }

        @Test
        fun `given item type normal and sellIn 0, then should be decrease in quality by 1`() {
            val items = listOf(Item("normal", 0, 1))

            val app = GildedRose(items)
            app.updateQuality()

            val expect = app.items[0]
            assertEquals("normal", expect.name)
            assertEquals(-1, expect.sellIn)
            assertEquals(0, expect.quality)
        }

        @Test
        fun `given item type normal and sell by date has passed, then decrease in quality twice`() {
            val items = listOf(Item("normal", 0, 10))

            val app = GildedRose(items)
            app.updateQuality()

            val expect = app.items[0]
            assertEquals("normal", expect.name)
            assertEquals(-1, expect.sellIn)
            assertEquals(8, expect.quality)
        }
    }

    @Nested
    inner class Sulfuras {
        private val sulfuras = Item("Sulfuras, Hand of Ragnaros", 0, 80)

        @Test
        fun `given sulfuras type, quality always 80`() {
            val items = listOf(sulfuras)

            val app = GildedRose(items)
            app.updateQuality()

            val expect = app.items[0]
            assertEquals("Sulfuras, Hand of Ragnaros", expect.name)
            assertEquals(80, expect.quality)
        }

        @Test
        fun `given sulfuras type, sellin not change `() {
            val items = listOf(sulfuras)

            val app = GildedRose(items)
            app.updateQuality()

            val expect = app.items[0]
            assertEquals("Sulfuras, Hand of Ragnaros", expect.name)
            assertEquals(0, expect.sellIn)
        }
    }

    @Nested
    inner class AgedBrie {
        private val agedBries = Item("Aged Brie", 2, 0)

        @Test
        fun `given agedbrie type, quality incremental`() {
            val items = listOf(agedBries)

            val app = GildedRose(items)
            app.updateQuality()

            val expect = app.items[0]
            assertEquals("Aged Brie", expect.name)
            assertEquals(1, expect.quality)
        }

        @Test
        fun `given agedbrie type, sellin deduct`() {
            val items = listOf(agedBries)

            val app = GildedRose(items)
            app.updateQuality()

            val expect = app.items[0]
            assertEquals("Aged Brie", expect.name)
            assertEquals(1, expect.sellIn)
        }
    }

    @Nested
    inner class Backstage {

        @Test
        fun `given item then quality +2 when sellin between 10`() {
            val backstage = Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)
            val items = listOf(backstage)

            val app = GildedRose(items)
            app.updateQuality()

            val expect = app.items[0]
            assertEquals("Backstage passes to a TAFKAL80ETC concert", expect.name)
            assertEquals(22, expect.quality)
        }

        @Test
        fun `given item then quality +3 when sellin between 5`() {
            val backstage = Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)
            val items = listOf(backstage)

            val app = GildedRose(items)
            app.updateQuality()

            val expect = app.items[0]
            assertEquals("Backstage passes to a TAFKAL80ETC concert", expect.name)
            assertEquals(23, expect.quality)
        }

        @Test
        fun `given item then quality = 0 when sellin passed`() {
            val backstage = Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)
            val items = listOf(backstage)

            val app = GildedRose(items)
            app.updateQuality()

            val expect = app.items[0]
            assertEquals("Backstage passes to a TAFKAL80ETC concert", expect.name)
            assertEquals(0, expect.quality)
        }
    }

    @Nested
    inner class Conjured {}
}


