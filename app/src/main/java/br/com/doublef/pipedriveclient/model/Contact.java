package br.com.doublef.pipedriveclient.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;

@Data
public class Contact extends RealmObject implements Parcelable {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private int id;

    @SerializedName("company_id")
    @Expose
    private int companyId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("owner_name")
    @Expose
    private String ownerName;

    @SerializedName("cc_email")
    @Expose
    private String ccEmail;

    @SerializedName("org_name")
    @Expose
    private String companyName;

    @SerializedName("email")
    @Expose
    private RealmList<Email> emailData;

    @SerializedName("phone")
    @Expose
    private RealmList<Phone> phoneData;

    public Contact() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.companyId);
        dest.writeString(this.name);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.ownerName);
        dest.writeString(this.ccEmail);
        dest.writeString(this.companyName);
        dest.writeList(this.emailData);
        dest.writeList(this.phoneData);
    }

    protected Contact(Parcel in) {
        this.id = in.readInt();
        this.companyId = in.readInt();
        this.name = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.ownerName = in.readString();
        this.ccEmail = in.readString();
        this.companyName = in.readString();
        this.emailData = new RealmList<>();
        this.phoneData = new RealmList<>();
        in.readList(this.emailData, Email.class.getClassLoader());
        in.readList(this.phoneData, Email.class.getClassLoader());
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ccEmail='" + ccEmail + '\'' +
                ", companyName='" + companyName + '\'' +
                ", emailData=" + emailData +
                ", phoneData=" + phoneData +
                '}';
    }
}
