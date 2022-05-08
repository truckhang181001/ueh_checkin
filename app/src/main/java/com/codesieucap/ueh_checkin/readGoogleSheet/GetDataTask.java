package com.codesieucap.ueh_checkin.readGoogleSheet;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.codesieucap.ueh_checkin.R;
import com.codesieucap.ueh_checkin.models.EventModel;
import com.codesieucap.ueh_checkin.models.JoinerModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GetDataTask extends AsyncTask<Void, Void, Void> {

    ProgressDialog dialog;
    int jIndex;
    int x;
    private Map<String,JoinerModel> map;
    private String sheetURL;
    private Context context;
    private EventModel eventItem;
    private DatabaseReference mDatabase;
    private StorageReference mStorage;


    public GetDataTask(Context context, Map<String,JoinerModel> map, String sheetURL, EventModel eventItem) {
        this.map = map;
        this.sheetURL = sheetURL;
        this.context = context;
        this.eventItem = eventItem;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        /**
         * Progress Dialog for User Interaction
         */

        x=map.size();

        if(x==0)
            jIndex=0;
        else
            jIndex=x;

        dialog = new ProgressDialog(context);
        dialog.setTitle("DANH SÁCH THAM GIA");
        dialog.setMessage("Đang lấy dữ liệu người tham gia...");
        dialog.setIcon(R.drawable.ueh_check_logo);
        dialog.show();
    }

    @android.support.annotation.Nullable
    @Override
    protected Void doInBackground(Void... params) {

        /**
         * Getting JSON Object from Web Using okHttp
         */
        JSONObject jsonObject = new JSONParser(sheetURL).getDataFromWeb();

        try {
            /**
             * Check Whether Its NULL???
             */
            if (jsonObject != null) {
                /**
                 * Check Length...
                 */
                if(jsonObject.length() > 0) {
                    /**
                     * Getting Array named "contacts" From MAIN Json Object
                     */
                    JSONArray array = jsonObject.getJSONArray(Keys.KEY_CONTACTS);

                    /**
                     * Check Length of Array...
                     */


                    int lenArray = array.length();
                    if(lenArray > 0) {
                        for( ; jIndex < lenArray; jIndex++) {

                            /**
                             * Creating Every time New Object
                             * and
                             * Adding into List
                             */

                            /**
                             * Getting Inner Object from contacts array...
                             * and
                             * From that We will get Name of that Contact
                             *
                             */
                            JSONObject innerObject = array.getJSONObject(jIndex);
                            String studentID = innerObject.getString(Keys.KEY_STUDENTID);
                            String name = innerObject.getString(Keys.KEY_NAME);
                            String className = innerObject.getString(Keys.KEY_CLASSNAME);
                            String email = innerObject.getString(Keys.KEY_EMAIL);
                            String gender = innerObject.getString(Keys.KEY_GENDER);

                            /**
                             * Getting Object from Object "phone"
                             */
                            //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
                            //String phone = phoneObject.getString(Keys.KEY_MOBILE);

                            JoinerModel model = new JoinerModel(studentID,name,className,email,gender);

                            /**
                             * Adding name and phone concatenation in List...
                             */

                            map.put(model.getIdCode(),model);
                        }
                    }
                }
            } else {

            }
        } catch (JSONException je) {
            Log.i(JSONParser.TAG, "" + je.getLocalizedMessage());
        }
        return null;
    }

    private byte[] generateQR(String message){
        // Initializing the QR Encoder with your value to be encoded, type you required and Dimension
        QRGEncoder qrgEncoder = new QRGEncoder(message, null, QRGContents.Type.TEXT, 1500);
        qrgEncoder.setColorBlack(Color.BLACK);
        qrgEncoder.setColorWhite(Color.WHITE);
        // Getting QR-Code as Bitmap
        Bitmap bitmap = qrgEncoder.getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        return data;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        /**
         * Checking if List size if more than zero then
         * Update ListView
         */
        if(map.size() > 0) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("Event").child(eventItem.getIdCode()).setValue(eventItem);

            Set<String> keySetJoiner = map.keySet();

            for(String key: keySetJoiner){
                String instance = key;
                mStorage = FirebaseStorage.getInstance().getReference();

                final String randomKey = UUID.randomUUID().toString();
                StorageReference riversRef = mStorage.child("images/"+randomKey);

                UploadTask uploadTask = riversRef.putBytes(generateQR(map.get(instance).getTicketCode()));
                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return riversRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        Log.d("TRUCKHANG", String.valueOf(instance));
                        map.get(instance).setTicketCodeLink(task.getResult().toString());

                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        mDatabase.child("Event").child(eventItem.getIdCode()).child("listJoiner").child(instance).setValue(map.get(instance));

//                        if(instance == list.size() - 1){
//                            dialog.dismiss();
//                            Toast.makeText(context, "Upload dữ liệu thành công",Toast.LENGTH_LONG).show();
//                        }
                    }
                });
            }
        }
        else {
            Toast.makeText(context, "Đã có lỗi, vui lòng thử lại",Toast.LENGTH_LONG).show();
        }
    }
}
