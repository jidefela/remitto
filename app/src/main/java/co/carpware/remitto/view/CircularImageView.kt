package co.carpware.remitto.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.Shader.TileMode
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView


class CircularImageView : ImageView {

    private var borderWidth: Int = 0
    private var image: Bitmap? = null
    private var paint: Paint? = null
    private var paintBorder: Paint? = null
    private var shader: BitmapShader? = null
    private var viewHeight: Int = 0
    private var viewWidth: Int = 0

    constructor(context: Context) : super(context) {
        this.borderWidth = 8
        setup()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.borderWidth = 8
        setup()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        this.borderWidth = 8
        setup()
    }

    private fun setup() {
        this.paint = Paint()
        this.paint!!.isAntiAlias = true
        this.paintBorder = Paint()
        setBorderColor(-1)
        this.paintBorder!!.isAntiAlias = true
    }

    fun setBorderWidth(borderWidth: Int) {
        this.borderWidth = borderWidth
        invalidate()
    }

    fun setBorderColor(borderColor: Int) {
        if (this.paintBorder != null) {
            this.paintBorder!!.color = borderColor
        }
        invalidate()
    }

    private fun loadBitmap() {
        val bitmapDrawable = drawable as BitmapDrawable
        if (bitmapDrawable != null) {
            this.image = bitmapDrawable.bitmap
        }
    }

    @SuppressLint("DrawAllocation")
    public override fun onDraw(canvas: Canvas) {
        loadBitmap()
        if (this.image != null) {
            this.shader = BitmapShader(Bitmap.createScaledBitmap(this.image, canvas.width, canvas.height, false), TileMode.CLAMP, TileMode.CLAMP)
            this.paint!!.shader = this.shader
            val circleCenter = this.viewWidth / 2
            canvas.drawCircle((this.borderWidth + circleCenter).toFloat(), (this.borderWidth + circleCenter).toFloat(), (this.borderWidth + circleCenter).toFloat(), this.paintBorder!!)
            canvas.drawCircle((this.borderWidth + circleCenter).toFloat(), (this.borderWidth + circleCenter).toFloat(), circleCenter.toFloat(), this.paint!!)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = measureWidth(widthMeasureSpec)
        val height = measureHeight(heightMeasureSpec, widthMeasureSpec)
        this.viewWidth = width - this.borderWidth * 2
        this.viewHeight = height - this.borderWidth * 2
        setMeasuredDimension(width, height)
    }

    private fun measureWidth(measureSpec: Int): Int {
        val specMode = View.MeasureSpec.getMode(measureSpec)
        val specSize = View.MeasureSpec.getSize(measureSpec)
        if (specMode == 1073741824) {
            return specSize
        }
        return this.viewWidth
    }

    private fun measureHeight(measureSpecHeight: Int, measureSpecWidth: Int): Int {
        val specMode = View.MeasureSpec.getMode(measureSpecHeight)
        val specSize = View.MeasureSpec.getSize(measureSpecHeight)
        if (specMode == 1073741824) {
            return specSize
        }
        return this.viewHeight
    }

}
