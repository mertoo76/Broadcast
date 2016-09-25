package gun.mert.broadcast;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by user on 24.09.2016.
 */
public class ServerBTConnection extends Thread {
// blutooth
    private final BluetoothServerSocket serverSocket;
    private static final String serviceName = "BluetoothDataTransfer";

    public ServerBTConnection(BluetoothAdapter btAdapter) {
        Log.d("BluetoothDevice Server", "Bluetooth Server Connection Thread calisiyor...");
        BluetoothServerSocket tempServerSocket = null;
        try {
            tempServerSocket = btAdapter.listenUsingRfcommWithServiceRecord(serviceName, UUID.fromString("f4798eca-54ed-49b7-8b12-925b1084aa5a"));
        } catch (IOException e) {
            Log.d("BluetoothDevice Server", "BluetoothServerSocket Hata: " + e.toString());
        }
        serverSocket = tempServerSocket;
    }
    @Override
    public void run() {
        BluetoothSocket btSocket = null;
        while(true) {
            try {
                btSocket = serverSocket.accept();
            } catch (IOException e) {
                Log.d("BluetoothDevice Server", "Gelen baglanti istegi kabul edilmedi.");
                break;
            }
            if(btSocket != null) {
                Log.d("BluetoothDevice", "Socket baglanti istegi kabul edildi.");
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    Log.d("BluetoothDevice Server", "ServerSocket baglantisi kapatilamadi: " + e.toString());
                }
                break;
            }
        }
    }

}
