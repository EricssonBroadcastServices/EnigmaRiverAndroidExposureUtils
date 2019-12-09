package com.redbeemedia.enigma.exposureutils.models.product;

import android.os.Parcel;
import android.util.JsonReader;
import android.os.Parcelable;
import java.io.IOException;


public class ApiProduct implements Parcelable {
    private boolean entitlementRequired;
    private boolean hasAds;
    private String businessUnit;
    private boolean blocked;
    private String name;
    private String description;
    private String id;
    private boolean anonymousAllowed;
    private long priority;
    private String customer;
    private String changed;


    protected ApiProduct() {}//Protected constructor for Parcelable.Creator and Mocks

    public ApiProduct(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "entitlementRequired":
                    this.entitlementRequired = jsonReader.nextBoolean();
                    break;
                case "hasAds":
                    this.hasAds = jsonReader.nextBoolean();
                    break;
                case "businessUnit":
                    this.businessUnit = jsonReader.nextString();
                    break;
                case "blocked":
                    this.blocked = jsonReader.nextBoolean();
                    break;
                case "name":
                    this.name = jsonReader.nextString();
                    break;
                case "description":
                    this.description = jsonReader.nextString();
                    break;
                case "id":
                    this.id = jsonReader.nextString();
                    break;
                case "anonymousAllowed":
                    this.anonymousAllowed = jsonReader.nextBoolean();
                    break;
                case "priority":
                    this.priority = jsonReader.nextLong();
                    break;
                case "customer":
                    this.customer = jsonReader.nextString();
                    break;
                case "changed":
                    this.changed = jsonReader.nextString();
                    break;
                default:
                    jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
    }


    public boolean getEntitlementRequired() {
        return this.entitlementRequired;
    }

    public boolean getHasAds() {
        return this.hasAds;
    }

    public String getBusinessUnit() {
        return this.businessUnit;
    }

    public boolean getBlocked() {
        return this.blocked;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getId() {
        return this.id;
    }

    public boolean getAnonymousAllowed() {
        return this.anonymousAllowed;
    }

    public long getPriority() {
        return this.priority;
    }

    public String getCustomer() {
        return this.customer;
    }

    public String getChanged() {
        return this.changed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<ApiProduct> CREATOR = new Parcelable.Creator<ApiProduct>() {
        public ApiProduct createFromParcel(Parcel in) {
            ApiProduct object = new ApiProduct();
            object.entitlementRequired = (in.readInt() != 0);
            object.hasAds = (in.readInt() != 0);
            object.businessUnit = in.readString();
            object.blocked = (in.readInt() != 0);
            object.name = in.readString();
            object.description = in.readString();
            object.id = in.readString();
            object.anonymousAllowed = (in.readInt() != 0);
            object.priority = in.readLong();
            object.customer = in.readString();
            object.changed = in.readString();
            return object;
        }

        public ApiProduct[] newArray(int size) {
            return new ApiProduct[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(entitlementRequired ? 1 : 0);
        dest.writeInt(hasAds ? 1 : 0);
        dest.writeString(businessUnit);
        dest.writeInt(blocked ? 1 : 0);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(id);
        dest.writeInt(anonymousAllowed ? 1 : 0);
        dest.writeLong(priority);
        dest.writeString(customer);
        dest.writeString(changed);
    }
}
