package io.palaima.smoothbluetooth.app;

import com.afollestad.materialdialogs.MaterialDialog;
import com.palaima.bluetooth.app.R;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.palaima.smoothbluetooth.SmoothBluetooth;
import io.palaima.smoothbluetooth.Device;


public class MainActivity extends AppCompatActivity {

    public static final int ENABLE_BT__REQUEST = 1;

    private static SmoothBluetooth mSmoothBluetooth;

    private TextView mStateTv;

    private TextView mDeviceTv;

    private Button mPairedButton;

    private Button mSendPrevButton;

    private Button mSendNextButton;

    private SeekBar mSeekBrightBar;

    private SeekBar mSeekSpeedBar;

    private Button mDisconnectButton;

    private LinearLayout mConnectionLayout;

    private EditText mMessageInput;

    private Long lastSentTime;

    private Button mSendButton;

    private Button mPickPattern;

    private static CheckBox mCRLFBox;

    private List<Integer> mBuffer = new ArrayList<>();
    private List<String> mResponseBuffer = new ArrayList<>();

    private ArrayAdapter<String> mResponsesAdapter;

    public static SmoothBluetooth getmSmoothBluetooth(){
        return mSmoothBluetooth;
    }

    public static CheckBox getmCRLFBox(){
        return mCRLFBox;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_main);

        mSmoothBluetooth = new SmoothBluetooth(this);

        mSmoothBluetooth.setListener(mListener);

        ListView responseListView = (ListView) findViewById(R.id.responses);
        mResponsesAdapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, mResponseBuffer);
        responseListView.setAdapter(mResponsesAdapter);

        mCRLFBox = (CheckBox) findViewById(R.id.carrage);
        mMessageInput = (EditText) findViewById(R.id.message);

        mSendButton = (Button) findViewById(R.id.send);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send(mMessageInput.getText().toString(), mCRLFBox.isChecked());
                mMessageInput.setText("");
            }
        });

        mSendPrevButton = (Button) findViewById(R.id.sendPrevPattern);
        mSendPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("l", mCRLFBox.isChecked());
            }
        });

        mSendNextButton = (Button) findViewById(R.id.sendNextPattern);
        mSendNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("n", mCRLFBox.isChecked());
            }
        });

        lastSentTime = System.currentTimeMillis();

        mSeekBrightBar = (SeekBar) findViewById(R.id.seekBright);
        mSeekBrightBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                if(System.currentTimeMillis() - lastSentTime > 100) {
                    mSmoothBluetooth.send("b", mCRLFBox.isChecked());
                    mSmoothBluetooth.send(String.valueOf(seekBar.getProgress()), mCRLFBox.isChecked());
                    lastSentTime = System.currentTimeMillis();
                }
            }
        });

        mSeekSpeedBar = (SeekBar) findViewById(R.id.seekSpeed);
        mSeekSpeedBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                if(System.currentTimeMillis() - lastSentTime > 100) {
                    mSmoothBluetooth.send("s", mCRLFBox.isChecked());
                    mSmoothBluetooth.send(String.valueOf(seekBar.getProgress()), mCRLFBox.isChecked());
                    lastSentTime = System.currentTimeMillis();
                }
            }
        });

        mDisconnectButton = (Button) findViewById(R.id.disconnect);
        mDisconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.disconnect();
                mResponseBuffer.clear();
                mResponsesAdapter.notifyDataSetChanged();
            }
        });

        mConnectionLayout = (LinearLayout) findViewById(R.id.connection);

        mPairedButton = (Button) findViewById(R.id.paired);
        mPairedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.tryConnection();
            }
        });

        mPickPattern = (Button) findViewById(R.id.pickPattern);
        mPickPattern.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent pat = new Intent(MainActivity.this, io.palaima.smoothbluetooth.app.PatternActivity.class);
                startActivity(pat);
            }
        });

        mStateTv = (TextView) findViewById(R.id.state);
        mStateTv.setText("Disconnected");
        mDeviceTv = (TextView) findViewById(R.id.device);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSmoothBluetooth.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ENABLE_BT__REQUEST) {
            if(resultCode == RESULT_OK) {
                mSmoothBluetooth.tryConnection();
            }
        }
    }

    private SmoothBluetooth.Listener mListener = new SmoothBluetooth.Listener() {
        @Override
        public void onBluetoothNotSupported() {
            Toast.makeText(MainActivity.this, "Bluetooth not found", Toast.LENGTH_SHORT).show();
            finish();
        }

        @Override
        public void onBluetoothNotEnabled() {
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth, ENABLE_BT__REQUEST);
        }

        @Override
        public void onConnecting(Device device) {
            mStateTv.setText("Connecting to");
            mDeviceTv.setText(device.getName());
        }

        @Override
        public void onConnected(Device device) {
            mStateTv.setText("Connected to");
            mDeviceTv.setText(device.getName());
            mConnectionLayout.setVisibility(View.GONE);
            mDisconnectButton.setVisibility(View.VISIBLE);
        }

        @Override
        public void onDisconnected() {
            mStateTv.setText("Disconnected");
            mDeviceTv.setText("");
            mDisconnectButton.setVisibility(View.GONE);
            mConnectionLayout.setVisibility(View.VISIBLE);
        }

        @Override
        public void onConnectionFailed(Device device) {
            mStateTv.setText("Disconnected");
            mDeviceTv.setText("");
            Toast.makeText(MainActivity.this, "Failed to connect to " + device.getName(), Toast.LENGTH_SHORT).show();
            if (device.isPaired()) {
                mSmoothBluetooth.doDiscovery();
            }
        }

        public void writeTolog(String data){
            mResponseBuffer.add(0, data);
            mResponsesAdapter.notifyDataSetChanged();
        }

        @Override
        public void onDiscoveryStarted() {
            Toast.makeText(MainActivity.this, "Searching", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDiscoveryFinished() {

        }

        @Override
        public void onNoDevicesFound() {
            Toast.makeText(MainActivity.this, "No device found", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDevicesFound(final List<Device> deviceList,
                final SmoothBluetooth.ConnectionCallback connectionCallback) {

            final MaterialDialog dialog = new MaterialDialog.Builder(MainActivity.this)
                    .title("Devices")
                    .adapter(new DevicesAdapter(MainActivity.this, deviceList),null)
                    .build();

            ListView listView = dialog.getListView();
            if (listView != null) {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        connectionCallback.connectTo(deviceList.get(position));
                        dialog.dismiss();
                    }

                });
            }

            dialog.show();

        }

        @Override
        public void onDataReceived(int data) {
            mBuffer.add(data);
            if (data == 10 && !mBuffer.isEmpty()) {
            //if (data == 0x0D && !mBuffer.isEmpty() && mBuffer.get(mBuffer.size()-2) == 0xA0) {
                StringBuilder sb = new StringBuilder();
                for (int integer : mBuffer) {
                    sb.append((char)integer);
                }
                mBuffer.clear();
                mResponseBuffer.add(0, sb.toString());
                mResponsesAdapter.notifyDataSetChanged();
            }
        }
    };
}
