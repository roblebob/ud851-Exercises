/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.example.android.background.sync;

import android.content.Context;
import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class WaterReminderFirebaseJobService extends JobService {
    // _TODO (3) WaterReminderFirebaseJobService should extend from JobService

    private AsyncTask mBackgroundTask;


    // _TODO (4) Override onStartJob
    public boolean onStartJob(final JobParameters jobParameters) {
        // _TODO (5) By default, jobs are executed on the main thread, so make an anonymous class extending
        //  AsyncTask called mBackgroundTask.
        mBackgroundTask = new AsyncTask() {
            // _TODO (6) Override doInBackground
            @Override protected Object doInBackground(Object[] objects) {
                Context context = WaterReminderFirebaseJobService.this;
                // _TODO (7) Use ReminderTasks to execute the new charging reminder task you made, use ...
                ReminderTasks .executeTask(context, ReminderTasks.ACTION_CHARGING_REMINDER);
                return null;
            }
            // _TODO (8) Override onPostExecute and call jobFinished. Pass the job parameters
            // and false to jobFinished. This will inform the JobManager that your job is done
            // and that you do not want to reschedule the job.
            @Override protected void onPostExecute( Object object) {
                super .onPostExecute( object);
                jobFinished( jobParameters, false);
            }
        };
        // _TODO (9) Execute the AsyncTask
        mBackgroundTask .execute();
        // _TODO (10) Return true
        return true;
    }



    // _TODO (11) Override onStopJob
    @Override public boolean onStopJob( JobParameters jobParameters) {
        // _TODO (12) If mBackgroundTask is valid, cancel it
        if( mBackgroundTask != null)   mBackgroundTask .cancel(true);
        // _TODO (13) Return true to signify the job should be retried
        return true;
    }









}
