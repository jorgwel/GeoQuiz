package book.geoquiz.geoquiz.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CheatedQuestion implements Parcelable {
    int mIndexOfCheatedQuestion;

    public CheatedQuestion(int indexOfCheatedQuestion) {
        mIndexOfCheatedQuestion = indexOfCheatedQuestion;
    }

    private CheatedQuestion(Parcel in) {
        mIndexOfCheatedQuestion = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mIndexOfCheatedQuestion);
    }

//    public void writeToParcel(Parcel out, int flags) {
////            out.writeString(color);
//    }

    public static final Parcelable.Creator<CheatedQuestion> CREATOR = new Parcelable.Creator<CheatedQuestion>() {
        public CheatedQuestion createFromParcel(Parcel in) {
            return new CheatedQuestion(in);
        }

        public CheatedQuestion[] newArray(int size) {
            return new CheatedQuestion[size];
        }
    };


}
