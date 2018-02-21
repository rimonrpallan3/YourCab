package com.voyager.sayaradriver.tabfragment.profiletabfragment.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 09-Feb-18.
 */

public class Documents implements Parcelable {

    String documentName;
    String documentType;

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.documentName);
        dest.writeString(this.documentType);
    }

    public Documents() {
    }

    protected Documents(Parcel in) {
        this.documentName = in.readString();
        this.documentType = in.readString();
    }

    public static final Creator<Documents> CREATOR = new Creator<Documents>() {
        @Override
        public Documents createFromParcel(Parcel source) {
            return new Documents(source);
        }

        @Override
        public Documents[] newArray(int size) {
            return new Documents[size];
        }
    };
}
