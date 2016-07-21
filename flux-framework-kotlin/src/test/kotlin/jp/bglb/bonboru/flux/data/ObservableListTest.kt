package jp.bglb.bonboru.flux.data

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Created by tetsuya on 2016/07/22.
 */
class ObservableListTest() {

  lateinit var observableList: ObservableList<String>

  @Before
  fun setup() {
    observableList = ObservableList()
    assertEquals(0, observableList.list.size)
    assertEquals(Operation.INVALID, observableList.operation)

    val list = ArrayList<String>()
    for (i in 0..9) {
      list.add("item:$i")
    }
    observableList.addAll(list)
    assertEquals(10, observableList.list.size)
    assertEquals(Operation.INSERT, observableList.operation)
    assertEquals(0, observableList.fromOperatedPosition)
    assertEquals(9, observableList.toOperatedPosition)
    for (i in 0..9) {
      assertEquals("item:$i", observableList.list[i])
    }
  }

  @Test fun testConstructor() {
    val list = ArrayList<String>()
    for (i in 0..9) {
      list.add("item:$i")
    }
    observableList = ObservableList(list)

    assertEquals(10, observableList.list.size)
    assertEquals(Operation.INVALID, observableList.operation)
  }

  @Test fun testSwap() {
    val operateList = ObservableList<String>(observableList.list)
    assertEquals(10, operateList.list.size)
    for (i in 0..9) {
      assertEquals("item:$i", operateList.list[i])
    }

    operateList.swap(0, 1)
    assertEquals(Operation.SWAP, operateList.operation)
    assertEquals(false, operateList.equals(observableList))
    assertEquals("item:1", operateList.list[0])
    assertEquals("item:0", operateList.list[1])

    operateList.swap(1, 4)
    assertEquals(Operation.SWAP, operateList.operation)
    assertEquals(false, operateList.equals(observableList))
    assertEquals("item:4", operateList.list[1])
    assertEquals("item:0", operateList.list[4])
  }

  @Test fun testReplace() {
    val operateList = ObservableList<String>(observableList.list)
    assertEquals(10, operateList.list.size)

    operateList.replace(4, "itemReplaced")
    assertEquals(10, operateList.list.size)
    assertEquals(Operation.UPDATE, operateList.operation)
    assertEquals(false, operateList.equals(observableList))
    assertEquals("itemReplaced", operateList.list[4])
    assertEquals(4, operateList.fromOperatedPosition)
    assertEquals(4, operateList.toOperatedPosition)
    for (i in 0..3) {
      assertEquals("item:$i", operateList.list[i])
    }

    for (i in 5..9) {
      assertEquals("item:$i", operateList.list[i])
    }
  }

  @Test fun testAdd() {
    val operateList = ObservableList<String>(observableList.list)
    assertEquals(10, operateList.list.size)

    operateList.add("item:10")
    assertEquals(11, operateList.list.size)
    assertEquals(Operation.INSERT, operateList.operation)
    assertEquals(false, operateList.equals(observableList))
    assertEquals(10, operateList.fromOperatedPosition)
    assertEquals(10, operateList.toOperatedPosition)
    for (i in 0..10) {
      assertEquals("item:$i", operateList.list[i])
    }
  }

  @Test fun testIndexedAdd() {
    val operateList = ObservableList<String>(observableList.list)
    assertEquals(10, operateList.list.size)

    operateList.add(5, "addItem")
    assertEquals(11, operateList.list.size)
    assertEquals(Operation.INSERT, operateList.operation)
    assertEquals(false, operateList.equals(observableList))
    assertEquals(5, operateList.fromOperatedPosition)
    assertEquals(5, operateList.toOperatedPosition)
    for (i in 0..4) {
      assertEquals("item:$i", operateList.list[i])
    }
    assertEquals("addItem", operateList.list[5])
    for (i in 6..10) {
      assertEquals("item:${i - 1}", operateList.list[i])
    }
  }

  @Test fun testAddAll() {
    val operateList = ObservableList<String>(observableList.list)
    assertEquals(10, operateList.list.size)

    val addList = ArrayList<String>()
    for (i in 100..120) {
      addList.add("item:$i")
    }
    operateList.addAll(addList)
    assertEquals(31, operateList.list.size)
    assertEquals(Operation.INSERT, operateList.operation)
    assertEquals(false, operateList.equals(observableList))
    assertEquals(10, operateList.fromOperatedPosition)
    assertEquals(30, operateList.toOperatedPosition)
    for (i in 0..9) {
      assertEquals("item:$i", operateList.list[i])
    }
    for (i in 0..20) {
      assertEquals("item:${i + 100}", operateList.list[i + 10])
    }
  }

  @Test fun testRemove() {
    val operateList = ObservableList<String>(observableList.list)
    assertEquals(10, operateList.list.size)

    operateList.remove("item:4")
    assertEquals(9, operateList.list.size)
    assertEquals(Operation.DELETE, operateList.operation)
    assertEquals(false, operateList.equals(observableList))
    assertEquals(4, operateList.fromOperatedPosition)
    assertEquals(4, operateList.toOperatedPosition)
  }

  @Test fun testIndexRemove() {
    val operateList = ObservableList<String>(observableList.list)
    assertEquals(10, operateList.list.size)

    operateList.remove(3)
    assertEquals(9, operateList.list.size)
    assertEquals(Operation.DELETE, operateList.operation)
    assertEquals(false, operateList.equals(observableList))
    assertEquals(3, operateList.fromOperatedPosition)
    assertEquals(3, operateList.toOperatedPosition)
  }

}
