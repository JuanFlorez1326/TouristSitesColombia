package com.politecnico.TouristSitesColombia;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MediaUtils {

    public static void saveImageToGallery(Context context, Bitmap bitmap, String title) {
        OutputStream fos;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ContentResolver resolver = context.getContentResolver();
                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, title + ".jpg");
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
                contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/TurismoColombia");
                Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                fos = resolver.openOutputStream(imageUri);
            } else {
                String imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/TurismoColombia";
                File file = new File(imagesDir);
                if (!file.exists()) file.mkdir();
                File image = new File(imagesDir, title + ".jpg");
                fos = new FileOutputStream(image);
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            if (fos != null) fos.close();
            Toast.makeText(context, "Imagen guardada en la galería", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al guardar imagen", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public static void shareImage(Context context, Bitmap bitmap, String text) {
        try {
            File cachePath = new File(context.getCacheDir(), "images");
            cachePath.mkdirs();
            FileOutputStream stream = new FileOutputStream(cachePath + "/image_to_share.png");
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

            File imagePath = new File(context.getCacheDir(), "images");
            File newFile = new File(imagePath, "image_to_share.png");
            Uri contentUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", newFile);

            if (contentUri != null) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                shareIntent.setDataAndType(contentUri, context.getContentResolver().getType(contentUri));
                shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
                shareIntent.putExtra(Intent.EXTRA_TEXT, text);
                context.startActivity(Intent.createChooser(shareIntent, "Compartir imagen en..."));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}