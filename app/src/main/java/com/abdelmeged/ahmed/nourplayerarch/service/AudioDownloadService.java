package com.abdelmeged.ahmed.nourplayerarch.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.abdelmeged.ahmed.nourplayerarch.App.NourApp;
import com.abdelmeged.ahmed.nourplayerarch.R;
import com.abdelmeged.ahmed.nourplayerarch.db.entity.Sura;
import com.abdelmeged.ahmed.nourplayerarch.model.Download;
import com.abdelmeged.ahmed.nourplayerarch.utils.Constants;
import com.abdelmeged.ahmed.nourplayerarch.utils.FilesUtils;
import com.abdelmeged.ahmed.nourplayerarch.utils.QuranIndex;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by ahmed on 7/25/2017.
 */

public class AudioDownloadService extends IntentService {

    /**
     * Tag for debugging
     */
    private static final String LOG_TAG = AudioDownloadService.class.getSimpleName();

    @Inject
    NourService nourService;


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     * <p>
     * name Used to name the worker thread, important only for debugging.
     */
    public AudioDownloadService() {
        super("AudioDownloadService");
        NourApp.appComponent.inject(this);
    }

    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;
    private int totalFileSize;


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.cloud_download)
                .setContentTitle("Download")
                .setContentText("Downloading File")
                .setAutoCancel(true);
        notificationManager.notify(0, notificationBuilder.build());

        Sura sura = intent.getParcelableExtra(Constants.EXTRA_SURA_DOWNLOAD);
        if (sura != null) {
            initDownload(sura);
        }

    }

    private void initDownload(Sura sura) {
        Call<ResponseBody> request = nourService.downloadSuraAudio(sura.getDownloadUrl());
        try {
            downloadFile(request.execute().body(), sura.getQuranIndex());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void downloadFile(ResponseBody body, QuranIndex quranIndex) throws IOException {

        if (body != null) {
            int count;
            byte data[] = new byte[1024 * 4];
            long fileSize = body.contentLength();
            InputStream bis = new BufferedInputStream(body.byteStream(), 1024 * 8);
            File outputFile = new File(FilesUtils.Companion.getAudioDownloadDirectory(this), quranIndex.toString() + ".mp3");
            Log.e(LOG_TAG, outputFile.getAbsolutePath());
            OutputStream output = new FileOutputStream(outputFile);
            long total = 0;
            long startTime = System.currentTimeMillis();
            int timeCount = 1;
            while ((count = bis.read(data)) != -1) {

                total += count;
                totalFileSize = (int) (fileSize / (Math.pow(1024, 2)));
                double current = Math.round(total / (Math.pow(1024, 2)));

                int progress = (int) ((total * 100) / fileSize);

                long currentTime = System.currentTimeMillis() - startTime;

                Download download = new Download();
                download.setTotalFileSize(totalFileSize);

                if (currentTime > 1000 * timeCount) {

                    download.setCurrentFileSize((int) current);
                    download.setProgress(progress);
                    sendNotification(download);
                    timeCount++;
                }

                output.write(data, 0, count);
            }
            onDownloadComplete();
            output.flush();
            output.close();
            bis.close();

        }
    }

    private void sendNotification(Download download) {
        sendIntent(download);
        notificationBuilder.setProgress(100, download.getProgress(), false);
        notificationBuilder.setContentText("Downloading file " + download.getCurrentFileSize() + "/" + totalFileSize + " MB");
        notificationManager.notify(0, notificationBuilder.build());
    }

    private void sendIntent(Download download) {
        Intent intent = new Intent(Constants.MESSAGE_PROGRESS);
        intent.putExtra("download", download);
        LocalBroadcastManager.getInstance(AudioDownloadService.this).sendBroadcast(intent);
    }

    private void onDownloadComplete() {
        Download download = new Download();
        download.setProgress(100);
        sendIntent(download);

        notificationManager.cancel(0);
        notificationBuilder.setProgress(0, 0, false);
        notificationBuilder.setContentText("File Downloaded");
        notificationManager.notify(0, notificationBuilder.build());
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        notificationManager.cancel(0);
    }
}
