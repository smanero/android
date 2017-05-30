package org.gaizer.adapter;

import java.io.InputStream;
import java.util.Set;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

public class BluetoothClient {
	
	public static void test() {
		try {
			BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
			bta.setName("STT-BT-ADAPTER");
//			get a set of BluetoothDevice objects representing all paired devices with getBondedDevices();
			bta.startDiscovery();
//			start device discovery with startDiscovery();
//			or create a BluetoothServerSocket to listen for incoming connection requests with 
			String name = "";
			UUID uuid = UUID.fromString("");
			BluetoothServerSocket btss = bta.listenUsingInsecureRfcommWithServiceRecord(name, uuid);
			BluetoothSocket bts = btss.accept(1000);
			while (!bts.isConnected()) {
				InputStream is = bts.getInputStream();
			}
			
			bta.listenUsingRfcommWithServiceRecord(name, uuid);
			
		} catch (Exception e) {
		}
	}	
	
	
	public static void viewDevices() {
		BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
		Set<BluetoothDevice> bonedDevices = bta.getBondedDevices();
		for (BluetoothDevice btd : bonedDevices) {
//			boolean fetch = btd.fetchUuidsWithSdp();
			String address = btd.getAddress();
			BluetoothClass btc = btd.getBluetoothClass();
			int cont = BluetoothClass.Service.AUDIO;
//			BluetoothClass.Device.AUDIO_VIDEO_HEADPHONES
			int iState = btd.getBondState();
			
//			btd.getUuids();
			
		}
		
	}
}
