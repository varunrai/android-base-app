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

package org.zulu.apps.sgx.test.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.zulu.apps.sgx.data.model.Name;
import org.zulu.apps.sgx.data.model.Profile;
import org.zulu.apps.sgx.data.model.Ribot;

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
public class TestDataFactory {

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    public static Ribot makeRibot(String uniqueSuffix) {
        return new Ribot(makeProfile(uniqueSuffix));
    }

    public static List<Ribot> makeListRibots(int number) {
        List<Ribot> ribots = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            ribots.add(makeRibot(String.valueOf(i)));
        }
        return ribots;
    }

    public static Profile makeProfile(String uniqueSuffix) {
        Profile profile = new Profile();
        profile.email = "email" + uniqueSuffix + "@ribot.co.uk";
        profile.name = makeName(uniqueSuffix);
        profile.dateOfBirth = new Date();
        profile.hexColor = "#0066FF";
        profile.avatar = "http://api.ribot.io/images/" + uniqueSuffix;
        profile.bio = randomUuid();
        return profile;
    }

    public static Name makeName(String uniqueSuffix) {
        Name name = new Name();
        name.first = "Name-" + uniqueSuffix;
        name.last = "Surname-" + uniqueSuffix;
        return name;
    }

}