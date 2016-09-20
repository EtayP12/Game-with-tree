package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.*
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

class MyGdxGame : ApplicationAdapter() {
    internal lateinit var batch: SpriteBatch
    internal lateinit var img: Texture
    internal lateinit var font: BitmapFont
    internal lateinit var music: Music
    internal lateinit var shapeRenderer: ShapeRenderer
    internal lateinit var sprite: Sprite
    internal lateinit var anima1: Animation
    internal lateinit var anima2: Animation
    internal lateinit var cur_frame: TextureRegion
    internal lateinit var walk: Array<TextureRegion>
    private var pos = Vector2(0f, 0f)
    private var diraction = "Right"
    var stateTime = 0f

    lateinit var anima_manager: AnimationManager

    override fun create() {
        batch = SpriteBatch()
        img = Texture("D:/programming/core/assets/sprite1.png")
        font = BitmapFont()
        music = Gdx.audio.newMusic(Gdx.files.absolute("C:/Users/Etay/Music/fullmetal alchemist brotherhood opening - again193.mp3"))
        shapeRenderer = ShapeRenderer()
        cur_frame = TextureRegion(img,0,0,16,32)
        //walk = arrayOf(TextureRegion(img,0,0,16,32), TextureRegion(img,25,0,16,32), TextureRegion(img,49,0,16,32), TextureRegion(img,82,0,16,32))
//        sprite = Sprite(regions)
        var bla: Array<TextureRegion> = arrayOf(TextureRegion(img,0,0,16,32), TextureRegion(img,25,0,24,32), TextureRegion(img,49,0,24,32),
                TextureRegion(img,82,0,24,32), TextureRegion(img,114,0,24,32), TextureRegion(img,139,0,24,32),
                TextureRegion(img,169,0,24,32))

        anima1 = Animation(0.25f,   *bla)
        anima2 = Animation(0.25f,   TextureRegion(img,1,40,16,32), TextureRegion(img,25,40,17,32), TextureRegion(img,49,40,24,32),
                                    TextureRegion(img,82,40,24,32), TextureRegion(img,110,40,18,32), TextureRegion(img,135,40,23,32),
                                    TextureRegion(img,167,40,20,32))
        anima_manager = AnimationManager("D:/programming/core/assets/newSpriteSheet.sprites")
    }
    fun UpdateAnimation (direction: String){
        var anima = anima_manager.animations[direction]
        if(anima != null) {
            cur_frame = anima.getKeyFrame(stateTime, true) }
        stateTime += Gdx.graphics.deltaTime
    }
    fun move() {
        val speed = 200
        val temp = speed * Gdx.graphics.deltaTime
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)&& Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            UpdateAnimation("DownLeft")
            pos.y -= temp/Math.sqrt(2.0).toFloat()
            pos.x -= temp/Math.sqrt(2.0).toFloat()
    }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)&& Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            UpdateAnimation("DownRight")
            pos.y -= temp/Math.sqrt(2.0).toFloat()
            pos.x += temp/Math.sqrt(2.0).toFloat()
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.UP)&& Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            UpdateAnimation("UpRight")
            pos.y += temp/Math.sqrt(2.0).toFloat()
            pos.x += temp/Math.sqrt(2.0).toFloat()
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.UP)&& Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            UpdateAnimation("UpLeft")
            pos.y += temp/Math.sqrt(2.0).toFloat()
            pos.x -= temp/Math.sqrt(2.0).toFloat()
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            UpdateAnimation("Left")
            pos.x -= temp
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            UpdateAnimation("Right")
            pos.x += temp
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            UpdateAnimation("Up")
            pos.y += temp
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            UpdateAnimation("Down")
            pos.y -= temp
        }
        if (pos.x>500f)
            pos.x=0f
        if (pos.x<0)
            pos.x=500f
        if (pos.y>500f)
            pos.y=0f
        if (pos.y<0f)
            pos.y=500f
        if (!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
            stateTime = 0f
            if (diraction=="Right")
                cur_frame = anima1.getKeyFrame(stateTime, true)
            if (diraction=="Left")
                cur_frame = anima2.getKeyFrame(stateTime, true)
        }
        if (Gdx.input.isTouched) {
            pos.x = Gdx.input.getX().toFloat()
            pos.y = -Gdx.input.getY().toFloat() + 500
        }
    }
    override fun render() {
        move()
//        music.play()
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        //batch.draw(img, 0f, 0f)
//        if (Gdx.input.isTouched)
//        font.draw(batch, "Hello, world!", 250f, 250f)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.identity();
        //shapeRenderer.translate(20f, 12f, 0f);
        //shapeRenderer.rotate(1f, 0f, 0f, 90f);
        shapeRenderer.setColor(0f,0f,0f,1f);
//        val width = 500f;
//        val height = 500f;
//        shapeRenderer.circle(pos.x, pos.y, 50f);
        shapeRenderer.end();
//        sprite.setPosition(pos.x, pos.y)
//        sprite.draw(batch)
        batch.draw(cur_frame, pos.x, pos.y)
        batch.end()
    }

    override fun dispose() {
        batch.dispose()
        img.dispose()
        font.dispose()
        music.dispose()

    }
}
