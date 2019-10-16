/*
    Copyright (C) 2017 Aseman Team
    http://aseman.co

    AsemanQtTools is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    AsemanQtTools is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package land.aseman.android;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.database.Cursor;
import android.graphics.Rect ;
import android.util.Log;
import java.util.Iterator;

import land.aseman.android.AsemanActivity;

public class AsemanMultimedia
{
    private static boolean implemented = false;

    public String getAllMusics() {
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        String[] projection = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.YEAR,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ALBUM_KEY,
            MediaStore.Audio.Media.ARTIST_KEY
        };

        Cursor cursor;
        try {
            cursor = AsemanActivity.getActivityInstance().managedQuery(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null);
        } catch (Exception e) {
            return "";
        }

        String result = "[";
        boolean firstItem = true;
        while(cursor.moveToNext()){
            if (!firstItem) {
                result += ",";
            } else {
                firstItem = false;
            }
            result += "{";
            result += "\"id\":\"" + cursor.getString(0) + "\",";
            result += "\"artist\":\"" + cursor.getString(1) + "\",";
            result += "\"album\":\"" + cursor.getString(2) + "\",";
            result += "\"art\":\"" + cursor.getString(3) + "\",";
            result += "\"title\":\"" + cursor.getString(4) + "\",";
            result += "\"data\":\"" + cursor.getString(5) + "\",";
            result += "\"displayName\":\"" + cursor.getString(6) + "\",";
            result += "\"duration\":\"" + cursor.getString(7) + "\",";
            result += "\"albumKey\":\"" + cursor.getString(8) + "\",";
            result += "\"artistKey\":\"" + cursor.getString(9) + "\"}";
        }

        result += "]";
        return result;
    }

    public String getAllAlbums() {
        String selection = MediaStore.Audio.Albums._ID + " != 0";

        String[] projection = {
            MediaStore.Audio.Albums._ID,
            MediaStore.Audio.Albums.ARTIST,
            MediaStore.Audio.Albums.ALBUM,
            MediaStore.Audio.Albums.ALBUM_ART,
            MediaStore.Audio.Albums.NUMBER_OF_SONGS,
            MediaStore.Audio.Albums.ARTIST_ID,
            MediaStore.Audio.Albums.ALBUM_KEY
        };

        Cursor cursor;
        try {
            cursor = AsemanActivity.getActivityInstance().managedQuery(
                MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null);
        } catch (Exception e) {
            return "";
        }

        String result = "[";
        boolean firstItem = true;
        while(cursor.moveToNext()){
            if (!firstItem) {
                result += ",";
            } else {
                firstItem = false;
            }
            result += "{";
            result += "\"id\":\"" + cursor.getString(0) + "\",";
            result += "\"artist\":\"" + cursor.getString(1) + "\",";
            result += "\"album\":\"" + cursor.getString(2) + "\",";
            result += "\"art\":\"" + cursor.getString(3) + "\",";
            result += "\"songs\":\"" + cursor.getString(4) + "\",";
            result += "\"artistId\":\"" + cursor.getString(5) + "\",";
            result += "\"albumKey\":\"" + cursor.getString(6) + "\"}";
        }

        result += "]";
        return result;
    }

    public boolean setImplemented(boolean stt) {
        implemented = stt;
        return true;
    }
}
