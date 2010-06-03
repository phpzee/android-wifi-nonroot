package com.realgt.androidwifinonroot;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

public class AndroidWifiNonRoot extends Activity {
   
	private static final String TAG = "AndroidWifiNonRoot";
	private static final String SSID_NAME = "ENTER_NETWORK_SSID_HERE";
	private static final String SHARED_KEY = "ENTER_SHARED_KEY_HERE";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiConfiguration config = new WifiConfiguration();
		config.SSID = "\"" + SSID_NAME + "\"";
//		config.preSharedKey = "\"" + SHARED_KEY+ "\"";
		
		//set to true if your network SSID is not broadcast!
		config.hiddenSSID = true;
		config.status = WifiConfiguration.Status.ENABLED;
		
		//KeyManagement
		config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.IEEE8021X);
//		config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
		
		//GroupCiphers
		config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
		config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
//		config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
		
		//PairwiseCiphers
//		config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
//		config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);

		//Authentication Algorithms
		config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
		config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
		config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.LEAP);
		
		config.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
//		config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
		
		
/*
 * 		After you've modified the code above based on your network configuration, you're almost ready launch this app in DEBUG mode!
 * 		**Make sure you enabled USB Debugging on your phone**
 *		Its time to do some live-debug hacking! Place a breakpoint on the line where it says:  
 *			int newNetworkId = wifiManager.addNetwork(config);
 * 		Right-click on the word "config" and select Watch from the menu. Modify the EnterpriseFields such as identity, eap, password, etc.
 * 		by expanding the fields (such as identity, and right clicking on the "value" item and select Change Value.
 * 		
 * 		REMEMBER YOU NEED TO WRAP IDENTITY AND PASSWORD IN QUOTES! so if your login is john@doe.com enter "john@doe.com" in the box (including quotes)	
 * 		Values in the "eap" property need not be wrapped in quotes.
 * 		Switch to Expressions view in Eclipse, expand the config item in your list
 */
		
		//MAKE SURE YOU PLACE A BREAKPOINT ON THE NEXT LINE!!!
		int newNetworkId = wifiManager.addNetwork(config);
		//ONCE YOU'VE MODIFIED THE VALUES PRESS F8 to continue!
		
		
		Log.d(TAG, "Adding Network returned: " + newNetworkId);
		boolean b = wifiManager.enableNetwork(newNetworkId, true);
		Log.d(TAG, "Enabling Network returned " + b);
		boolean s = wifiManager.saveConfiguration();
		Log.d(TAG, "Saving Configuration returned " + s);
		
		setContentView(R.layout.main);
    }
}