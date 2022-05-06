package com.codesieucap.ueh_checkin.readGoogleSheet;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.codesieucap.ueh_checkin.models.EventModel;
import com.codesieucap.ueh_checkin.models.JoinerModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class GetDataTask extends AsyncTask<Void, Void, Void> {

    ProgressDialog dialog;
    int jIndex;
    int x;
    private List<JoinerModel> list;
    private String sheetURL;
    private Context context;
    private EventModel eventItem;
    private DatabaseReference mDatabase;


    public GetDataTask(Context context, List<JoinerModel> list, String sheetURL, EventModel eventItem) {
        this.list = list;
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

        x=list.size();

        if(x==0)
            jIndex=0;
        else
            jIndex=x;

        dialog = new ProgressDialog(context);
        dialog.setTitle("Hey Wait Please..."+x);
        dialog.setMessage("I am getting your JSON");
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
                            list.add(model);


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

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        dialog.dismiss();
        /**
         * Checking if List size if more than zero then
         * Update ListView
         */
        if(list.size() > 0) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("Event").child(eventItem.getIdCode()).setValue(eventItem);
            Toast.makeText(context, "Upload dữ liệu thành công",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "Đã có lỗi, vui lòng thử lại",Toast.LENGTH_LONG).show();
        }
    }
}
