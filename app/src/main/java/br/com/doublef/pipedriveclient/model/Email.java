package br.com.doublef.pipedriveclient.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Email extends RealmObject implements Parcelable{

    private String label;
    private String value;
    private boolean primary;

    public Email(){}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.label);
        dest.writeString(this.value);
        dest.writeByte(this.primary ? (byte) 1 : (byte) 0);
    }

    protected Email(Parcel in) {
        this.label = in.readString();
        this.value = in.readString();
        this.primary = in.readByte() != 0;
    }

    public static final Creator<Email> CREATOR = new Creator<Email>() {
        @Override
        public Email createFromParcel(Parcel source) {
            return new Email(source);
        }

        @Override
        public Email[] newArray(int size) {
            return new Email[size];
        }
    };
}
