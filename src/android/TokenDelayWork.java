/*
 * Copyright (C) 2018 The Android Open Source Project
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

package org.apache.cordova.firebase;

import android.content.ContentResolver;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.json.JSONArray;

public class TokenDelayWork extends Worker {
    
    /**
     * Creates an instance of the {@link Worker}.
     *
     * @param appContext   the application {@link Context}
     * @param workerParams the set of {@link WorkerParameters}
     */
    public TokenDelayWork(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    private static final String TAG = TokenDelayWork.class.getSimpleName();

    @NonNull
    @Override
    public Result doWork() {

        Data inputData = getInputData();
        int number = inputData.getInt("number", -1);
        Log.d(TAG, "doWork(): number: " + number);

        for (int i = number; i > 0; i--){
            Log.d(TAG, "Notification Message 카운트: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Result.failure();
            }
            //return Result.retry();
        }

        Data outPutData = new Data.Builder()
                .putInt("number", 15)
                .build();

        JSONArray providersJson = new JSONArray();
        FirebasePluginInstanceIDService.getToken();                

        return Result.success(outPutData);
    }
}
