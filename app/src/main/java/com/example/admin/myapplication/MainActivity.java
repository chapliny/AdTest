package com.example.admin.myapplication;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {

    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        test();

        mImageView = (ImageView)findViewById(R.id.iv_pic);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasMashroom()) {
                    XPermissionUtils.requestPermissions(MainActivity.this, XPermissionUtils.PHOTO, new String[]{Manifest
                                    .permission.READ_EXTERNAL_STORAGE}
                            , new XPermissionUtils.OnPermissionListener() {
                                @Override
                                public void onPermissionGranted() {
                                    chosePhoto(MainActivity.this, 1);

                                }

                                @Override
                                public void onPermissionDenied() {
                                }
                            });

                } else {
                    chosePhoto(MainActivity.this, 1);
                }
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasMashroom()) {
                    XPermissionUtils.requestPermissions(MainActivity.this, XPermissionUtils.CAMERA, new String[]{Manifest
                                    .permission.READ_EXTERNAL_STORAGE, Manifest
                                    .permission.CAMERA}
                            , new XPermissionUtils.OnPermissionListener() {
                                @Override
                                public void onPermissionGranted() {
                                    if (isCameraEnable()) {
                                        takePhoto(MainActivity.this, 2);
//                                        takePictureFromCamera();
                                    }
                                }

                                @Override
                                public void onPermissionDenied() {

                                }
                            });

                } else {
                    takePhoto(MainActivity.this, 2);
//                    takePictureFromCamera();


                }
            }
        });
    }

    private  void chosePhoto(Activity activity, int photoRequestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        ComponentName componentName = intent.resolveActivity(getPackageManager());
        if (componentName != null) {
            activity.startActivityForResult(intent, photoRequestCode);
        }

//        activity.startActivityForResult(intent, photoRequestCode);
    }


    public static boolean hasMashroom(){
        return Build.VERSION.SDK_INT >=  Build.VERSION_CODES.M;
    }

    public static boolean isCameraEnable() {
        boolean isCanUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
            Camera.Parameters mParameters = mCamera.getParameters(); //针对魅族手机
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            isCanUse = false;
        }

        if (mCamera != null) {
            try {
                mCamera.release();
            } catch (Exception e) {
                e.printStackTrace();
                return isCanUse;
            }
        }
        return isCanUse;
    }

    private void takePhoto(Activity activity, int cameraRequestCode) {

//        String pictureName = "headimg.jpg";
//        File mOutputImage = new File(getExternalCacheDir(), pictureName);
//        Uri imageUri;
//        if (Build.VERSION.SDK_INT >= 24) {
//            imageUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", mOutputImage);
//
//            Log.e("info","=========takePictureFromCamera=imageUri.getPath()="+imageUri.getPath());
//        } else {
//            imageUri = Uri.fromFile(mOutputImage);
//            Log.e("info","=========takePictureFromCamera=mOutputImage.getPath()="+mOutputImage.getPath());
//        }

        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 下面这句指定调用相机拍照后的照片存储的路径
        intent1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "headimg.jpg")));
//        intent1.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent1, cameraRequestCode);
    }


