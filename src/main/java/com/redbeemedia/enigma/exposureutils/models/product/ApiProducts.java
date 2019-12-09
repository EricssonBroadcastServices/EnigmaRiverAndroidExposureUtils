package com.redbeemedia.enigma.exposureutils.models.product;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;

import com.redbeemedia.enigma.core.util.JsonReaderUtil;

import java.io.IOException;
import java.util.List;


public class ApiProducts implements Parcelable {
    private List<ApiProduct> products;


    protected ApiProducts() {}//Protected constructor for Parcelable.Creator and Mocks

    public ApiProducts(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "products":
                    this.products = JsonReaderUtil.readArray(jsonReader, ApiProduct.class);
                    break;
                default:
                    jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
    }


    public List<ApiProduct> getProducts() {
        return this.products;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<ApiProducts> CREATOR = new Parcelable.Creator<ApiProducts>() {
        public ApiProducts createFromParcel(Parcel in) {
            ApiProducts object = new ApiProducts();
            object.products = in.createTypedArrayList(ApiProduct.CREATOR);
            return object;
        }

        public ApiProducts[] newArray(int size) {
            return new ApiProducts[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(products);
    }
}
