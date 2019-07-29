package ar.com.wolox.androidtechnicalinterview.models

import ar.com.wolox.android_technical_interview.models.Images
import com.google.gson.annotations.SerializedName
import com.orm.SugarRecord
import com.orm.dsl.Table


/**
 * MIT License
 *
 * Copyright (c) 2019 Wolox S.A
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 */

@Table
class Gif() {

    companion object {
        fun listAll(withRelationship: Boolean = false) : List<Gif> {
            val gifs = SugarRecord.listAll(Gif::class.java)
            if (withRelationship) {
                gifs.forEach { it.images = SugarRecord.findById(Images::class.java, it.id) }
            }
            return gifs
        }
    }

    @SerializedName("db_id")
    val id: Long? = null

    @SerializedName("id")
    lateinit var aid : String

    var images : Images? = null
    lateinit var title: String
    lateinit var url: String

}