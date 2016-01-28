// ---------------------------------------------------------------------
// Copyright 2016 Zulu Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
// ---------------------------------------------------------------------

package org.zulu.apps.sgx.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Name implements Parcelable {
    public String first;
    public String last;

    public Name() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;

        if (first != null ? !first.equals(name.first) : name.first != null) return false;
        return !(last != null ? !last.equals(name.last) : name.last != null);

    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (last != null ? last.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.first);
        dest.writeString(this.last);
    }

    protected Name(Parcel in) {
        this.first = in.readString();
        this.last = in.readString();
    }

    public static final Creator<Name> CREATOR = new Creator<Name>() {
        public Name createFromParcel(Parcel source) {
            return new Name(source);
        }

        public Name[] newArray(int size) {
            return new Name[size];
        }
    };
}
