package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import java.util.*


/**
 * Created by Etay on 20-Sep-16.
 */
class AnimationManager {
    internal var animations: MutableMap<String, Animation> = HashMap()
    internal lateinit var cur_anima: Animation

    constructor(name: String) {
        val xml = com.badlogic.gdx.utils.XmlReader()
        val ProgressFileHandle = Gdx.files.absolute(name)
        val xml_element = xml.parse(ProgressFileHandle)
        print(xml_element.getAttribute("name"))
        val image = Texture(Gdx.files.absolute("D:/programming/core/assets/" + xml_element.getAttribute("name")))

        var count = xml_element.childCount - 1
        for(i in 0..count) {
            val iterator_level = xml_element.getChild(i)
            print(iterator_level.getAttribute("name"))
            val region: MutableList<TextureRegion> = ArrayList<TextureRegion>()

            var count = iterator_level.childCount - 1
            for(i in 0..count) {
                val frame = iterator_level.getChild(i)
                val x = frame.getAttribute("x").toInt()
                val y = frame.getAttribute("y").toInt()
                val w = frame.getAttribute("w").toInt()
                val h = frame.getAttribute("h").toInt()
                region.add(TextureRegion(image,x, y, w, h))
            }
            var anima: Animation = Animation(0.25f, *region.toTypedArray())
            animations.put(iterator_level.getAttribute("name"), anima)
        }
//        val iterator_level = xml_element.getChild(2)
//        print(iterator_level.getAttribute("name"))
//        while (iterator_level.hasNext()) {
//            val level_element = iterator_level.next()
//            print(level_element.getAttribute("name"))
//            val level_number = level_element.getAttribute("x")
//            val level_status = level_element.getAttribute("y")

//        }

    }
}
