/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.network;


import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

/**
 * Common class used by API responses.
 *
 * @param <T>
 * @author batman
 */
public class ApiResponse<T> {

    public String cmd;
    public int result;
    public String resultNote;
    public int pages;
    public int pageNo;
    public int totalRecordNum;
    public String queryTime;

    public int ver;
    public String note;
    @Nullable
    public String token;

    @Nullable
    public String code;

    @Nullable
    public String msg;
    @Nullable
    @SerializedName("detail")
    public T body;

    public boolean isSuccessful() {
        if (!TextUtils.isEmpty(resultNote) && resultNote.equals("0")) {
            return true;
        }
        return false;

    }
}
