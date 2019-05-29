package com.redbeemedia.enigma.exposureutils.models.season;

import com.redbeemedia.enigma.exposureutils.models.linked.ApiLinkedEntity;
import com.redbeemedia.enigma.exposureutils.models.localized.ApiLocalizedData;
import com.redbeemedia.enigma.exposureutils.models.tag.ApiTag;
import android.os.Parcel;
import android.util.JsonReader;
import android.os.Parcelable;
import java.util.List;
import com.redbeemedia.enigma.exposureutils.models.external.ApiExternalReference;
import com.redbeemedia.enigma.exposureutils.models.asset.ApiAsset;
import java.io.IOException;
import com.redbeemedia.enigma.core.util.JsonReaderUtil;


public class ApiSeason implements Parcelable {
    private List<ApiExternalReference> externalReferences;
    private List<ApiLinkedEntity> linkedEntities;
    private long episodeCount;
    private String created;
    private List<ApiLocalizedData> localized;
    private long startYear;
    private String availableDate;
    private long endYear;
    private String tvShowId;
    private List<ApiTag> tags;
    private String seasonId;
    private String season;
    private String publishedDate;
    private List<ApiAsset> episodes;
    private String changed;


    protected ApiSeason() {}//Protected constructor for Parcelable.Creator and Mocks

    public ApiSeason(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "externalReferences":
                    this.externalReferences = JsonReaderUtil.readArray(jsonReader, ApiExternalReference.class);
                    break;
                case "linkedEntities":
                    this.linkedEntities = JsonReaderUtil.readArray(jsonReader, ApiLinkedEntity.class);
                    break;
                case "episodeCount":
                    this.episodeCount = jsonReader.nextLong();
                    break;
                case "created":
                    this.created = jsonReader.nextString();
                    break;
                case "localized":
                    this.localized = JsonReaderUtil.readArray(jsonReader, ApiLocalizedData.class);
                    break;
                case "startYear":
                    this.startYear = jsonReader.nextLong();
                    break;
                case "availableDate":
                    this.availableDate = jsonReader.nextString();
                    break;
                case "endYear":
                    this.endYear = jsonReader.nextLong();
                    break;
                case "tvShowId":
                    this.tvShowId = jsonReader.nextString();
                    break;
                case "tags":
                    this.tags = JsonReaderUtil.readArray(jsonReader, ApiTag.class);
                    break;
                case "seasonId":
                    this.seasonId = jsonReader.nextString();
                    break;
                case "season":
                    this.season = jsonReader.nextString();
                    break;
                case "publishedDate":
                    this.publishedDate = jsonReader.nextString();
                    break;
                case "episodes":
                    this.episodes = JsonReaderUtil.readArray(jsonReader, ApiAsset.class);
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


    public List<ApiExternalReference> getExternalReferences() {
        return this.externalReferences;
    }

    public List<ApiLinkedEntity> getLinkedEntities() {
        return this.linkedEntities;
    }

    public long getEpisodeCount() {
        return this.episodeCount;
    }

    public String getCreated() {
        return this.created;
    }

    public List<ApiLocalizedData> getLocalized() {
        return this.localized;
    }

    public long getStartYear() {
        return this.startYear;
    }

    public String getAvailableDate() {
        return this.availableDate;
    }

    public long getEndYear() {
        return this.endYear;
    }

    public String getTvShowId() {
        return this.tvShowId;
    }

    public List<ApiTag> getTags() {
        return this.tags;
    }

    public String getSeasonId() {
        return this.seasonId;
    }

    public String getSeason() {
        return this.season;
    }

    public String getPublishedDate() {
        return this.publishedDate;
    }

    public List<ApiAsset> getEpisodes() {
        return this.episodes;
    }

    public String getChanged() {
        return this.changed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<ApiSeason> CREATOR = new Parcelable.Creator<ApiSeason>() {
        public ApiSeason createFromParcel(Parcel in) {
            ApiSeason object = new ApiSeason();
            object.externalReferences = in.createTypedArrayList(ApiExternalReference.CREATOR);
            object.linkedEntities = in.createTypedArrayList(ApiLinkedEntity.CREATOR);
            object.episodeCount = in.readLong();
            object.created = in.readString();
            object.localized = in.createTypedArrayList(ApiLocalizedData.CREATOR);
            object.startYear = in.readLong();
            object.availableDate = in.readString();
            object.endYear = in.readLong();
            object.tvShowId = in.readString();
            object.tags = in.createTypedArrayList(ApiTag.CREATOR);
            object.seasonId = in.readString();
            object.season = in.readString();
            object.publishedDate = in.readString();
            object.episodes = in.createTypedArrayList(ApiAsset.CREATOR);
            object.changed = in.readString();
            return object;
        }

        public ApiSeason[] newArray(int size) {
            return new ApiSeason[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(externalReferences);
        dest.writeTypedList(linkedEntities);
        dest.writeLong(episodeCount);
        dest.writeString(created);
        dest.writeTypedList(localized);
        dest.writeLong(startYear);
        dest.writeString(availableDate);
        dest.writeLong(endYear);
        dest.writeString(tvShowId);
        dest.writeTypedList(tags);
        dest.writeString(seasonId);
        dest.writeString(season);
        dest.writeString(publishedDate);
        dest.writeTypedList(episodes);
        dest.writeString(changed);
    }
}