//    File mOutputImage;
//    Uri imageUri;
    private void takePictureFromCamera() {

//        String pictureName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()).format(new Date()) +
//                "-" + System.currentTimeMillis() + ".jpg";


        String pictureName = "headimg.jpg";
        File mOutputImage = new File(getExternalCacheDir(), pictureName);
        Uri imageUri;
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", mOutputImage);

            Log.e("info","=========takePictureFromCamera=imageUri.getPath()="+imageUri.getPath());
        } else {
            imageUri = Uri.fromFile(mOutputImage);
            Log.e("info","=========takePictureFromCamera=mOutputImage.getPath()="+mOutputImage.getPath());
        }


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "headimg.jpg")));
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        ComponentName componentName = intent.resolveActivity(getPackageManager());
        if (componentName != null) {
            startActivityForResult(intent, 2);
        }
    }




    // TODO 拍照返回
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("info", "======requestCode=" + requestCode + "===resultCode=" + resultCode);
        if (requestCode == 1 && resultCode == RESULT_OK) {   // 相册
            if (data != null) {
                Log.i("info","===========data.getData().getPath()=="+data.getData().getPath());
//                new StrUtil().startPhotoZoom(this, uri, 130, 130);
            }
//            mImageView.setImageBitmap(BitmapFactory.decodeFile(parsePicturePath(this, data.getData())));
            cropPicture(new File(parsePicturePath(this, data.getData())));

        } else if (requestCode == 2 && resultCode == RESULT_OK) {// 相机
//            File temp = new File(Environment.getExternalStorageDirectory() + "/headimg.jpg");
//            Uri uri = getImageContentUri(this,temp);
//            new StrUtil().startPhotoZoom(this, uri, 130, 130);
            try {
//                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
//                mImageView.setImageBitmap(bitmap);

//                cropPicture(mOutputImage);

                if(null == data){
                    Log.i("info", "======拍照回来=data=null==22222222");
                }else {
                    if (null == data && null == data.getData()) {
                        Log.i("info", "======拍照回来=data.getData()=nullo==");
                    } else {
                        if (null == parsePicturePath(this, data.getData())) {
                            Log.i("info", "======拍照回来获取路径null==");
                        }
                    }
                }


               File file = new File(Environment.getExternalStorageDirectory(), "headimg.jpg");


//                cropPicture(new File(parsePicturePath(this, data.getData())));
                cropPicture(file);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (requestCode == 3 && resultCode == RESULT_OK) {// 剪切回来
            try {
//                if (data != null) {
//                    Log.i("info", "data 不为空裁剪回来");
//                    setPicToView(data);
//                } else {
//                    Log.i("info", "data 为空");
//                }
                if(null == data.getData()){
                    Log.i("info","====data.getData=null");
                }else{
                    Log.i("info","====data.getData="+data.getData().getPath());
                }
                if(null==cropUri){
                    Log.i("info","==cropUri==null");
                }else{
                    Log.i("info","==cropUri="+cropUri.getPath());
                }


                if (cropUri != null) {
//                    String path = parsePicturePath(this, data.getData());
                    String path = parsePicturePath(this, cropUri);//还是要用cropUri 的
                    //  String imageName = path.substring(path.lastIndexOf("/") + 1); //得到图片名称

                    Log.i("info","===============setPicToView=====uri.getPath()="+cropUri.getPath());
                    Log.i("info","===============setPicToView====path=="+path);

                    mImageView.setImageBitmap(BitmapFactory.decodeFile(path));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    Uri cropUri;
    public void cropPicture(File file) {
        String cropImageName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()).format(new Date()) +
                "-1-" + System.currentTimeMillis() + ".jpg";
        File cropFile = new File(getExternalCacheDir(), cropImageName);

        Intent intent = new Intent("com.android.camera.action.CROP");

        //注意到此处使用的file:// uri类型.
        cropUri = Uri.fromFile(cropFile);
        Uri sourceUri;
        if (Build.VERSION.SDK_INT >= 24) {
            sourceUri  = getImageContentUri(this, file);
        } else {
            sourceUri  = Uri.fromFile(file);
        }

//        intent.setDataAndType(Uri.fromFile(file), "image/*"); //此处有问题
        intent.setDataAndType(sourceUri, "image/*"); //此处有问题
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        ComponentName componentName = intent.resolveActivity(getPackageManager());
        if (componentName != null) {
            startActivityForResult(intent, 3);
        }
    }

    //获取文件的Content uri路径
    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }





    private void test(){
        SparseArray<String> sa = new SparseArray<>();
        sa.put(1,"1111");
        sa.put(2,"2222");
        sa.put(3,"3333");
        sa.put(4,"4444");

        System.out.println("=====1164523817====size="+sa.size());

        for (int i=0;i<sa.size();i++){
            System.out.println("=========i="+i+"=="+sa.get(i));
        }


        System.out.println("=========toString="+sa.toString());
    }


    // 解析获取图片库图片Uri物理路径
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private String parsePicturePath(Context context, Uri uri) {

        if (null == context || uri == null)
            return null;

        boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentUri
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageDocumentsUri
            if (isExternalStorageDocumentsUri(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] splits = docId.split(":");
                String type = splits[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + File.separator + splits[1];
                }
            }
            // DownloadsDocumentsUri
            else if (isDownloadsDocumentsUri(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaDocumentsUri
            else if (isMediaDocumentsUri(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = "_id=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosContentUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;

    }

    private String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        String column = "_data";
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            try {
                if (cursor != null)
                    cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    private boolean isExternalStorageDocumentsUri(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private boolean isDownloadsDocumentsUri(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private boolean isMediaDocumentsUri(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private boolean isGooglePhotosContentUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


}
