package com.voyager.sayaradriver.tabfragment.profiletabfragment.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 09-Feb-18.
 */

public class ProfileModel implements Parcelable {

    String driverName;
    String driverPhone;
    String driverEmail;
    int driverAcno;
    String driverCarRegNo;
    String driverCarTaxExpiryDate;
    String driverCarInsuranceExpiryDate;
    String driverCarPollutionExpiryDate;
    String driverCarOwner;
    String driverCity;
    String driverCountry;
    String driverCpr;
    String driverPhoto;
    int driverId;
    String driverRating;
    String languagesKnown;
    String carPhoto;
    String carName;

    List<Documents> documents;


    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public int getDriverAcno() {
        return driverAcno;
    }

    public void setDriverAcno(int driverAcno) {
        this.driverAcno = driverAcno;
    }

    public String getDriverCarRegNo() {
        return driverCarRegNo;
    }

    public void setDriverCarRegNo(String driverCarRegNo) {
        this.driverCarRegNo = driverCarRegNo;
    }

    public String getDriverCarTaxExpiryDate() {
        return driverCarTaxExpiryDate;
    }

    public void setDriverCarTaxExpiryDate(String driverCarTaxExpiryDate) {
        this.driverCarTaxExpiryDate = driverCarTaxExpiryDate;
    }

    public String getDriverCarInsuranceExpiryDate() {
        return driverCarInsuranceExpiryDate;
    }

    public void setDriverCarInsuranceExpiryDate(String driverCarInsuranceExpiryDate) {
        this.driverCarInsuranceExpiryDate = driverCarInsuranceExpiryDate;
    }

    public String getDriverCarPollutionExpiryDate() {
        return driverCarPollutionExpiryDate;
    }

    public void setDriverCarPollutionExpiryDate(String driverCarPollutionExpiryDate) {
        this.driverCarPollutionExpiryDate = driverCarPollutionExpiryDate;
    }

    public String getDriverCarOwner() {
        return driverCarOwner;
    }

    public void setDriverCarOwner(String driverCarOwner) {
        this.driverCarOwner = driverCarOwner;
    }

    public String getLanguagesKnown() {
        return languagesKnown;
    }

    public void setLanguagesKnown(String languagesKnown) {
        this.languagesKnown = languagesKnown;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getDriverCity() {
        return driverCity;
    }

    public void setDriverCity(String driverCity) {
        this.driverCity = driverCity;
    }

    public String getDriverCountry() {
        return driverCountry;
    }

    public void setDriverCountry(String driverCountry) {
        this.driverCountry = driverCountry;
    }

    public String getDriverCpr() {
        return driverCpr;
    }

    public void setDriverCpr(String driverCpr) {
        this.driverCpr = driverCpr;
    }

    public String getDriverPhoto() {
        return driverPhoto;
    }

    public void setDriverPhoto(String driverPhoto) {
        this.driverPhoto = driverPhoto;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(String driverRating) {
        this.driverRating = driverRating;
    }

    public String getCarPhoto() {
        return carPhoto;
    }

    public void setCarPhoto(String carPhoto) {
        this.carPhoto = carPhoto;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public List<Documents> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Documents> documents) {
        this.documents = documents;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.driverName);
        dest.writeString(this.driverPhone);
        dest.writeString(this.driverEmail);
        dest.writeInt(this.driverAcno);
        dest.writeString(this.driverCarRegNo);
        dest.writeString(this.driverCarTaxExpiryDate);
        dest.writeString(this.driverCarInsuranceExpiryDate);
        dest.writeString(this.driverCarPollutionExpiryDate);
        dest.writeString(this.driverCarOwner);
        dest.writeString(this.driverCity);
        dest.writeString(this.driverCountry);
        dest.writeString(this.driverCpr);
        dest.writeString(this.driverPhoto);
        dest.writeInt(this.driverId);
        dest.writeString(this.driverRating);
        dest.writeString(this.languagesKnown);
        dest.writeString(this.carPhoto);
        dest.writeString(this.carName);
        dest.writeList(this.documents);
    }

    public ProfileModel() {
    }

    protected ProfileModel(Parcel in) {
        this.driverName = in.readString();
        this.driverPhone = in.readString();
        this.driverEmail = in.readString();
        this.driverAcno = in.readInt();
        this.driverCarRegNo = in.readString();
        this.driverCarTaxExpiryDate = in.readString();
        this.driverCarInsuranceExpiryDate = in.readString();
        this.driverCarPollutionExpiryDate = in.readString();
        this.driverCarOwner = in.readString();
        this.driverCity = in.readString();
        this.driverCountry = in.readString();
        this.driverCpr = in.readString();
        this.driverPhoto = in.readString();
        this.driverId = in.readInt();
        this.driverRating = in.readString();
        this.languagesKnown = in.readString();
        this.carPhoto = in.readString();
        this.carName = in.readString();
        this.documents = new ArrayList<Documents>();
        in.readList(this.documents, Documents.class.getClassLoader());
    }

    public static final Creator<ProfileModel> CREATOR = new Creator<ProfileModel>() {
        @Override
        public ProfileModel createFromParcel(Parcel source) {
            return new ProfileModel(source);
        }

        @Override
        public ProfileModel[] newArray(int size) {
            return new ProfileModel[size];
        }
    };

    public static Creator<ProfileModel> getCREATOR() {
        return CREATOR;
    }
}
