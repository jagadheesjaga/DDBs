package com.myapplication1;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by RAVEE on 29/08/2016.
 */
public class KsoapSample {

    private final String NAMESPACE = "http://192.169.143.81/plesk-site-preview/webservice.fams.bz/192.169.143.81/";
    private final String URL = "http://192.169.143.81/plesk-site-preview/webservice.fams.bz/192.169.143.81/WebService.asmx";
    private final String SOAP_ACTION = "http://192.169.143.81/plesk-site-preview/webservice.fams.bz/192.169.143.81/Value1";
    private final String METHOD_NAME = "Value1";

    public void Request (){
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.call(SOAP_ACTION,envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            Log.e("Message",""+response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}

