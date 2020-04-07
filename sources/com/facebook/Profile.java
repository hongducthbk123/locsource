package com.facebook;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.internal.ImageRequest;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.GraphMeRequestWithCacheCallback;
import com.facebook.internal.Validate;
import org.json.JSONException;
import org.json.JSONObject;

public final class Profile implements Parcelable {
    public static final Creator<Profile> CREATOR = new Creator() {
        public Profile createFromParcel(Parcel source) {
            return new Profile(source);
        }

        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
    private static final String FIRST_NAME_KEY = "first_name";
    private static final String ID_KEY = "id";
    private static final String LAST_NAME_KEY = "last_name";
    private static final String LINK_URI_KEY = "link_uri";
    private static final String MIDDLE_NAME_KEY = "middle_name";
    private static final String NAME_KEY = "name";
    private final String firstName;

    /* renamed from: id */
    private final String f944id;
    private final String lastName;
    private final Uri linkUri;
    private final String middleName;
    private final String name;

    public static Profile getCurrentProfile() {
        return ProfileManager.getInstance().getCurrentProfile();
    }

    public static void setCurrentProfile(Profile profile) {
        ProfileManager.getInstance().setCurrentProfile(profile);
    }

    public static void fetchProfileForCurrentAccessToken() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken == null) {
            setCurrentProfile(null);
        } else {
            Utility.getGraphMeRequestWithCacheAsync(accessToken.getToken(), new GraphMeRequestWithCacheCallback() {
                public void onSuccess(JSONObject userInfo) {
                    String id = userInfo.optString("id");
                    if (id != null) {
                        String link = userInfo.optString("link");
                        Profile.setCurrentProfile(new Profile(id, userInfo.optString(Profile.FIRST_NAME_KEY), userInfo.optString(Profile.MIDDLE_NAME_KEY), userInfo.optString(Profile.LAST_NAME_KEY), userInfo.optString("name"), link != null ? Uri.parse(link) : null));
                    }
                }

                public void onFailure(FacebookException error) {
                }
            });
        }
    }

    public Profile(String id, @Nullable String firstName2, @Nullable String middleName2, @Nullable String lastName2, @Nullable String name2, @Nullable Uri linkUri2) {
        Validate.notNullOrEmpty(id, "id");
        this.f944id = id;
        this.firstName = firstName2;
        this.middleName = middleName2;
        this.lastName = lastName2;
        this.name = name2;
        this.linkUri = linkUri2;
    }

    public Uri getProfilePictureUri(int width, int height) {
        return ImageRequest.getProfilePictureUri(this.f944id, width, height);
    }

    public String getId() {
        return this.f944id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getName() {
        return this.name;
    }

    public Uri getLinkUri() {
        return this.linkUri;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Profile)) {
            return false;
        }
        Profile o = (Profile) other;
        if (!this.f944id.equals(o.f944id) || this.firstName != null) {
            if (!this.firstName.equals(o.firstName) || this.middleName != null) {
                if (!this.middleName.equals(o.middleName) || this.lastName != null) {
                    if (!this.lastName.equals(o.lastName) || this.name != null) {
                        if (!this.name.equals(o.name) || this.linkUri != null) {
                            return this.linkUri.equals(o.linkUri);
                        }
                        if (o.linkUri != null) {
                            return false;
                        }
                        return true;
                    } else if (o.name != null) {
                        return false;
                    } else {
                        return true;
                    }
                } else if (o.lastName != null) {
                    return false;
                } else {
                    return true;
                }
            } else if (o.middleName != null) {
                return false;
            } else {
                return true;
            }
        } else if (o.firstName != null) {
            return false;
        } else {
            return true;
        }
    }

    public int hashCode() {
        int result = this.f944id.hashCode() + 527;
        if (this.firstName != null) {
            result = (result * 31) + this.firstName.hashCode();
        }
        if (this.middleName != null) {
            result = (result * 31) + this.middleName.hashCode();
        }
        if (this.lastName != null) {
            result = (result * 31) + this.lastName.hashCode();
        }
        if (this.name != null) {
            result = (result * 31) + this.name.hashCode();
        }
        if (this.linkUri != null) {
            return (result * 31) + this.linkUri.hashCode();
        }
        return result;
    }

    /* access modifiers changed from: 0000 */
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", this.f944id);
            jsonObject.put(FIRST_NAME_KEY, this.firstName);
            jsonObject.put(MIDDLE_NAME_KEY, this.middleName);
            jsonObject.put(LAST_NAME_KEY, this.lastName);
            jsonObject.put("name", this.name);
            if (this.linkUri == null) {
                return jsonObject;
            }
            jsonObject.put(LINK_URI_KEY, this.linkUri.toString());
            return jsonObject;
        } catch (JSONException e) {
            return null;
        }
    }

    Profile(JSONObject jsonObject) {
        Uri uri = null;
        this.f944id = jsonObject.optString("id", null);
        this.firstName = jsonObject.optString(FIRST_NAME_KEY, null);
        this.middleName = jsonObject.optString(MIDDLE_NAME_KEY, null);
        this.lastName = jsonObject.optString(LAST_NAME_KEY, null);
        this.name = jsonObject.optString("name", null);
        String linkUriString = jsonObject.optString(LINK_URI_KEY, null);
        if (linkUriString != null) {
            uri = Uri.parse(linkUriString);
        }
        this.linkUri = uri;
    }

    private Profile(Parcel source) {
        this.f944id = source.readString();
        this.firstName = source.readString();
        this.middleName = source.readString();
        this.lastName = source.readString();
        this.name = source.readString();
        String linkUriString = source.readString();
        this.linkUri = linkUriString == null ? null : Uri.parse(linkUriString);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.f944id);
        dest.writeString(this.firstName);
        dest.writeString(this.middleName);
        dest.writeString(this.lastName);
        dest.writeString(this.name);
        dest.writeString(this.linkUri == null ? null : this.linkUri.toString());
    }
}
