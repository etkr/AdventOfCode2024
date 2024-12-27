package day09

import kotlin.test.Test
import kotlin.test.assertEquals

class DiskMapTest {

    @Test
    fun `Test parser example 1`() {
        val diskMap = Parser().createDisk("12345")
        val excepted = listOf(0, null, null, 1, 1, 1, null, null, null, null, 2, 2, 2, 2, 2)
        assertEquals(excepted, diskMap.filesystem)
    }

    @Test
    fun `Test parser example 2`() {
        val diskMap = Parser().createDisk("2333133121414131402")
        val excepted = listOf(
            0, 0,
            null, null, null,
            1, 1, 1,
            null, null, null,
            2,
            null, null, null,
            3, 3, 3,
            null,
            4, 4,
            null,
            5, 5, 5, 5,
            null,
            6, 6, 6, 6,
            null,
            7, 7, 7,
            null,
            8, 8, 8, 8,
            9, 9
        )
        assertEquals(excepted, diskMap.filesystem)
    }

    @Test
    fun `Test first example`() {
        val disk = Parser().createDisk("2333133121414131402")
        disk.compress()
        println("Free space: ${disk.freeSpace}")
        println("Free space count: ${disk.freeSpace.count()}")
        println("Files: ${disk.fileIndex}")
        assertEquals(1928, disk.checksum)
    }

    @Test
    fun `Test puzzle input part 1`() {
        val stream = javaClass.getResourceAsStream("input.txt") ?: throw RuntimeException("input not found")
        val input = stream.bufferedReader().readText()
        val disk = Parser().createDisk(input)
        disk.compress()
        assertEquals(6461289671426, disk.checksum)
    }

    @Test
    fun `Test part 2 example`() {
        val disk = Parser().createDisk("2333133121414131402")
        disk.defragment()
        println(disk.filesystem.joinToString(""))
        assertEquals(2858, disk.checksum)
    }

    @Test
    fun `Test puzzle input part 2`() {
        val stream = javaClass.getResourceAsStream("input.txt") ?: throw RuntimeException("input not found")
        val input = stream.bufferedReader().readText()
        val disk = Parser().createDisk(input)
        disk.defragment()
        assertEquals(6488291456470, disk.checksum)
    }
}
