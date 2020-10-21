package com.innerfence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;

import com.innerfence.ChargeRequest;
import com.innerfence.ChargeResponse;

public class RNInnerFenceModule extends ReactContextBaseJavaModule {

    private Promise mPromise;

    private static final String E_TERMINAL_NOT_INSTALLED = "E_TERMINAL_NOT_INSTALLED";
    private static final String E_ACTIVITY_DOES_NOT_EXIST = "E_ACTIVITY_DOES_NOT_EXIST";

    private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {

        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
            if( requestCode == ChargeRequest.CCTERMINAL_REQUEST_CODE ) {
                ChargeResponse chargeResponse = new ChargeResponse( data );
    
                String status;
                String recordId = null;
                String transactionId = null;
                String amount = null;
                String currency = null;
                String cardType = null;
                String redactedCardNumber = null;
    
                Bundle extraParams = chargeResponse.getExtraParams();
                if( null != extraParams ) {
                    recordId = chargeResponse.getExtraParams().getString("record_id");
                }
    
                if ( chargeResponse.getResponseCode() == ChargeResponse.Code.APPROVED ) {
                    status = "charged";
                    recordId = String.format("%s\n",recordId);
                    transactionId = String.format("%s\n",chargeResponse.getTransactionId());
                    amount = String.format("%s\n",chargeResponse.getAmount());
                    currency = String.format("%s\n",chargeResponse.getCurrency());
                    cardType = String.format("%s\n",chargeResponse.getCardType());
                    redactedCardNumber = String.format("%s\n",chargeResponse.getRedactedCardNumber());
                } else {
                    status = "not charged";
                }
    
                WritableMap res = Arguments.createMap();
                res.putString("status", status);
                res.putString("recordId", recordId);
                res.putString("transactionId", transactionId);
                res.putString("amount", amount);
                res.putString("currency", currency);
                res.putString("cardType", cardType);
                res.putString("redactedCardNumber", redactedCardNumber);
                mPromise.resolve(res);
            }
        }
    };

    public RNInnerFenceModule(ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addActivityEventListener(mActivityEventListener);
    }

    @Override
    public String getName() {
        return "RNInnerFence";
    }

    @ReactMethod
    public void paymentRequest(final ReadableMap innerFenceParameters, final Promise promise) {
        Activity activity = getCurrentActivity();

        mPromise = promise;

        if (activity == null) {
            promise.reject(E_ACTIVITY_DOES_NOT_EXIST, "Activity doesn't exist");
            return;
        }

        final String address = innerFenceParameters.getString("address");
        final String amount = innerFenceParameters.getString("amount");
        final String currency = innerFenceParameters.getString("currency");
        final String city = innerFenceParameters.getString("city");
        final String company = innerFenceParameters.getString("company");
        final String country = innerFenceParameters.getString("country");
        final String description = innerFenceParameters.getString("description");
        final String email = innerFenceParameters.getString("email");
        final String firstName = innerFenceParameters.getString("firstName");
        final String invoiceNumber = innerFenceParameters.getString("invoiceNumber");
        final String lastName = innerFenceParameters.getString("lastName");
        final String phone = innerFenceParameters.getString("phone");
        final String state = innerFenceParameters.getString("state");
        final String zip = innerFenceParameters.getString("zip");
        final String recordId = innerFenceParameters.getString("recordId");
        
        ChargeRequest chargeRequest = new ChargeRequest();

        Bundle extraParams = new Bundle();
        extraParams.putString( "record_id", recordId );
        chargeRequest.setExtraParams( extraParams );

        chargeRequest.setAddress("123 Test St");
        chargeRequest.setAmount("50.00");
        chargeRequest.setCurrency("USD");
        chargeRequest.setCity("Nowhereville");
        chargeRequest.setCompany("Company Inc");
        chargeRequest.setCountry("US");
        chargeRequest.setDescription("Test transaction");
        chargeRequest.setEmail("john@example.com");
        chargeRequest.setFirstName("John");
        chargeRequest.setInvoiceNumber("321");
        chargeRequest.setLastName("Doe");
        chargeRequest.setPhone("555-1212");
        chargeRequest.setState("HI");
        chargeRequest.setZip("98021");

        try {
            chargeRequest.submit( activity );
        } catch( ChargeRequest.ApplicationNotInstalledException ex ) {
            mPromise.reject(E_TERMINAL_NOT_INSTALLED, "Credit card terminal not installed");
        }
    }

}