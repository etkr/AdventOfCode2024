package day09

data class File(val id: Int, val start: Int, val end: Int) {
    val length get() = end - start + 1
}

data class Space(var start: Int, val end: Int) {
    val length get() = end - start + 1
}


class Disk(val filesystem: MutableList<Int?>) {


    val fileIndex: MutableList<File> = mutableListOf()
    val freeSpace: MutableList<Space> = mutableListOf()

    init {
        var start = 0
        var end = 0
        var previous: Int? = filesystem[0]
        for (i in 0 until filesystem.size) {
            val current = filesystem[i]
            if (i == filesystem.size - 1) {
                if (previous == null) {
                    freeSpace.add(Space(start, end))
                } else {
                    fileIndex.add(File(previous, start, end))
                }
            }
            if (previous != current) {
                if (previous == null) {
                    freeSpace.add(Space(start, end - 1))
                } else {
                    fileIndex.add(File(previous, start, end - 1))
                }
                start = i
                end = i
            }
            end++
            previous = current
        }
    }


    fun getLastNotNull(): Int {
        var it: Int? = null
        while (it == null) {
            it = filesystem.removeLast()
        }
        return it
    }

    fun compress() {
        var i = 0
        while (i < filesystem.size) {
            if (filesystem[i] == null) {
                filesystem[i] = getLastNotNull()
            }
            i++
        }
    }

    private fun findFirstSpace(file: File): Space? {
        return freeSpace.find { it.length >= file.length && it.end < file.start }
    }

    /**
     * Move file into empty location
     */
    private fun moveFile(file: File, space: Space) {
        // Remove file from old location
        (file.start..file.end).forEach {
            filesystem[it] = null
        }

        // Move file into new location
        (space.start until space.start + file.length).forEach {
            filesystem[it] = file.id
        }
        val remaining = space.length - file.length
        if (remaining > 0) {
            space.start += file.length
        } else {
            freeSpace.remove(space)
        }
    }

    fun defragment() {

        for (file in fileIndex.reversed()) {
            val space = findFirstSpace(file)
            if (space == null) continue
            moveFile(file, space)
        }
    }

    val checksum
        get() = filesystem
            .mapIndexedNotNull { i, k -> k?.let { i * it } }
            .sumOf(Int::toLong)
}

