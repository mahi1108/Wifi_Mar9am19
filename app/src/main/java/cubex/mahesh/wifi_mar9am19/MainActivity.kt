package cubex.mahesh.wifi_mar9am19

import android.content.Context
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var wManager :WifiManager = applicationContext.
getSystemService(Context.WIFI_SERVICE) as WifiManager
        // 1
        var wState:Int = wManager.wifiState
        when(wState)
        {
            0 -> s1.setChecked(false)
            1 -> s1.setChecked(false)
            2 -> s1.setChecked(true)
            3 -> s1.setChecked(true)
        }
        //2
        s1.setOnCheckedChangeListener { compoundButton, b ->
            wManager.setWifiEnabled(b)
        }
        //3
        getWifiDevices.setOnClickListener {
            var wlist:List<ScanResult> = wManager.scanResults
            var templist = mutableListOf<String>()
            for(device in wlist)
            {
                templist.add(device.SSID +"\n"+device.frequency)
            }
            var adapter = ArrayAdapter<String>(
                this@MainActivity,
                android.R.layout.simple_list_item_single_choice,
                templist)
            lview.setAdapter(adapter)
        }
        //4
        pairedWifiDevices.setOnClickListener {
            var wlist:List<WifiConfiguration> = wManager.configuredNetworks
            var templist = mutableListOf<String>()
            for(device in wlist)
            {
                templist.add(device.SSID +"\n"+device.status)
            }
            var adapter = ArrayAdapter<String>(
                this@MainActivity,
                android.R.layout.simple_list_item_single_choice,
                templist)
            lview.setAdapter(adapter)
        }


    }
}
