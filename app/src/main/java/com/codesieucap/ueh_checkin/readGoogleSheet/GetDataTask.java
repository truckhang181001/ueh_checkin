package com.codesieucap.ueh_checkin.readGoogleSheet;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codesieucap.ueh_checkin.models.JoinerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class GetDataTask extends AsyncTask<Void, Void, Void> {

    int jIndex;
    int x;
    private List<JoinerModel> list;

    public GetDataTask(List<JoinerModel> list) {
        this.list = list;
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
    }

    @Nullable
    @Override
    protected Void doInBackground(Void... params) {

        /**
         * Getting JSON Object from Web Using okHttp
         */
        JSONObject jsonObject = JSONParser.getDataFromWeb();

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
                            JoinerModel model = new JoinerModel();

                            /**
                             * Getting Inner Object from contacts array...
                             * and
                             * From that We will get Name of that Contact
                             *
                             */
                            JSONObject innerObject = array.getJSONObject(jIndex);
                            String studentID = innerObject.getString(Keys.KEY_STUDENTID);
                            Log.d("TRUCKHANG", studentID);
                            String name = innerObject.getString(Keys.KEY_NAME);
                            Log.d("TRUCKHANG", name);
                            String className = innerObject.getString(Keys.KEY_CLASSNAME);
                            String email = innerObject.getString(Keys.KEY_EMAIL);
                            String gender = innerObject.getString(Keys.KEY_GENDER);

                            /**
                             * Getting Object from Object "phone"
                             */
                            //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
                            //String phone = phoneObject.getString(Keys.KEY_MOBILE);

                            model.setEmail(email);
                            model.setJoinerName(name);
                            model.setIdCode(studentID);
                            model.setClassName(className);
                            model.setGender(gender);


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

}

