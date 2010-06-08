package com.realgt.androidwifinonroot;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

/*
 * 	Edit values of the SSID_NAME and SHARED_KEY (if you need it)
 * 	and make sure to place a breakpoint where it says:   int newNetworkId = wifiManager.addNetwork(config);
 */
public class AndroidWifiNonRoot extends Activity {
   
	private static final String TAG = "AndroidWifiNonRoot";
	private static final String SSID_NAME = "sL1c&N53";
//	private static final String SHARED_KEY = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        
        if (!wifiManager.isWifiEnabled()) wifiManager.setWifiEnabled(true);
		
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
 *		Once you've placed the breakpoint, launch Debug mode for this project (right click project name, Debug As->Android Application
 * 		When it comes to the breakpoint, right-click on the word "config" and select Watch from the menu. 
 * 		Switch to Expressions view in Eclipse, expand the config item in your list
 * 		Modify the EnterpriseFields such as identity, eap, password, etc. by expanding the fields 
 * 		such as "identity" and right click on the "value" item and select Change Value.
 * 		
 * 		REMEMBER YOU NEED TO WRAP IDENTITY AND PASSWORD IN QUOTES! so if your login is john@doe.com enter "john@doe.com" in the box (including quotes)	
 * 		Values in the "eap" property need not be wrapped in quotes.
 * 		
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