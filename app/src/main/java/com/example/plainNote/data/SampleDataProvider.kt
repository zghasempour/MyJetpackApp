package com.example.plainNote.data

import java.util.Date

class SampleDataProvider {

    companion object {
        private val sampleText1 = "A simple note"
        private val sampleText2 = "A note a\n line feed"
        private val sampleText3 = """
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus in dictum urna. Morbi metus tellus, consectetur et ultrices eu, feugiat accumsan neque. Donec non felis risus. Phasellus fermentum nibh sit amet nisl bibendum cursus. Nunc bibendum neque vel ultrices rhoncus. Maecenas placerat, tellus a varius sagittis, tortor mi fringilla nisl, nec laoreet sem ex tincidunt leo. Etiam nunc metus, laoreet nec velit ut, mollis posuere felis. Integer sollicitudin elit dui, ut posuere risus efficitur eu. Donec eleifend sapien mollis purus aliquam, eget convallis leo egestas.

            In urna justo, bibendum et elementum eget, vestibulum quis lectus. Aliquam aliquet faucibus leo, at laoreet metus facilisis ut. Curabitur blandit cursus justo, vel ornare ipsum aliquet nec. Nam quis enim eu enim porttitor sodales. Vestibulum non odio justo. Morbi ut lectus eget turpis fermentum efficitur ut vel sem. Aliquam consectetur, dolor eu commodo eleifend, dui orci bibendum diam, at mollis nisi est id tortor. Aliquam ut turpis at eros ultrices egestas non ut augue. Duis a faucibus ligula. Pellentesque consectetur enim in ultrices ultricies. Quisque laoreet lobortis sapien, quis pellentesque sapien placerat non. Phasellus feugiat nisl risus, id congue sem pharetra ac. Suspendisse potenti. Aliquam blandit est ipsum, vitae elementum sapien fermentum quis.

        """.trimIndent()


        private fun getDate(diff: Long): Date {
            return Date(Date().time + diff)
        }

        fun getNotes() = arrayListOf(
            NoteEntity(getDate(0), sampleText1),
            NoteEntity(getDate(1), sampleText2),
            NoteEntity(getDate(2), sampleText3)
        )
    }
}