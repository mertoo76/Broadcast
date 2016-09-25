package gun.mert.broadcast;

/**
 * Created by user on 24.09.2016.
 */
/* package */ final class MovingAverage
{
    //broadcast
    private final int mNumValues;
    private final long[] mValues;
    private int mEnd = 0;
    private int mLength = 0;
    private long mSum = 0L;

    /* package */ MovingAverage(final int numValues)
    {
        super();
        mNumValues = numValues;
        mValues = new long[numValues];
    } // constructor()

    /* package */ void update(final long value)
    {
        mSum -= mValues[mEnd];
        mValues[mEnd] = value;
        mEnd = (mEnd + 1) % mNumValues;
        if (mLength < mNumValues)
        {
            mLength++;
        } // if
        mSum += value;
    } // update(long)

    /* package */ double getAverage()
    {
        return mSum / (double) mLength;
    } // getAverage()

} // class MovingAverage
