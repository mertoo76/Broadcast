package gun.mert.broadcast;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by user on 24.09.2016.
 */
public class ClientBTConnection extends Thread{

//bluetooth
    private BluetoothSocket btSocket;
    private BluetoothDevice btDevice;
    private static final String uuidVal = "f4798eca-54ed-49b7-8b12-925b1084aa5a";

    public ClientBTConnection(BluetoothDevice device) {
        Log.d("BluetoothDevice Client", "Bluetooth Client Connection Thread calisiyor...");
        BluetoothSocket tempSocket = null;
        btDevice = device;
        try {
            tempSocket = device.createRfcommSocketToServiceRecord(UUID.fromString(uuidVal));
        } catch (IOException e) {
            Log.d("BluetoothDevice Client", "RFCOMM socket olusturulamadi: " + e.toString());
        }
        btSocket = tempSocket;
    }

    @Override
    public void run() {
        try {
            btSocket.connect();
        } catch (IOException e) {
            Log.d("BluetoothDevice Client", "Baglanti basarisiz: " + e.toString());
            try {
                btSocket.close();
            } catch (IOException e1) {
                Log.d("BluetoothDevice Client", "Baglanti kapatma basarisiz: " + e.toString());
            }
            return;
        }
    }
}
