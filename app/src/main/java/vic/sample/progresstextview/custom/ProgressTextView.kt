package vic.sample.progresstextview.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import vic.sample.progresstextview.R


class ProgressTextView : View {

    companion object {
        const val UNINITIALIZED_INT_VALUE = -1
        const val MAXIMUM_PROGRESS_VALUE = 100
        const val MINIMUM_PROGRESS_VALUE = 0
    }

    private var mText = ""

    /*
    * X and Y position of the text
    * */
    private var mPosX = UNINITIALIZED_INT_VALUE
    private var mPosY = UNINITIALIZED_INT_VALUE

    private lateinit var mTextPaint: Paint
    private lateinit var mInvertedTextPaint: Paint

    private val mRect = Rect()

    private var mMaxProgress = MAXIMUM_PROGRESS_VALUE
    private var mMinProgress = MINIMUM_PROGRESS_VALUE
    private var mCurrProgress = UNINITIALIZED_INT_VALUE

    private var mProgressIncrease = true

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context = context, attrs = attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        init(context = context, attrs = attrs)
    }

    /*
    * Init Attr
    * */
    private fun init(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressTextView, 0, 0);

        /*
        * Init the text paint
        * */
        mTextPaint = Paint()
        mTextPaint.color =
            typedArray.getColor(R.styleable.ProgressTextView_originalTextColor, Color.BLACK)
        mTextPaint.textSize = typedArray.getDimensionPixelSize(
            R.styleable.ProgressTextView_textSize,
            context.resources.getDimensionPixelSize(R.dimen.textDefaultSize)
        ).toFloat()
        mTextPaint.typeface = Typeface.defaultFromStyle(
            typedArray.getInteger(
                R.styleable.ProgressTextView_textTypeface,
                Typeface.defaultFromStyle(Typeface.NORMAL).style
            )
        )
        mTextPaint.textAlign = Paint.Align.CENTER
        mTextPaint.isLinearText = true
        mTextPaint.isAntiAlias = true

        mInvertedTextPaint = Paint(mTextPaint)
        mInvertedTextPaint.color =
            typedArray.getColor(R.styleable.ProgressTextView_invertedTextColor, Color.WHITE)

        /*
        * Init the maximum and minimum
        * */
        mMaxProgress =
            typedArray.getInteger(R.styleable.ProgressTextView_maxProgress, MAXIMUM_PROGRESS_VALUE)
        mMinProgress =
            typedArray.getInteger(R.styleable.ProgressTextView_minProgress, MINIMUM_PROGRESS_VALUE)
        mCurrProgress =
            typedArray.getInteger(R.styleable.ProgressTextView_progress, UNINITIALIZED_INT_VALUE)
        mProgressIncrease =
            typedArray.getBoolean(R.styleable.ProgressTextView_progressIncrease, true)

        /*
        * Set the text
        * */
        mText = typedArray.getString(R.styleable.ProgressTextView_text).toString()

        /*
        * Recycle the typedArray
        * */
        typedArray.recycle()
    }

    override fun draw(canvas: Canvas) {

        canvas.getClipBounds(mRect)

        /*
        * The position of the text.
        * */
        mPosX = width / 2
        mPosY = ((height / 2) - ((mTextPaint.descent() + mTextPaint.ascent()) / 2)).toInt()

        /*
        * Draw the text.
        * */
        if (mText.isNotEmpty())
            canvas.drawText(mText, mPosX.toFloat(), mPosY.toFloat(), mTextPaint)

        /*
        * Draw the background
        * */
        if (mMaxProgress > mMinProgress && mCurrProgress in mMinProgress..mMaxProgress) {

            val range = (mMaxProgress - mMinProgress)

            if (mProgressIncrease)
                mRect.right = mRect.width() * mCurrProgress / range
            else
                mRect.left = mRect.width() * mCurrProgress / range

            canvas.clipRect(mRect)
        }

        super.draw(canvas)

        /*
        * Draw the overlap text on the background
        * */
        if (mText.isNotEmpty()) {
            canvas.drawText(mText, mPosX.toFloat(), mPosY.toFloat(), mInvertedTextPaint)
        }
    }

    fun setProgress(percent: Int) {
        mCurrProgress = percent
        invalidate()
    }

    fun getProgress(): Int {
        return mCurrProgress
    }

    fun setMaxProgress(max: Int) {
        mMaxProgress = max
    }

    fun getMaxProgress(): Int {
        return mMaxProgress
    }

    fun setMinProgress(min: Int) {
        mMinProgress = min
    }

    fun getMinProgress(): Int {
        return mMinProgress
    }

    fun setText(str: String) {
        mText = str
    }

    fun getText(): String {
        return mText
    }

    fun setTextSize(size: Int) {
        mTextPaint.textSize = size.toFloat()
        mInvertedTextPaint.textSize = size.toFloat()
    }

}