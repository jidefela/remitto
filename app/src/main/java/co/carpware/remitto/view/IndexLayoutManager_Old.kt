package co.carpware.remitto.view

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import co.carpware.remitto.R

class IndexLayoutManager_Old : FrameLayout {

    private var stickyIndex: TextView? = null
    private var indexList: RecyclerView? = null
    private var mScrollListener: RecyclerView.OnScrollListener? = null

    init {
         mScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                update(recyclerView!!, dx.toFloat(), dy.toFloat())
            }
        }
    }


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onFinishInflate() {
        super.onFinishInflate()
        LayoutInflater.from(context).inflate(R.layout.sticky_section_layout, this, true)
        init()
    }

    private fun init() {
        stickyIndex = findViewById(R.id.section_title) as TextView
    }

    fun attach(pRecyclerView: RecyclerView) {
        indexList = pRecyclerView
        pRecyclerView.addOnScrollListener(mScrollListener)
        update(pRecyclerView, 0f, 0f)
    }

    fun dettach(pRecyclerView: RecyclerView) {
        pRecyclerView.removeOnScrollListener(mScrollListener)
    }

    fun refresh() {
        update(indexList!!, 0f, 0f)
    }

    private fun isHeader(prev: TextView, act: TextView): Boolean {
        return (isSameChar(prev.text[0], act.text[0]))
    }

    private fun isSameChar(prev: Char, curr: Char): Boolean {
        return (Character.toLowerCase(prev) == Character.toLowerCase(curr))
    }

    private fun updatePosBasedOnReferenceList(referenceRv: RecyclerView) {
        val firstVisibleView = referenceRv.getChildAt(0)
        val actual = referenceRv.getChildPosition(firstVisibleView)
        (indexList!!.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(actual, firstVisibleView.top + 0)
    }

    private fun update(referenceList: RecyclerView, dx: Float, dy: Float) {
        if (indexList != null && indexList!!.childCount > 2) {
            show()
            updatePosBasedOnReferenceList(referenceList)

            val firstVisibleView = indexList!!.getChildAt(0)
            val secondVisibleView = indexList!!.getChildAt(1)


            val firstRowIndex = firstVisibleView.findViewById(R.id.section_title) as TextView
            val secondRowIndex = secondVisibleView.findViewById(R.id.section_title) as TextView

            val visibleRange = indexList!!.childCount
            val actual = indexList!!.getChildPosition(firstVisibleView)
            val next = actual + 1
            val last = actual + visibleRange

            // RESET STICKY LETTER INDEX
            stickyIndex!!.text = getIndexContext(firstRowIndex).toString().toUpperCase()
            stickyIndex!!.visibility = TextView.VISIBLE
            ViewCompat.setAlpha(firstRowIndex, 1f)

            if (dy > 0) {
                // USER SCROLLING DOWN THE RecyclerView
                if (next <= last) {
                    if (isHeader(firstRowIndex, secondRowIndex)!!) {
                        stickyIndex!!.visibility = TextView.INVISIBLE
                        firstRowIndex.visibility = TextView.VISIBLE
                        ViewCompat.setAlpha(firstRowIndex, 1 - Math.abs(ViewCompat.getY(firstVisibleView)) / firstRowIndex.height)
                        secondRowIndex.visibility = TextView.VISIBLE
                    } else {
                        firstRowIndex.visibility = TextView.INVISIBLE
                        stickyIndex!!.visibility = TextView.VISIBLE
                    }
                }
            } else if (dy < 0) {
                // USER IS SCROLLING UP THE RecyclerVIew
                if (next <= last) {
                    // RESET FIRST ROW STATE
                    firstRowIndex.visibility = TextView.INVISIBLE
                    if ((isHeader(firstRowIndex, secondRowIndex) || getIndexContext(firstRowIndex) != getIndexContext(secondRowIndex)) && isHeader(firstRowIndex, secondRowIndex)) {
                        stickyIndex!!.visibility = TextView.INVISIBLE
                        firstRowIndex.visibility = TextView.VISIBLE
                        ViewCompat.setAlpha(firstRowIndex, 1 - Math.abs(ViewCompat.getY(firstVisibleView) / firstRowIndex.height))
                        secondRowIndex.visibility = TextView.VISIBLE
                    } else {
                        secondRowIndex.visibility = TextView.INVISIBLE
                    }
                }
            }

            if (stickyIndex!!.visibility == TextView.VISIBLE) {
                firstRowIndex.visibility = TextView.INVISIBLE
            }
        } else {
            hide()
        }
    }

    private fun getIndexContext(index: TextView): Char {
        return  index.text[0]
    }

    fun show() {
        stickyIndex!!.visibility = View.VISIBLE
    }

    fun hide() {
        stickyIndex!!.visibility = View.GONE
    }
}